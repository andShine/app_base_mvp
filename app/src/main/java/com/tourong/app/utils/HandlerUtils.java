package com.tourong.app.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Handler工具类
 * Created by liu on 2017/9/15.
 */
public class HandlerUtils {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public static void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static void removeRunable(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }
}