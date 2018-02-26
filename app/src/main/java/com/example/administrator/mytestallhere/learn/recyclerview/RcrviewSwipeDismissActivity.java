package com.example.administrator.mytestallhere.learn.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.MainActivity;
import com.example.administrator.mytestallhere.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RcrviewSwipeDismissActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    List<String> mDatas=new ArrayList<String>(){{addAll(MainActivity.mDatas);}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter());
        mRecyclerView.setOnTouchListener(new SwipeDismissRecyclerViewTouchListener(new SwipeDismissRecyclerViewTouchListener.Builder(mRecyclerView, new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
            @Override
            public boolean canDismiss(int position) {
                return true;
            }

            @Override
            public void onDismiss(View view) {
               int position= mRecyclerView.getChildAdapterPosition(view);
                mDatas.remove(position);
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }).setIsVertical(false)));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rcrview_swipe_dismiss;
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH>{


        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MyVH(LayoutInflater.from(RcrviewSwipeDismissActivity.this).inflate(R.layout.item_rcv,null));
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position) {
            ((TextView)holder.contentView.findViewById(R.id.tv)).setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

         class MyVH extends BaseRecyclerViewAdapter.VH{
            View contentView;
            public MyVH(View itemView) {
                super(itemView);
                contentView=itemView;
            }
        }
    }
}
