package com.example;

import sun.rmi.runtime.Log;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class ObjectReference {
    String name ;
    public static void main(String[] sss){
        ObjectReference c = new ObjectReference();
        c.name = "third";
        ObjectReference a = new ObjectReference();
        a.name = "first";
        ObjectReference b = a;
        b.name = "second";
        a = c;
        System.out.print("a.name: "+a.name+"\nb.name: "+b.name);
    }
}
