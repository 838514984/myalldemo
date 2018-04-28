package com.example.administrator.mytestallhere;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest.ActivityAAA;
import com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest.ControllerActivity;
import com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest.TransitionManagerActivity;
import com.example.administrator.mytestallhere.aidl.AIDLActivity;
import com.example.administrator.mytestallhere.aidl.MessengeActivity;
import com.example.administrator.mytestallhere.alarmwhenlocked.PrepareActivity;
import com.example.administrator.mytestallhere.animation.AnimationActivity;
import com.example.administrator.mytestallhere.annotationTest.Annotation.AnnotationActivity;
import com.example.administrator.mytestallhere.buildConfigTest.BuildConfigActivity;
import com.example.administrator.mytestallhere.butterknife.ButterKnifeActivity;
import com.example.administrator.mytestallhere.cameraTest.CameraPreViewTestActivity;
import com.example.administrator.mytestallhere.customdialog.CustomDialogActivity;
import com.example.administrator.mytestallhere.edittextKeyboardAction.EditTextKeyboaedActivity;
import com.example.administrator.mytestallhere.fillsystem.FillSystemActivity;
import com.example.administrator.mytestallhere.fragment.FragmentJumpActivity;
import com.example.administrator.mytestallhere.glideGetBitmap.GlideGetBitmapActivity;
import com.example.administrator.mytestallhere.http.okhttptest.OkHttpActivity;
import com.example.administrator.mytestallhere.http.retrofit.RetrofitRxjavaActivity;
import com.example.administrator.mytestallhere.learn.LearningListActivity;
import com.example.administrator.mytestallhere.memoryleak.MemoryActivityTest;
import com.example.administrator.mytestallhere.multiApksBuildTest.MuiltyApksbuildTestActivity;
import com.example.administrator.mytestallhere.mvpDemo.MVPLoginActivity;
import com.example.administrator.mytestallhere.myJecenterDependence.MyJecenterDependence;
import com.example.administrator.mytestallhere.placeholderview.PlaceholderViewActivityList;
import com.example.administrator.mytestallhere.provider.ProviderActivity;
import com.example.administrator.mytestallhere.remoteView.CustomNotificationActivity;
import com.example.administrator.mytestallhere.rxJava.TestRxJavaActivity;
import com.example.administrator.mytestallhere.rxbinding.RxBinDingActivity;
import com.example.administrator.mytestallhere.selectImgAndCrop.SelectImgAndCropActivity;
import com.example.administrator.mytestallhere.smsVerfiry.SmsVerfiryActivity;
import com.example.administrator.mytestallhere.startActivityFromBrowseTest.StartActivityFromBrowse;
import com.example.administrator.mytestallhere.testDependences.DependencesActivity;
import com.example.administrator.mytestallhere.testRelativeoutPaddingValueable.RelativeLayoutpadding30dp;
import com.example.administrator.mytestallhere.testStatusBar.TestStatusBar;
import com.example.administrator.mytestallhere.testmaterial_calendarview.MaterialCalendarViewActivity;
import com.example.administrator.mytestallhere.themeswitch.ThemeSwitchActivity;
import com.example.administrator.mytestallhere.time_data_orotherpicker.TestPickActivity;
import com.example.util.Logger;
import com.example.administrator.mytestallhere.view.CircleViewActivity;
import com.example.administrator.mytestallhere.view.ViewMoveActivity;
import com.example.administrator.mytestallhere.viewpagerTest.ViewPagerActivity;
import com.example.administrator.mytestallhere.webviewTest.WebViewAndJsInvokeMethodActivity;
import com.example.administrator.mytestallhere.webviewTest.WebViewTestActivity;
import com.example.administrator.mytestallhere.webviewTest.WebViewVideoActivity;
import com.example.administrator.mytestallhere.window.windowManager.AddWindowActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    BaseAdapter adapter;
    public static List<String> mDatas = new ArrayList<String>() {{
        add("RelativeLayoutPaddingTest");
        add("StatusBarTest");
        add("RxBindingTest");
        add("RxJavaTest");
        add("ButtferKnifeTest");
        add("Camera SurfaceViewTest");
        add("SelectImg&cropTest");
        add("Picker(Time Number..)Test");
        add("MaterialCalendarViewTest");
        add("AddDependencesTest");
        add("GlideGetBitmapTest");
        add("MultiApksTest");
        add("BuildConfigAdditionTest");
        add("ViewpagerTest");
        add("overridePendingTransition Activity Test");
        add("Transition Activity Test");
        add("TransitionManagerActivityTest");
        add("StartActivityFromBrowsTest");
        add("MemoryLeakTest");
        add("MyJecenterDependence");
        add("WebView Test");
        add("WebViewVideoPlayer Test");
        add("Android&JsInvoke");
        add("CustomAnnotation");
        add("learningList");
        add("customDialogue");
        add("alarmWhenLocked");
        add("EditTextKeyBoardAction");
        add("AIDL Test");
        add("Messenge Test");
        add("Provider");
        add("moveView");
        add("circleViewActivity");
        add("CustomNotification");
        add("Animation");
        add("addWindow");
        add("retrofit&Rxjava");
        add("okhttptest");
        add("fragment jump");
        add("switch theme");
        add("MVPLogin test");
        add("place holder view");
        add("fillsys");
        add("短信验证码 43");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.error("onCreate");

        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        RxPermissions rxPermissions=new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                //Toast.makeText(BaseActivity.this,aBoolean?"授权成功":"授权失败",0).show();
            }
        });

    }

    @Override
    protected void onResume() {
        Logger.error("onResume");
        super.onResume();
        Intent intent=new Intent();
        intent.setAction("xiixi");
        sendBroadcast(intent);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Logger.error("onPostCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.error("onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.error("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.error("onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.error("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.error("onDestroy");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, RelativeLayoutpadding30dp.class));
                //mSwipeBackHelper.forward(RelativeLayoutpadding30dp.class);
                break;
            case 1:
                startActivity(new Intent(this, TestStatusBar.class));
                break;
            case 2:
                startActivity(new Intent(this, RxBinDingActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, TestRxJavaActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ButterKnifeActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, CameraPreViewTestActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, SelectImgAndCropActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, TestPickActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, MaterialCalendarViewActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, DependencesActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, GlideGetBitmapActivity.class));
                break;
            case 11:
                startActivity(new Intent(this, MuiltyApksbuildTestActivity.class));
                break;
            case 12:
                startActivity(new Intent(this, BuildConfigActivity.class));
                break;
            case 13:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case 14:
                startActivity(new Intent(this, ActivityAAA.class));
                break;
            case 15:
                startActivity(new Intent(this, ControllerActivity.class));
                break;
            case 16:
                startActivity(new Intent(this, TransitionManagerActivity.class));
                break;
            case 17:
                startActivity(new Intent(this, StartActivityFromBrowse.class));
                break;
            case 18:
                startActivity(new Intent(this, MemoryActivityTest.class));
                break;
            case 19:
                startActivity(new Intent(this, MyJecenterDependence.class));
                break;
            case 20:
                startActivity(new Intent(this, WebViewTestActivity.class));
                break;
            case 21:
                startActivity(new Intent(this, WebViewVideoActivity.class));
                break;
            case 22:
                startActivity(new Intent(this, WebViewAndJsInvokeMethodActivity.class));
                break;
            case 23:
                startActivity(new Intent(this, AnnotationActivity.class));
                break;
            case 24:
                startActivity(new Intent(this,LearningListActivity.class));
                break;
            case 25:
                startActivity(new Intent(this, CustomDialogActivity.class));
                break;
            case 26:
                Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(MainActivity.this, PrepareActivity.class));
                    }
                });
                break;
            case 27:
                startActivity(new Intent(this, EditTextKeyboaedActivity.class));
                break;
            case 28:
                startActivity(new Intent(this, AIDLActivity.class));
                break;
            case 29:
                startActivity(new Intent(this, MessengeActivity.class));
                break;
            case 30:
                startActivity(new Intent(this, ProviderActivity.class));
                break;
            case 31:
                startActivity(new Intent(this, ViewMoveActivity.class));
                break;
            case 32:
                startActivity(new Intent(this, CircleViewActivity.class));
                break;
            case 33:
                startActivity(new Intent(this, CustomNotificationActivity.class));
                break;
            case 34:
                startActivity(new Intent(this, AnimationActivity.class));
                overridePendingTransition(R.anim.enteranim,R.anim.exitanim);
                break;
            case 35:
                startActivity(new Intent(this, AddWindowActivity.class));
                break;
            case 36:
                startActivity(new Intent(this, RetrofitRxjavaActivity.class));
                break;
            case 37:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
            case 38:
                startActivity(new Intent(this, FragmentJumpActivity.class));
                break;
            case 39:
                startActivity(new Intent(this, ThemeSwitchActivity.class));
                break;
            case 40:
                startActivity(new Intent(this, MVPLoginActivity.class));
                break;
            case 41:
                startActivity(new Intent(this, PlaceholderViewActivityList.class));
                break;
            case 42:
                FillSystemActivity.startActivity(this);
                break;
            case 43:
                SmsVerfiryActivity.StartActivity(this);
                break;
        }
    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                TextView tv = new TextView(MainActivity.this);
                tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setHeight(MainActivity.this.dp2px(48));
                //ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,MainActivity.this.dp2px(48));
                //tv.setLayoutParams(layoutParams);
                tv.setGravity(Gravity.CENTER);
                convertView = tv;
            }

            ((TextView) convertView).setText(mDatas.get(position));

            return convertView;
        }
    }


    public int dp2px(int dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

}
