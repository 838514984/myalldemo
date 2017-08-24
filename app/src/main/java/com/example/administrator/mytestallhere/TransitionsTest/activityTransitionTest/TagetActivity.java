package com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

public class TagetActivity extends BaseActivity {
    private ControllerActivity.TransitionType type;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taget);
        type= (ControllerActivity.TransitionType) getIntent().getSerializableExtra("type");
        if (type== ControllerActivity.TransitionType.FADE){
            setTransition(Fade.class);
        }
        if (type== ControllerActivity.TransitionType.SLIDE){
            setTransition(Slide.class);
        }
        if (type== ControllerActivity.TransitionType.EXPLODE){
            setTransition(Explode.class);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        finishAfterTransition();
        //finish();//注意这里要用上面那句
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTransition(Class transition) {
        try {
            Transition transition1 = (Transition) transition.newInstance();
            Transition transition2 = (Transition) transition.newInstance();
            transition1.setDuration(3000);
            transition2.setDuration(3000);
            getWindow().setEnterTransition(transition1);
            getWindow().setReturnTransition(transition2);
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
