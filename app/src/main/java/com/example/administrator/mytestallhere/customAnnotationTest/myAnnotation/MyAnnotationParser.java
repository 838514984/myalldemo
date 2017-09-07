package com.example.administrator.mytestallhere.customAnnotationTest.myAnnotation;

import android.app.Activity;
import android.view.View;

import com.example.administrator.mytestallhere.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class MyAnnotationParser {
    public static void bind(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        for (Field field:fields){
            if (field.isAnnotationPresent( MyBindView.class)){
              MyBindView myBindView = field.getAnnotation(MyBindView.class);
                int id=myBindView.value();
                View view=((Activity)o).findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(o,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
