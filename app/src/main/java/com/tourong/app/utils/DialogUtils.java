package com.tourong.app.utils;

import android.content.Context;
import android.text.TextUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public class DialogUtils {

    private static final Long DELAY_DISMISS = (long) (1 * 1000);

    private static volatile DialogUtils instance;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (null == instance) {
            synchronized (DialogUtils.class) {
                if (null == instance) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    private QMUITipDialog tipDialog;

    // 显示加载框
    public void showLoading(Context context) {
        dismissDialog();
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("请稍等")
                .create();
        tipDialog.setCancelable(true);
        tipDialog.setCanceledOnTouchOutside(false);
        tipDialog.show();
    }

    // 显示加载框
    public void showLoading(Context context, String tips) {
        dismissDialog();
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(TextUtils.isEmpty(tips) ? "请稍等" : tips)
                .create();
        tipDialog.setCancelable(true);
        tipDialog.setCanceledOnTouchOutside(false);
        tipDialog.show();
    }

    // 隐藏加载框
    public void hideLoading() {
        dismissDialog();
    }

    private void dismissDialog() {
        if (null != tipDialog) {
            tipDialog.dismiss();
        }
    }

    public void showTip(Context context, int iconType, String tipWord) {
        dismissDialog();
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(iconType)
                .setTipWord(tipWord)
                .create();
        tipDialog.setCancelable(true);
        tipDialog.show();
        HandlerUtils.runOnUiThreadDelay(() -> {
            if (null != tipDialog) {
                tipDialog.dismiss();
            }
        }, DELAY_DISMISS);
    }

    public void showTip(Context context, String tipWord) {
        dismissDialog();
        tipDialog = new QMUITipDialog.Builder(context)
                .setTipWord(tipWord)
                .create();
        tipDialog.setCancelable(true);
        tipDialog.show();
        HandlerUtils.runOnUiThreadDelay(() -> {
            if (null != tipDialog) {
                tipDialog.dismiss();
            }
        }, DELAY_DISMISS);
    }

    // 显示成功提示框
    public void showTipSuccess(Context context, String tipWord) {
        showTip(context, QMUITipDialog.Builder.ICON_TYPE_SUCCESS, tipWord);
    }

    // 显示失败提示框
    public void showTipFail(Context context, String tipWord) {
        showTip(context, QMUITipDialog.Builder.ICON_TYPE_FAIL, tipWord);
    }

    // 显示信息提示框
    public void showTipInfo(Context context, String tipWord) {
        showTip(context, QMUITipDialog.Builder.ICON_TYPE_INFO, tipWord);
    }
}
