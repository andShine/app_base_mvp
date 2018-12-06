package com.tourong.app.ui.account;

import com.tourong.app.entity.AccountEntity;
import com.tourong.app.net.NetWorkManager;
import com.tourong.app.net.response.Response;
import io.reactivex.Observable;

public class AccountModel implements AccountContract.Model {

    @Override
    public Observable<Response<AccountEntity>> getAccountInfo() {
        return NetWorkManager.getRequest().getAccountInfo();
    }
}
