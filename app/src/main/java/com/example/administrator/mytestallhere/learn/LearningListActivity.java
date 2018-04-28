package com.example.administrator.mytestallhere.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.learn.custom.CustomViewLearningActivity;
import com.example.administrator.mytestallhere.learn.recyclerview.RcrviewSwipeDismissActivity;
import com.example.administrator.mytestallhere.placeholderview.ExpendPlaceHoldViewActivity;
import com.example.administrator.mytestallhere.placeholderview.SwipePlaceHolderViewLikeTanTan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearningListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.lv)
    ListView listView;
    ArrayList<String> mdatas = new ArrayList<String>(){{
        add("customView");
        add("recyclerview");
    }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_list);
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
                TextView textView = new TextView(LearningListActivity.this);
                textView.setText(mdatas.get(position));
                return textView;
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            startActivity(new Intent(this,CustomViewLearningActivity.class));
        }else if (position == 1){
            startActivity(new Intent(this,RcrviewSwipeDismissActivity.class));
        }
    }
}
