package com.example.administrator.mytestallhere.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class GetAssetsFiles {
    /**
     * 获取assets目录下的图片
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * 获取assets目录下的单个文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static File getFileFromAssetsFile(Context context, String fileName) {//这种方式不能用，只能用于webview加载，直接取路径是不行的

        String path = "file:///android_asset/" + fileName;
        File file = new File(path);
        return file;

    }

    /**
     * 获取所有文件
     *
     * @param path
     * @return
     */
    public static String[] getfilesFromAssets(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String str : files) {
            //LogUtils.logInfoStar(str);
        }
        return files;

    }

    /**
     * 将assets下的文件放到sd指定目录下
     *
     * @param context    上下文
     * @param assetsPath assets下的路径
     * @param sdCardPath sd卡的路径
     */
    public static void putAssetsToSDCard(Context context, String assetsPath,
                                         String sdCardPath) {
        try {
            String mString[] = context.getAssets().list(assetsPath);
            if (mString.length == 0) { // 说明assetsPath为空,或者assetsPath是一个文件
                InputStream mIs = context.getAssets().open(assetsPath); // 读取流
                byte[] mByte = new byte[1024];
                int bt = 0;
                File file = new File(sdCardPath + File.separator
                        + assetsPath.substring(assetsPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.createNewFile(); // 创建文件
                } else {
                    return;//已经存在直接退出
                }
                FileOutputStream fos = new FileOutputStream(file); // 写入流
                while ((bt = mIs.read(mByte)) != -1) { // assets为文件,从文件中读取流
                    fos.write(mByte, 0, bt);// 写入流到文件中
                }
                fos.flush();// 刷新缓冲区
                mIs.close();// 关闭读取流
                fos.close();// 关闭写入流

            } else { // 当mString长度大于0,说明其为文件夹
                sdCardPath = sdCardPath + File.separator + assetsPath;
                File file = new File(sdCardPath);
                if (!file.exists())
                    file.mkdirs(); // 在sd下创建目录
                for (String stringFile : mString) { // 进行递归
                    putAssetsToSDCard(context, assetsPath + File.separator
                            + stringFile, sdCardPath);
                }
            }
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param activity
     * @param fileName 不需要带路径，eg（"xxx.jpg"）
     */
    public static void putFileToSdCard(final Activity activity, final String fileName)  {
        RxPermissions rxPermissions=new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                AssetManager manager=activity.getAssets();

                try {
                    InputStream inputStream= manager.open(fileName);
                    File outputdir=new File(Environment.getExternalStorageDirectory(),"myHtml");
                    Logger.error("outputfile: "+outputdir);
                    if (!outputdir.exists())
                        outputdir.mkdir();
                    File outputFile=new File(outputdir,fileName);
                    FileOutputStream outputStream=new FileOutputStream(outputFile);
                    byte[] bytes=new byte[1024];
                    int len=0;
                    while((len=inputStream.read(bytes))!=-1){
                        outputStream.write(bytes,0,len);
                    }
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }

}
