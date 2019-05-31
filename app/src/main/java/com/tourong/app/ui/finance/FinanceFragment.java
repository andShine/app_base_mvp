package com.tourong.app.ui.finance;

import android.os.Bundle;
import android.view.View;
import com.gyf.immersionbar.ImmersionBar;
import com.tourong.app.R;
import com.tourong.app.base.BaseToolbarFragment;

/**
 * 主页-理财
 */
public class FinanceFragment extends BaseToolbarFragment {

    public static FinanceFragment newInstance() {
        FinanceFragment fragment = new FinanceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finance;
    }

    @Override
    protected int getTitleBar() {
        return R.id.toolbar;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        ImmersionBar.with(_mActivity).statusBarDarkFont(false).init();
    }
}
