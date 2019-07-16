package com.example.view.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.view.R;

public class MyBroadCastReceiver extends AppCompatActivity {
    public static final String ACTION_DYNAMIC_BROADCAST = "android.intent.action.DYNAMIC_BROADCAST";
    public static final String ACTION_STATIC_BROADCAST = "android.intent.action.STATIC_BROADCAST";
    private  DynamicBroadcast dynamicBroadcast;
    Button mbtn_dynamic_broadcast;
    Button mbtn_static_broadcast_send_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broad_cast_receiver);
        mbtn_dynamic_broadcast = findViewById(R.id.btn_dynamic_broadcast_send_message);
        mbtn_dynamic_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送动态注册 广播 内容
                Intent intent = new Intent(ACTION_DYNAMIC_BROADCAST);
                intent.putExtra("data","Dynamc Broadcast Rweceiver");
                sendBroadcast(intent);
            }
        });

        mbtn_static_broadcast_send_message = findViewById(R.id.btn_static_broadcast_send_message);
        mbtn_static_broadcast_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送静态注册广播的内容
                Intent intent = new Intent(ACTION_STATIC_BROADCAST);
                intent.putExtra("data","来自 静态注册广播发送的消息----");
                sendBroadcast(intent);
            }
        });
        //动态注册广播
        dynamicBroadcast =new DynamicBroadcast();
        IntentFilter  intentFilter = new IntentFilter(ACTION_DYNAMIC_BROADCAST);
        registerReceiver(dynamicBroadcast,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("mouse","销毁广播");
        unregisterReceiver(dynamicBroadcast);
    }
}
