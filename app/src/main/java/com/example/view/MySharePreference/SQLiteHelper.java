package com.example.view.MySharePreference;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;    //数据库版本号
    private static String db_name = "demo";
    public SQLiteHelper(Context context) {
        super(context, db_name, null, VERSION);
    }

    //第一次创建库的时候，调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建 数据库时候 把学生表创建好
        String  sql = "create table user(id  int,name varchar(20),age  int)";
        db.execSQL(sql);
        Log.i("mouse","onCrete-----");
    }
    //更新 数据库版本号的时候就会这个方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("mosue","update Database---");
    }
}
