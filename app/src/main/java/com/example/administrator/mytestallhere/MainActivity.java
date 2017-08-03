package com.example.administrator.mytestallhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.butterknife.ButterKnifeActivity;
import com.example.administrator.mytestallhere.cameraTest.CameraPreViewTestActivity;
import com.example.administrator.mytestallhere.rxJava.TestRxJavaActivity;
import com.example.administrator.mytestallhere.rxbinding.RxBinDingActivity;
import com.example.administrator.mytestallhere.selectImgAndCrop.SelectImgAndCropActivity;
import com.example.administrator.mytestallhere.statusutil.StatusBarUtil;
import com.example.administrator.mytestallhere.testRelativeoutPaddingValueable.RelativeLayoutpadding30dp;
import com.example.administrator.mytestallhere.testStatusBar.TestStatusBar;
import com.example.administrator.mytestallhere.testmaterial_calendarview.MaterialCalendarViewActivity;
import com.example.administrator.mytestallhere.time_data_orotherpicker.TestPickActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    BaseAdapter adapter;
    List<String> mDatas = new ArrayList<String>() {{
        add("TestRelativeLayoutPadding");
        add("TestStatusBar");
        add("TestRxBinding");
        add("TestRxJava");
        add("TestButtferKnife");
        add("TestCamera SurfaceView");
        add("TestSelectImg&crop");
        add("TestPicker(Time Number..)");
        add("TestMaterialCalendarView");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this,lv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, RelativeLayoutpadding30dp.class));
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
