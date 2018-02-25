package com.example.administrator.mytestallhere.provider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/2/25 0025.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="book_provider.db";
    public static final String USER_TABLE_NAME="user";
    public static final String BOOK_TABLE_NAME="book";
    public static final int DB_VERSION=1;
    private static String CREATE_BOOK_TABLE="CREATE TABLE IF NOT EXISTS "+BOOK_TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)";
    private static String  CREATE_USER_TABLE="CREATE TABLE IF NOT EXISTS "+USER_TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT ,AGE INT)";

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
