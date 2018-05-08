package com.example.administrator.mytestallhere.tbs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.placeholderview.ExpendPlaceHoldViewActivity;
import com.example.administrator.mytestallhere.placeholderview.PlaceholderViewActivityList;
import com.example.administrator.mytestallhere.placeholderview.SwipePlaceHolderViewLikeTanTan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TbslistActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static void startActivity(Context context){
        Intent intent = new Intent(context,TbslistActivity.class);
        context.startActivity(intent);
    }


    @BindView(R.id.lv)
    ListView listView;
    ArrayList<String> mdatas = new ArrayList<String>() {{
        add("BrowserActivity");
        add("FilechooserActivity");
        add("fullScreenActivity");
        add("tabMainActivity");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mdatas.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(TbslistActivity.this);
                textView.setText(mdatas.get(position));
                return textView;
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_placeholder_view_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            startActivity(new Intent(this, BrowserActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(this, FilechooserActivity.class));
        } else if (position == 2) {
            startActivity(new Intent(this, FullScreenActivity.class));
        } else if (position == 3) {
            startActivity(new Intent(this, TabMainActivity.class));
        }
    }
}
