package com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ControllerActivity extends BaseActivity {
    @BindView(R.id.tv_1)
    TextView mTvFade;
    @BindView(R.id.tv_2)
    TextView mTvSlide;
    @BindView(R.id.tv_3)
    TextView mTvExplode;
    private TransitionType type;

    enum TransitionType {FADE, SLIDE, EXPLODE}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tv_1)
    public void fade() {
        //getWindow().setEnterTransition(new Fade());
        setTransition(Fade.class);
        type = TransitionType.FADE;
        startActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tv_2)
    public void slide() {
        setTransition(Slide.class);
        type = TransitionType.SLIDE;
        startActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tv_3)
    public void Explode() {
        setTransition(Explode.class);
        type = TransitionType.EXPLODE;
        startActivity();
    }

    private void startActivity() {
        Intent i = new Intent(this, TagetActivity.class);
        i.putExtra("type", type);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(i, activityOptionsCompat.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTransition(Class transition) {
        try {
            Transition transition1 = (Transition) transition.newInstance();
            Transition transition2 = (Transition) transition.newInstance();
            transition1.setDuration(3000);
            transition2.setDuration(3000);
            getWindow().setExitTransition(transition1);
            getWindow().setReenterTransition(transition2);
            getWindow().setAllowEnterTransitionOverlap(false);
            getWindow().setAllowReturnTransitionOverlap(false);
            getWindow().setAllowEnterTransitionOverlap(false);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
