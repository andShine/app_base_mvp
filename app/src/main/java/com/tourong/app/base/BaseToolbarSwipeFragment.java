package com.tourong.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseToolbarSwipeFragment extends BaseSwipeFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (0 != getTitleBar()) {
            View titleBar = view.findViewById(getTitleBar());
            ImmersionBar.setTitleBar(_mActivity, titleBar);
        }
    }

    /**
     * @return 可以设置为自定义View，不能是RelativeLayout布局
     */
    protected abstract int getTitleBar();

}
