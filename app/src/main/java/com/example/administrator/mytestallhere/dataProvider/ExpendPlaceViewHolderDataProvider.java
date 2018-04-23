package com.example.administrator.mytestallhere.dataProvider;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.bean.placeholderview.Child;
import com.example.administrator.mytestallhere.bean.placeholderview.Parent;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

@Singleton
public class ExpendPlaceViewHolderDataProvider {
    @Inject
    public ExpendPlaceViewHolderDataProvider() {
    }

    public ArrayList<Parent> getExpendPlaceParent() {
        ArrayList<Parent> parents = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            parents.add(new Parent("this is " + i + " parent"));
        }
        return parents;
    }

    public ArrayList<Child> getChild() {
        int count =new Random().nextInt(5)+1;
        ArrayList<Child> children = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                children.add(new Child(R.mipmap.two, "child index " + i, "this is the " + i + " child"));
            } else {
                children.add(new Child(R.mipmap.mei_1, "child index " + i, "this is the " + i + " child"));
            }
        }
        return children;
    }

}
