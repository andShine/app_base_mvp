package com.tourong.app.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.blankj.utilcode.util.LogUtils;
import com.tourong.app.R;
import com.tourong.app.base.BaseMvpFragment;
import com.tourong.app.entity.AccountEntity;

/**
 * 主页-账户
 */
public class AccountFragment extends BaseMvpFragment<AccountPresenter> implements AccountContract.View {

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        mPresenter = new AccountPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 请求账户信息
        mPresenter.getAccountInfo();
    }

    @Override
    public void showLoading() {
        LogUtils.d("showLoading()");
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(AccountEntity account) {

    }

    @Override
    public void onError(Throwable throwable) {
        LogUtils.d("onError()");
    }
}
