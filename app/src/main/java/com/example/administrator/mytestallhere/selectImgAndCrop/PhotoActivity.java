package com.example.administrator.mytestallhere.selectImgAndCrop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.administrator.mytestallhere.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class PhotoActivity extends Activity {
    public static void startActivity(Context context, int type,
                                     String className,int outPutSize)
    {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("className", className);
        intent.putExtra("outPutSize",outPutSize);
        context.startActivity(intent);
    }
    public int outPutSize;
    public static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    public static final int PHOTO_REQUEST_GALLERY = 2;
    public static final int PHOTO_REQUEST_CUT = 3;
    private int type;
    private String className;
    private String tempFileFolderpath = Environment.getExternalStorageDirectory() + "/photo";
    private File tempFile = new File(Environment.getExternalStorageDirectory() + "/photo", getPhotoFileName());
    private File tempFileAfterResize = new File(Environment.getExternalStorageDirectory() + "/photo", "resize"+getPhotoFileName());

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        resloveIntent(getIntent());
        File file = new File(Environment.getExternalStorageDirectory() + "/photo");
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        init();
    }
    /**
     * 处理参数
     */

    public void resloveIntent(Intent intent)
    {
        type = intent.getIntExtra("type", -1);
        className = intent.getStringExtra("className");
        outPutSize=intent.getIntExtra("outPutSize",dp2px(200));
    }

    private void init() {

        if (type == PHOTO_REQUEST_TAKEPHOTO)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
            startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                if (tempFile.length() == 0)
                {
                    finish();
                    return;
                }

                startPhotoZoom(Uri.fromFile(tempFile), outPutSize);
                break;

            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), outPutSize);
                else
                    finish();
                break;

            case PHOTO_REQUEST_CUT:
                if (tempFileAfterResize.length() != 0)
                    returnResult();
                else
                    finish();
                break;

            default:
                finish();
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    private void returnResult(){
        PhotoEvent photoEvent=new PhotoEvent();
        photoEvent.filePath=tempFileAfterResize.getAbsolutePath();
        EventBus.getDefault().post(photoEvent);
        finish();
    }


    private void startPhotoZoom(Uri uri, int size) {
        //删除之前的resize文件
        /*File folder = new File(tempFileFolderpath);
        File[] files = folder.listFiles();
        for(File file:files){
            if(file.getName().startsWith("resize")) {
                file.delete();
            }
        }*/

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFileAfterResize));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    //自己需要的操作
   /* private void returnResult() {
        EventBus.getDefault().post(
                new PhotoEvent(className, tempFileAfterResize.getAbsolutePath()));

        if (tempFile.exists())
            tempFile.delete();

        finish();
    }*/

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    public int dp2px(int dp){
           return (int) (getResources().getDisplayMetrics().scaledDensity*dp);
       }

}
