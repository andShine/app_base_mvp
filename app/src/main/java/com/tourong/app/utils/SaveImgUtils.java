package com.tourong.app.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import com.blankj.utilcode.util.ToastUtils;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.tourong.app.net.NetWorkManager;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存图片工具类
 * Talk is cheap.Show me the code.
 * 2019-04-19 15:57
 * Created by Mr.Liu
 */
public class SaveImgUtils {

    /**
     * 指定线程下载文件(异步)，非阻塞式下载
     *
     * @param url        图片url
     * @param floderPath 下载文件保存目录
     * @param fileName   文件名称(不带后缀)
     */
    public static void downloadImg(Context context, String url, final String floderPath, final String fileName) {

        // 检查权限
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        downloadImgReal(context, url, floderPath, fileName);
                    }

                    @Override
                    public void onPermissionDenied(Permission[] refusedPermissions) {
                        ToastUtils.showShort("请授予存储权限后保存图片");
                    }
                });

    }

    private static void downloadImgReal(Context context, String url, String floderPath, String fileName) {
        NetWorkManager.getRequest()
                .downloadImg(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Bitmap bitmap = null;
                        byte[] bys;
                        try {
                            bys = responseBody.bytes();
                            bitmap = BitmapFactory.decodeByteArray(bys, 0, bys.length);
                            try {
                                saveImg(context, bitmap, floderPath, fileName);
                                // String savePath = savePatch + File.separator + fileName + ".jpg";
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //你的处理
                        ToastUtils.showShort("保存失败");
                    }

                    @Override
                    public void onComplete() {
                        //你的处理
                    }
                });
    }

    /**
     * 保存图片到SD卡
     *
     * @param bm         图片bitmap对象
     * @param floderPath 下载文件保存目录
     * @param fileName   文件名称(不带后缀)
     */
    private static void saveImg(Context context, Bitmap bm, String floderPath, String fileName) throws IOException {
        //如果不保存在sd下面下面这几行可以不加
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ToastUtils.showShort("SD卡异常");
            return;
        }

        File folder = new File(floderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String savePath = folder.getPath() + File.separator + fileName + ".jpg";
        File file = new File(savePath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bm.compress(Bitmap.CompressFormat.JPEG, 99, bos);
        ToastUtils.showShort("已保存至" + floderPath + "目录下");

        // 通知更新图库
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);

        bos.flush();
        bos.close();
    }
}
