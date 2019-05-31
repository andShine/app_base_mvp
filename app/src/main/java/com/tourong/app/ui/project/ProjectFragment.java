package com.tourong.app.ui.project;

import android.os.Bundle;
import android.view.View;
import com.gyf.immersionbar.ImmersionBar;
import com.tourong.app.R;
import com.tourong.app.base.BaseToolbarFragment;

/**
 * 主页-项目库
 */
public class ProjectFragment extends BaseToolbarFragment {

    public static ProjectFragment newInstance() {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected int getTitleBar() {
        return R.id.toolbar;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        ImmersionBar.with(_mActivity).statusBarDarkFont(true, 0.2f).init();
    }
}
