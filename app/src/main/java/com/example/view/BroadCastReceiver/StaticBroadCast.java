package com.example.view.BroadCastReceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 静态注册广播
 */
public class StaticBroadCast extends DynamicBroadcast {
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        Log.i("mouse","静态接收广播消息 ："+data);
    }
}
