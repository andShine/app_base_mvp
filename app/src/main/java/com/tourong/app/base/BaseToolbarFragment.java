package com.tourong.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.gyf.barlibrary.ImmersionBar;
import com.tourong.app.R;

public abstract class BaseToolbarFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View titleBar = view.findViewById(getTitleBar());
        ImmersionBar.setTitleBar(_mActivity, titleBar);
        View statusBarView = view.findViewById(getStatusBarView());
        ImmersionBar.setStatusBarView(_mActivity, statusBarView);
    }

    /**
     * @return 可以设置为自定义View，不能是RelativeLayout布局
     */
    protected int getTitleBar(){
        return R.id.toolbar;
    }

    protected int getStatusBarView() {
        return 0;
    }
}
