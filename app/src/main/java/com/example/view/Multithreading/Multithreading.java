package com.example.view.Multithreading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.view.R;

import java.util.Random;

import static java.lang.Thread.sleep;


public class Multithreading extends AppCompatActivity {

    private TextView tv_textView;
    private Button  mbtn_sendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading);
        tv_textView = findViewById(R.id.tv_myThreadText);
        tv_textView.setText("---Handler Test---");
        mbtn_sendMessage = findViewById(R.id.btn_sendMessage);
        mbtn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = mhandler.obtainMessage();
                msg.what = 1;
                mhandler.sendMessage(msg);
            }
        });
        //方式一
        //通过runOnUiThread实现
//        new  Thread(){
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        while (true){
//                            int color = new Random().nextInt(65535);
//                            tv_textView.setText("color " + color);
////                            try {
////                                sleep(5000);
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
//                            Log.i("mouse","color");
////                        }
//                    }
//                });
//            }
//        }.start();
        //方式二
        //View.post
//        new Thread(){
//            @Override
//            public void run() {
//                Log.i("mouse","run---");
//                tv_textView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (true){
//                            int color = new Random().nextInt(65535);
//                            tv_textView.setText("color "+color);
//                            Log.i("mouse",""+color);
//                        }
//
//                    }
//                });
//            }
//        }.start();
    }

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (msg.what == 1){
               int num = new Random().nextInt(65535);
               tv_textView.setText("num = "+num);
           }
        }
    };
}
