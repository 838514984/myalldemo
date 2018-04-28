package com.example.administrator.mytestallhere.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.example.util.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class ListFragment extends Fragment {
    View mContentView;
    ListView mListView;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position, Holder holder);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.error("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.error("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.error("onCreateView");
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_list, null);
            mListView = (ListView) mContentView.findViewById(R.id.lv);
            mListView.setAdapter(new MyAdapter());
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClickListener(view,position,null);
                    }
                }
            });
        }
//        if (mContentView == null) {
//            mContentView = inflater.inflate(R.layout.item_fragment_list, null);
//            mContentView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onItemClickListener != null)
//                        onItemClickListener.onItemClickListener(mContentView, 0, null);
//                }
//            });
//        }
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.error("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Logger.error("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Logger.error("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.error("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Logger.error("onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Logger.error("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Logger.error("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Logger.error("onDetach");
        super.onDetach();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MyAdapter extends BaseAdapter {
        ArrayList<String> mDatas;

        public MyAdapter() {
            mDatas = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                mDatas.add("这是第 " + (i + 1) + " 个");
            }
        }

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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_fragment_list, null);
                holder = new Holder();
                holder.rootView = convertView;
                holder.cardView = (CardView) convertView.findViewById(R.id.cardview);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.content = (TextView) convertView.findViewById(R.id.tv_content);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.title.setTransitionName("shareTitle" + position);
                    holder.img.setTransitionName("shareImg" + position);
                    holder.content.setTransitionName("shareContent" + position);
                    holder.rootView.setTransitionName("shareRootView"+position);
                }
                final View finalConvertView = convertView;
                final Holder finalHolder = holder;
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (onItemClickListener != null) {
//                            onItemClickListener.onItemClickListener(finalConvertView, position, finalHolder);
//                        }
//                    }
//                });
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.content.setText(mDatas.get(position));
            return holder.rootView;
        }


    }

    public static class Holder {
        public TextView title;
        public TextView content;
        public ImageView img;
        public CardView cardView;
        public View rootView;
    }
}
