package com.example.view.Service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.view.R;

public class MyServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtn_start_services;
    private Button mbtn_stop_services;
    private Button mbtn_bind_services;
    private Button mbtn_unbind_services;
    private LocalService localService;  //services对象
    private boolean mIsBound;   //是否绑定

    ServiceConnection serviceConnection = new ServiceConnection() {
        //Activity与Service绑定 时候用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            localService = ((LocalService.LocalBinder) service).getService();
            localService.downloadMusic();   //调用下载音乐的方法
        }

        //解绑时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
            localService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        mbtn_start_services = findViewById(R.id.btn_start_service);
        mbtn_start_services.setOnClickListener(this);
        mbtn_stop_services = findViewById(R.id.btn_stop_service);
        mbtn_stop_services.setOnClickListener(this);
        Log.i("mouse","Tis"+android.os.Process.myTid());
        mbtn_bind_services = findViewById(R.id.btn_bind_service);
        mbtn_bind_services.setOnClickListener(this);
        mbtn_unbind_services = findViewById(R.id.btn_unbind_service);
        mbtn_unbind_services.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_service:
                //启动Service
                Intent start_intent = new Intent(this,LocalService.class);
                startService(start_intent);
                Log.i("mouse","Click!!!");
                break;
            case R.id.btn_stop_service:
                //关闭Service
                Intent stop_intent = new Intent(this,LocalService.class);
                stopService(stop_intent);
                break;
            case R.id.btn_bind_service:
                //绑定Service
                Intent bindintent = new Intent(this,LocalService.class);
                bindService(bindintent,serviceConnection,BIND_AUTO_CREATE);
                mIsBound = true;

                break;
            case R.id.btn_unbind_service:
                if (mIsBound){
                    unbindService(serviceConnection);
                    mIsBound = false;
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
