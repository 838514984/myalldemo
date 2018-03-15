package com.example.administrator.mytestallhere.annotationTest.Annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class MyAnnotationParser {
    public static void bindView(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyBindView.class)) {
                MyBindView myBindView = field.getAnnotation(MyBindView.class);
                int id = myBindView.value();
                View view = ((Activity) o).findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(o, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bindContentView(Object o) {
        Class c = o.getClass();
        if (c.isAnnotationPresent(MyBindLayout.class)) {
            MyBindLayout myBindView = (MyBindLayout) c.getAnnotation(MyBindLayout.class);
            int layoutId = myBindView.layoutId();
            //((Activity)o).setContentView(layoutId);
            try {
                //Method method=c.getDeclaredMethod("setContentView",int.class);
                Method method = c.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(o, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

}
