package com.example.administrator.mytestallhere.viewpagerTest;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.mytestallhere.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {
    @BindView(R.id.vp_1)
    ViewPager vp_1;
    @BindView(R.id.vp_2)
    VerticalViewPager vp_2;
    PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {

                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                int drawable=-1;
                if (position==0){
                    drawable=R.mipmap.mei_1;
                }
                if (position==1){
                    drawable=R.mipmap.mei_2;
                }
                if (position==2){
                    drawable=R.mipmap.mei_3;
                }
                ImageView img=new ImageView(ViewPagerActivity.this);
                img.setImageResource(drawable);
                container.addView(img);
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if(((View)object).getParent()!=null){
                    container.removeView((View) object);
                }
            }
        };
        vp_1.setAdapter(adapter);
        vp_1.setPageTransformer(true, new ZoomOutPageTransformer());
        vp_2.setAdapter(adapter);
    }
}
