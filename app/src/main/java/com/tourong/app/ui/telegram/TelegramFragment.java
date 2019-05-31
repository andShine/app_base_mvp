package com.tourong.app.ui.telegram;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.gyf.immersionbar.ImmersionBar;
import com.tourong.app.R;
import com.tourong.app.base.BaseToolbarFragment;
import com.tourong.app.ui.TestFragment;

/**
 * 主页-电报
 */
public class TelegramFragment extends BaseToolbarFragment {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static TelegramFragment newInstance() {
        TelegramFragment fragment = new TelegramFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        textView.setOnClickListener(v ->
                startFragment(TestFragment.newInstance()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_telegram;
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
