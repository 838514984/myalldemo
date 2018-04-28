package com.example.administrator.mytestallhere.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.util.Logger;

/**
 * Created by Administrator on 2018/2/25 0025.
 */

public class ProviderMy extends ContentProvider {
    public static String AUTHORITY = "cn.leslielee.provider";
    public static Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");
    public static final int BOOK_CODE = 0;
    public static final int USER_CODE = 1;
    private DBHelper mDbHelper;
    public static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase mDataBase;

    static {
        sUriMatcher.addURI(AUTHORITY,"book",BOOK_CODE);
        sUriMatcher.addURI(AUTHORITY,"user",USER_CODE);
    }

    private String getTableName(Uri uri){
        switch (sUriMatcher.match(uri)){
            case BOOK_CODE:
                return DBHelper.BOOK_TABLE_NAME;
            case USER_CODE:
                return DBHelper.USER_TABLE_NAME;
            default:
                return "";
        }
    }

    @Override
    public boolean onCreate() {
        Logger.error("ProviderMy onCreate Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        mDbHelper =new DBHelper(getContext());
        //初始化，插入几条数据
        mDataBase=mDbHelper.getWritableDatabase();
        mDataBase.execSQL("delete from "+DBHelper.USER_TABLE_NAME);
        mDataBase.execSQL("delete from "+DBHelper.BOOK_TABLE_NAME);
        mDataBase.execSQL("insert into book (name) values('android');");
        mDataBase.execSQL("insert into book (name) values('english');");
        mDataBase.execSQL("insert into user (name,age) values('2332',24);");
        mDataBase.execSQL("insert into user (name,age) values('wqh',24);");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Logger.error("ProviderMy query Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        String tableName=getTableName(uri);
        return mDataBase.query(tableName,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Logger.error("ProviderMy getType Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Logger.error("ProviderMy insert Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        String tableName=getTableName(uri);
        mDataBase.insert(tableName,null,values);
        //数据源改变，需要通知
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Logger.error("ProviderMy delete Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        String tableName=getTableName(uri);
        int count=mDataBase.delete(tableName,selection,selectionArgs);
        if (count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Logger.error("ProviderMy update Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        String tableName=getTableName(uri);
        int count=mDataBase.update(tableName,values,selection,selectionArgs);
        if (count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }
}
