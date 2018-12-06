package com.tourong.app.ui.finance;

import android.os.Bundle;
import android.view.View;
import com.tourong.app.R;
import com.tourong.app.base.BaseFragment;

/**
 * 主页-理财
 */
public class FinanceFragment extends BaseFragment {

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
}
