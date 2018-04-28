package com.example.administrator.mytestallhere.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.bean.Book;
import com.example.administrator.mytestallhere.bean.User;
import com.example.util.Logger;

public class ProviderActivity extends AppCompatActivity implements View.OnClickListener {
    Uri uriProvider=Uri.parse("content://cn.leslielee.provider");
    Uri uriBook = Uri.parse("content://cn.leslielee.provider/book");
    Uri uriUser = Uri.parse("content://cn.leslielee.provider/user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        //getContentResolver().registerContentObserver(uriProvider, true, );
        queryBook();
        queryUser();
        findViewById(R.id.btn_add_book).setOnClickListener(this);
        findViewById(R.id.btn_query_book).setOnClickListener(this);
        findViewById(R.id.btn_query_user).setOnClickListener(this);
        findViewById(R.id.btn_delete_book).setOnClickListener(this);
    }

    private void queryBook() {

        Cursor cursor = getContentResolver().query(uriBook, new String[]{"id,name"}, null, null, null);
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.name = cursor.getString(1);
            Logger.error("book name: " + book.name);
        }
    }

    private void queryUser() {

        Cursor cursor = getContentResolver().query(uriUser, new String[]{"id,name,age"}, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            Logger.error("user id: " + cursor.getInt(0) + " user name: " + user.name+" user age: "+user.age);
        }
    }

    private void addBook(){
        ContentValues values=new ContentValues();
        values.put("name","lover book");
        getContentResolver().insert(uriBook,values);
    }
    private void deleteBook(){
        getContentResolver().delete(uriBook,"name = ?",new String[]{"lover book"});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_query_book:
                queryBook();
                break;
            case R.id.btn_query_user:
                queryUser();
                break;
            case R.id.btn_delete_book:
                deleteBook();
                break;
            case R.id.btn_add_book:
                addBook();
                break;
        }
    }
}
