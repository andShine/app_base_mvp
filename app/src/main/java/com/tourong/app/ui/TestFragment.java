package com.tourong.app.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.blankj.utilcode.util.LogUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tourong.app.R;
import com.tourong.app.base.BaseToolbarSwipeFragment;
import com.tourong.app.net.NetWorkManager;
import com.tourong.app.net.exception.ApiException;
import com.tourong.app.net.response.ResponseTransformer;
import com.tourong.app.net.schedulers.SchedulerProvider;
import com.tourong.app.utils.SaveImgUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.io.File;

public class TestFragment extends BaseToolbarSwipeFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("测试");
        toolbar.setNavigationOnClickListener(v -> pop());
    }

    @Override
    protected int getTitleBar() {
        return R.id.toolbar;
    }

    // 图片保存路径
    private String floderPath = Environment.getExternalStorageDirectory() + File.separator + "ceshi";
    // 图片保存名称
    private String fileName = "img_" + System.currentTimeMillis();
    // 图片地址
    private String imgPath = "http://n.sinaimg.cn/photo/700/w1000h500/20190530/353f-hxsrwwr0289459.jpg";

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.cardView).setOnClickListener(v -> {
                    NetWorkManager.getRequest().getClassList()
                            .compose(ResponseTransformer.handleResult())
                            .compose(SchedulerProvider.getInstance().applySchedulers())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                            .subscribe(stringResponse -> {
                                        TextView tvTest = view.findViewById(R.id.tvTest);
                                        tvTest.setText(stringResponse);
                                    }, throwable -> {
                                        // 处理异常
                                        LogUtils.d(((ApiException) throwable).getDisplayMessage());
                                    }
                            );
                }
        );
        view.findViewById(R.id.cardImg).setOnClickListener(v ->
                SaveImgUtils.downloadImg(getActivity(), imgPath, floderPath, fileName));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout.finishRefresh(5000);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        ImmersionBar.with(_mActivity).statusBarDarkFont(true, 0.2f).init();
    }
}
