package com.example.finaltest;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class FinalTest {
    int a;
    public FinalTest(int a){
        this.a = a;
    }
    public static void main(String[] a){
        FinalTest A = new FinalTest(12);
        FinalTest B = new FinalTest(13);
        A.show(A);
        A.a = 222;
        A.show(A);
        A.show(B);
    }

    public void show(final FinalTest finalTest){
        System.out.println(finalTest.a);
    }

}
