package com.tourong.app.net.request;

import com.tourong.app.entity.AccountEntity;
import com.tourong.app.net.response.Response;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 封装请求的接口
 */
public interface Request {

    String HOST = "https://www.jujinsoft.cn/";
    String HOST_DEV = "";

    @GET
    @Streaming
    Observable<ResponseBody> downloadImg(@Url String imgUrl);

    @GET("eat/cookbook/listclass")
    Observable<Response<String>> getClassList();

    @GET("trapi/user/getaccountinfo")
    Observable<Response<AccountEntity>> getAccountInfo();
}
