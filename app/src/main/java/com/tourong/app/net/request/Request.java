package com.tourong.app.net.request;

import com.tourong.app.entity.AccountEntity;
import com.tourong.app.net.response.Response;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 封装请求的接口
 */
public interface Request {

    String HOST = "https://www.jujinsoft.cn/";
    String HOST_DEV = "";

    @GET("eat/cookbook/listclass")
    Observable<Response<String>> getClassList();

    @GET("trapi/user/getaccountinfo")
    Observable<Response<AccountEntity>> getAccountInfo();
}
