package com.tourong.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tourong.app.event.StartBrotherEvent;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment extends SupportFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    /**
     * MainFragment中跳转专用
     */
    public void startFragment(SupportFragment fragment) {
        EventBusActivityScope.getDefault(_mActivity).post(new StartBrotherEvent(fragment));
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    /**
     * 初始化视图
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int getLayoutId();
}
