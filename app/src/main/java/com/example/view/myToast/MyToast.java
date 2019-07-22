package com.example.view.myToast;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.R;

public class MyToast extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_toast);
        Toast mytoast = new Toast(this);
        View view = LayoutInflater.from(this).inflate(R.layout.toast_msg,null);
         textView = view.findViewById(R.id.txt_toast_msg);
//         textView.setText("Toast测试-----！！！");
          mytoast.setView(view);
          mytoast.setGravity(Gravity.CENTER,0,0);
//        mytoast.setText("Toast测试！！！");
            mytoast.setDuration(Toast.LENGTH_LONG);
            mytoast.show();
        //复制内容到剪贴板
//        textCopy();
        //获取剪贴板内容
        getContentClipBoard();

    }

    //复制内容到剪贴板
    public void textCopy(){
        String str = "测试！";
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null,str));
    }

    //从剪贴板获取内容
    public void getContentClipBoard(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            clipboardManager.clearPrimaryClip();
        }
        String str = (String) clipboardManager.getPrimaryClip().getItemAt(0).getText();
        textView.setText(str);
        Log.i("mouse",str);


    }


}
