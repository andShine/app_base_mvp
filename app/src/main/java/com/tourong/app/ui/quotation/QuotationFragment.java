package com.tourong.app.ui.quotation;

import android.os.Bundle;
import android.view.View;
import com.tourong.app.R;
import com.tourong.app.base.BaseFragment;

/**
 * 主页-行情
 */
public class QuotationFragment extends BaseFragment {

    public static QuotationFragment newInstance() {
        QuotationFragment fragment = new QuotationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quotation;
    }
}
