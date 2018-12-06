package com.tourong.app.ui.account;

import com.blankj.utilcode.util.LogUtils;
import com.tourong.app.base.BasePresenter;
import com.tourong.app.net.response.ResponseTransformer;
import com.tourong.app.net.schedulers.SchedulerProvider;

public class AccountPresenter extends BasePresenter<AccountContract.View> implements AccountContract.Presenter {

    private AccountModel model;

    public AccountPresenter() {
        model = new AccountModel();
    }

    @Override
    public void getAccountInfo() {
        if (!isViewAttached()) {
            return;
        }
        LogUtils.d("getAccountInfo()");
        mView.showLoading();
        model.getAccountInfo()
                .compose(ResponseTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .as(mView.bindAutoDispose())
                .subscribe(accountEntity -> {
                            // 获取账户数据成功
                            mView.onSuccess(accountEntity);
                            mView.hideLoading();
                        }, throwable -> {
                            mView.onError(throwable);
                            mView.hideLoading();
                        }
                );
    }
}
