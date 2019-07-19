package com.example.view.MySharePreference;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MySharePreference extends AppCompatActivity implements View.OnClickListener {
    Button mbtn_write,mbtn_read,mbtn_SQL,mbtn_mysql_look,mbtn_mysql_print,mbtn_mysql_update;
    Button mbtn_sdwrite,mbtn_sdread;
    private SQLiteDatabase sqLiteDatabase;
    private TextView mtv_showinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share_preference);
        mbtn_read = findViewById(R.id.btn_read);
        mbtn_read.setOnClickListener(this);
        mbtn_write = findViewById(R.id.btn_write);
        mbtn_write.setOnClickListener(this);
        mbtn_SQL = findViewById(R.id.btn_mysql);
        mbtn_SQL.setOnClickListener(this);
        mbtn_mysql_look = findViewById(R.id.btn_mysql_look);
        mbtn_mysql_look.setOnClickListener(this);
        mbtn_mysql_print = findViewById(R.id.btn_mysql_delete);
        mbtn_mysql_print.setOnClickListener(this);
        mbtn_mysql_update = findViewById(R.id.btn_mysql_update);
        mbtn_mysql_update.setOnClickListener(this);
        mbtn_sdwrite = findViewById(R.id.btn_sdwrite);
        mbtn_sdwrite.setOnClickListener(this);
        mbtn_sdread = findViewById(R.id.btn_sdread);
        mbtn_sdread.setOnClickListener(this);

        mtv_showinfo = findViewById(R.id.tv_showinfo);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_read:
                readMySharePreference();
                break;
            case R.id.btn_write:
                writeMySharePreference();
                break;
            case R.id.btn_mysql:
                sqlLiteTest();
                break;
            case R.id.btn_mysql_update:
                sqlLiteUpdate();
                break;
            case R.id.btn_mysql_look:
                diplaysql();
                break;
            case R.id.btn_mysql_delete:
                sqldelete();
                break;
            case R.id.btn_sdwrite:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    //判断有没有SD卡
                    File sdCard = Environment.getExternalStorageDirectory();
                    Toast.makeText(this,sdCard.getPath(),Toast.LENGTH_LONG).show();
                    String filepath = sdCard.getPath()+"/temp";
                    File demoFile = new File(filepath);
                    demoFile.mkdir();
//                    try {
//                        FileOutputStream fw = new FileOutputStream(filepath+"/demo.txt");
//                        byte[] buffer = "demo测试！！！！".getBytes();
//                        for (int i = 0; i < buffer.length; i++) {
//                            fw.write(buffer[i]);
//                        }
//                        fw.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                break;
            case R.id.btn_sdread:
                    File getFileDir = getFilesDir();
                    Toast.makeText(this,getFileDir.getPath(),Toast.LENGTH_LONG).show();
                break;

        }

    }

    //写数据
    public void writeMySharePreference(){
        //默认的操作 方式
        SharedPreferences sp = getApplicationContext().getSharedPreferences("demo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name","mouse");
        editor.putString("name","json");
        editor.commit();
    }

    //读数据
    public void readMySharePreference(){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("demo",Context.MODE_PRIVATE);
        String name = sp.getString("name","");
        Log.i("mouse","name "+name);
    }

    //SQL操作
    public void  sqlLiteTest(){
        ContentValues values = new ContentValues();
        values.put("id",1);
        values.put("name","mouse");
        values.put("age",23);
        sqLiteDatabase.insert("user",null,values);
        values.put("id",2);
        values.put("name","json");
        values.put("age",32);
        sqLiteDatabase.insert("user",null,values);
        Log.i("mosue","数据 增加成功---");

    }
    //更新数据库
    public void sqlLiteUpdate(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("age","88");
        sqLiteDatabase.update("user",contentValues,"name=?",new String[]{"mouse"});
    }
    //查找数据库
    public void diplaysql(){
        Cursor cursor = sqLiteDatabase.query("user",new String[]{"id","name","age"},null,null,null,null,null);
        String str = null;
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            str = str +"name: "+name+" age :"+age+"\n";
        }
        mtv_showinfo.setText(str);
        cursor.close();
    }

    //删除一条记录
    public void sqldelete(){
        sqLiteDatabase.delete("user","name=?",new String[]{"mouse"});
        diplaysql();
    }

    public void isSdExist(){
        //
    }

}
