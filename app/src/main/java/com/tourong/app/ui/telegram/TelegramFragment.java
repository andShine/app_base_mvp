package com.tourong.app.ui.telegram;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tourong.app.R;
import com.tourong.app.base.BaseFragment;
import com.tourong.app.ui.TestFragment;

/**
 * 主页-电报
 */
public class TelegramFragment extends BaseFragment {

    @BindView(R.id.textView)
    TextView textView;

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
}
