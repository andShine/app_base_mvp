package com.tourong.app.ui.project;

import android.os.Bundle;
import android.view.View;
import com.tourong.app.R;
import com.tourong.app.base.BaseFragment;

/**
 * 主页-项目库
 */
public class ProjectFragment extends BaseFragment {

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
}
