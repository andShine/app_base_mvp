package com.tourong.app.net.exception;

import android.net.ParseException;
import org.json.JSONException;
import retrofit2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * 本地异常处理。包括解析异常等其他异常
 */

public class CustomException {

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof com.alibaba.fastjson.JSONException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            ex = new ApiException(PARSE_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof ConnectException) {
            //网络错误
            ex = new ApiException(NETWORK_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            //连接错误
            ex = new ApiException(NETWORK_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof HttpException) {
            ex = new ApiException(HTTP_ERROR, e.getMessage());
            return ex;
        } else {
            //未知错误
            ex = new ApiException(UNKNOWN, e.getMessage());
            return ex;
        }
    }
}
