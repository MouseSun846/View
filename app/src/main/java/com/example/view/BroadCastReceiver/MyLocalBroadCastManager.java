package com.example.view.BroadCastReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.view.R;

public class MyLocalBroadCastManager extends AppCompatActivity {
    private Button mbtn_myAPPLocalBroadCast;
    private LocalBroadcastReceiver localBroadcastReceiver;
    private final String action = "ACTION_LOCAL_BROADCAST";
    private  LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_local_broad_cast_manager);
        //获取对象
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastReceiver= new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver,new IntentFilter(action));
        mbtn_myAPPLocalBroadCast = findViewById(R.id.btn_myAPPLocalBroadCast);
        mbtn_myAPPLocalBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(action);
                intent.putExtra("data", "from localBroadcast send message!!!");
                LocalBroadcastManager.getInstance(MyLocalBroadCastManager.this).sendBroadcast(intent);
            }
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("mouse","销毁应用内广播");
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }
}
