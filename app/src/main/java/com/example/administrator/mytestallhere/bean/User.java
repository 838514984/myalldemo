package com.example.administrator.mytestallhere.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/24 0024.
 */

public class User implements Parcelable{
    public String name;
    public int age;
    public String[] favorites;
    public String[] viewhistories;

    public User(){

    }
    public User(String name,int age){
        this.name=name;
        this.age=age;
    }
    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return name+":"+age;
    }
}
