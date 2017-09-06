package com.example.administrator.mytestallhere.selectImgAndCrop;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class PhotoActivity extends BaseActivity {
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
    private File tempFile = new File(Environment.getExternalStorageDirectory()+"/photo", getPhotoFileName());
    private File tempFileAfterResize = new File(Environment.getExternalStorageDirectory() + "/photo", "resize"+getPhotoFileName());

    private String mPackageName;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resloveIntent(getIntent());
        File file = new File(Environment.getExternalStorageDirectory() + "/photo");
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        mPackageName=getPackageName();
        Logger.error(mPackageName);

        init();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
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
            //7.0以上必须用ContentProvider
            Uri uri= FileProvider.getUriForFile(this,mPackageName+".fileprovider",tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            //下面两句也是
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            // fix java.lang.SecurityException: Permission Denial: opening provider android.support.v4.content.FileProvider from ProcessRecord{42725078 24872:com.android.camera/u0a14} (pid=24872, uid=10014) that is not exported from uid 10310
            List<ResolveInfo> resInfoList= getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, uri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
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
                Logger.error("PHOTO_REQUEST_TAKEPHOTO "+getUriFromFile(tempFile,null));
                startPhotoZoom(getUriFromFile(tempFile,null), outPutSize);
                break;

            case PHOTO_REQUEST_GALLERY:
                if (data!=null){
                    if (data.getData()!=null)
                        Logger.error("PHOTO_REQUEST_GALLERY "+data.getData().toString());
                }
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFileAfterResize));//这里好像只能用Uri.fromFile
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
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


    private Uri getUriFromFile(File file,Intent intent){
        if (intent!=null){
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        return FileProvider.getUriForFile(this,mPackageName+".fileprovider",file);
    }

}
