package com.example.view.ContentProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.R;

import org.w3c.dom.Text;

/**
 * 本Activity通过动态授权，读取手机通讯录联系人
 */
public class MyContentProvider extends AppCompatActivity {
    private Button mbtn_getInfo;
    private TextView mtv_myinfo;
    private final int TAG_Permission = 1023;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_content_provider);
        mtv_myinfo = findViewById(R.id.tv_myinfo);
        mbtn_getInfo = findViewById(R.id.btn_getInfo);
        mbtn_getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readContacts();
                Toast.makeText(MyContentProvider.this,"toast",Toast.LENGTH_LONG).show();
            }
        });
        //检查用户是否同意授权，没有的话就申请权限

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},TAG_Permission);
        }else {
            Toast.makeText(this,"agreed!",Toast.LENGTH_SHORT).show();
        }


    }

    //重写授权回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case TAG_Permission:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"授权成功~~~",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"权限被拒绝了~~~",Toast.LENGTH_LONG).show();
                }

                break;
        }
        Log.i("mouse","requestCode " + requestCode);
    }

    //查询手机联系人列表
    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            Log.i("mouse","cursor.getCount()+ "+cursor.getCount());
            while (cursor.moveToNext()){
                String displayname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i("mouse","姓名："+displayname + " 号码："+number);
                mtv_myinfo.append("姓名："+displayname + " 号码："+number +" \n");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.i("mouse",e.getMessage());
        }
        finally {
            if(cursor !=null ){
                cursor.close();
            }
        }

    }
}
