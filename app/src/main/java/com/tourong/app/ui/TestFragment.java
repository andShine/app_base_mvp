package com.tourong.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.blankj.utilcode.util.LogUtils;
import com.tourong.app.R;
import com.tourong.app.base.BaseToolbarSwipeFragment;
import com.tourong.app.net.NetWorkManager;
import com.tourong.app.net.exception.ApiException;
import com.tourong.app.net.response.ResponseTransformer;
import com.tourong.app.net.schedulers.SchedulerProvider;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public class TestFragment extends BaseToolbarSwipeFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("测试");
        toolbar.setNavigationOnClickListener(v -> pop());
    }

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }
}
