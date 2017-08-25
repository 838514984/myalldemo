package com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TransitionManagerActivity extends BaseActivity {
    @BindView(R.id.rl)
    ViewGroup rootView;
    TransitionManager transitionManager;
    Scene scene1;
    Scene scene2;
    Scene scene3;
    Scene scene4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_manager);

        transitionManager=new TransitionManager();


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        scene1=Scene.getSceneForLayout(rootView,R.layout.activity_animations_scene1,this);
        scene2=Scene.getSceneForLayout(rootView,R.layout.activity_animations_scene2,this);
        scene3=Scene.getSceneForLayout(rootView,R.layout.activity_animations_scene3,this);
        scene4=Scene.getSceneForLayout(rootView,R.layout.activity_animations_scene4,this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_scene1,R.id.btn_scene2,R.id.btn_scene3,R.id.btn_scene4})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_scene1:
                transitionManager.go(scene1,new ChangeBounds());
                break;
            case R.id.btn_scene2:
                transitionManager.go(scene2,new Explode());
                break;
            case R.id.btn_scene3:
                transitionManager.go(scene3,new Slide());
                break;
            case R.id.btn_scene4:
                transitionManager.go(scene4,new Fade());
                break;
        }
    }
}
