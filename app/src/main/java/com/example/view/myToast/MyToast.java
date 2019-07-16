package com.example.view.myToast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
         textView.setText("Toast测试-----！！！");
          mytoast.setView(view);
          mytoast.setGravity(Gravity.CENTER,0,0);
//        mytoast.setText("Toast测试！！！");
        mytoast.setDuration(Toast.LENGTH_LONG);
        mytoast.show();

    }
}
