package com.example.administrator.mytestallhere.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;

import java.util.ArrayList;

public class AnimationActivity extends Activity {
    ImageView mImageView;
    Button mbtnAnimal;
    AnimationDrawable mAnimationDrawable;
    ArrayList<String> mDatas = new ArrayList<>();
    ListView mListView;
    AnimationSet mAnimSetFromCode;
    Animation mAnimFromXml;
    LayoutAnimationController mLayoutAnimController;
    ValueAnimator mValueAnimator;
    AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mbtnAnimal = (Button) findViewById(R.id.btn_anim);
        mImageView = (ImageView) findViewById(R.id.iv_anim_list);
        mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
        mListView = (ListView) findViewById(R.id.lv_listView);
        initListView();
        grantAnimationSetFromCode();
        grantAnimationSetFromXml();
        grantLayoutAnim();
        grantValueAnimator();
        grantAnimatorSetFromCode();
        //grantAnimatorSetFromXml();
        mbtnAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xml
                //mbtnAnimal.startAnimation(mAnimFromXml);

                //code
                mbtnAnimal.startAnimation(mAnimSetFromCode);
                mAnimationDrawable.start();
            }
        });
    }

    private void grantAnimatorSetFromCode() {
        mAnimatorSet = new AnimatorSet();
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(mbtnAnimal,"scaleX",1,1.5f);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);
        scaleX.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator scaleY=ObjectAnimator.ofFloat(mbtnAnimal,"scaleY",1,0.5f);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);
        scaleY.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorSet.playTogether(scaleX
                ,scaleY);
        mAnimatorSet.setDuration(3000).start();

    }
    private void grantAnimatorSetFromXml(){
        mAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.property_animator);
        mAnimatorSet.setTarget(mbtnAnimal);
        mAnimatorSet.start();
    }
    private void grantValueAnimator() {
        mValueAnimator = ObjectAnimator.ofInt(mbtnAnimal, "backgroundColor",/*Red*/0xffff8080,/*Blue*/0xff8080ff);
        mValueAnimator.setDuration(500);//设置间隔
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置重复次数
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);//设置重复模式
        mValueAnimator.setEvaluator(new ArgbEvaluator());//设置估值器
        mValueAnimator.start();
    }

    private void grantLayoutAnim() {
        //注意这里不是xml的LayoutAnimation！！！！！这是错的！！！
        //Animation layoutAnim = AnimationUtils.loadAnimation(this, R.anim.layoutanimation);
        Animation layoutAnim = AnimationUtils.loadAnimation(this, R.anim.animforlearning);
        mLayoutAnimController = new LayoutAnimationController(layoutAnim);
        mLayoutAnimController.setDelay(0.5f);
        mLayoutAnimController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mListView.setLayoutAnimation(mLayoutAnimController);
    }

    private void grantAnimationSetFromXml() {
        //xml
        mAnimFromXml = AnimationUtils.loadAnimation(this, R.anim.animforlearning);

    }

    private void grantAnimationSetFromCode() {
        //code
        mAnimSetFromCode = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 200);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ;
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ;
        translateAnimation.setDuration(3000);
        scaleAnimation.setDuration(3000);
        alphaAnimation.setDuration(3000);
        rotateAnimation.setDuration(3000);
        mAnimSetFromCode.addAnimation(translateAnimation);
        mAnimSetFromCode.addAnimation(scaleAnimation);
        mAnimSetFromCode.addAnimation(alphaAnimation);
        mAnimSetFromCode.addAnimation(rotateAnimation);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enteranim, R.anim.exitanim);
    }

    private void initListView() {
        for (int i = 0; i < 50; i++) {
            mDatas.add("this is item: " + i);
        }
        mListView.setAdapter(new BaseAdapter() {
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
                Holder holder;
                if (convertView == null) {
                    TextView tv = new TextView(AnimationActivity.this);
                    holder = new Holder();
                    holder.tv = tv;
                    tv.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                holder.tv.setText(mDatas.get(position));
                return holder.tv;
            }

            class Holder {
                TextView tv;
            }
        });
    }
}
