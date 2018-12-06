package com.tourong.app.ui.account;

import com.tourong.app.base.BaseView;
import com.tourong.app.entity.AccountEntity;
import com.tourong.app.net.response.Response;
import io.reactivex.Observable;

public interface AccountContract {

    interface Model {
        Observable<Response<AccountEntity>> getAccountInfo();
    }

    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void onSuccess(AccountEntity account);

        void onError(Throwable throwable);
    }

    interface Presenter {
        /**
         * 得到账户信息
         */
        void getAccountInfo();
    }
}
