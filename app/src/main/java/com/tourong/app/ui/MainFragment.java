package com.tourong.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import com.tourong.app.R;
import com.tourong.app.base.BaseFragment;
import com.tourong.app.event.StartBrotherEvent;
import com.tourong.app.event.TabSelectedEvent;
import com.tourong.app.ui.account.AccountFragment;
import com.tourong.app.ui.finance.FinanceFragment;
import com.tourong.app.ui.project.ProjectFragment;
import com.tourong.app.ui.quotation.QuotationFragment;
import com.tourong.app.ui.telegram.TelegramFragment;
import com.tourong.app.widget.BottomBar;
import com.tourong.app.widget.BottomBarTab;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;
import org.greenrobot.eventbus.Subscribe;

public class MainFragment extends BaseFragment {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_main_telegram,R.drawable.ic_main_telegram_checked, "电报"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_main_finance,R.drawable.ic_main_finance_checked, "理财"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_main_quotation,R.drawable.ic_main_quotation_checked, "行情"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_main_project,R.drawable.ic_main_project_checked, "项目库"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_main_account,R.drawable.ic_main_account_checked, "账户"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
                BottomBarTab tab = mBottomBar.getItem(FIRST);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 主要为了交互: 重选tab 比如：如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
        EventBusActivityScope.getDefault(_mActivity).register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(TelegramFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = TelegramFragment.newInstance();
            mFragments[SECOND] = FinanceFragment.newInstance();
            mFragments[THIRD] = QuotationFragment.newInstance();
            mFragments[FOURTH] = ProjectFragment.newInstance();
            mFragments[FIFTH] = AccountFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(FinanceFragment.class);
            mFragments[THIRD] = findChildFragment(QuotationFragment.class);
            mFragments[FOURTH] = findChildFragment(ProjectFragment.class);
            mFragments[FOURTH] = findChildFragment(AccountFragment.class);
        }
    }

    /**
     * start other BrotherFragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event){
        start(event.targetFragment);
    }

    @Override
    public boolean onBackPressedSupport() {
        // return super.onBackPressedSupport();
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
    }
}
