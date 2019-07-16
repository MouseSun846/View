package com.example.view.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import static java.lang.Thread.sleep;

public class LocalService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private boolean isInterrupt = false;
    private Thread mythread;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("mouse","Tis"+android.os.Process.myTid());
        Log.i("mouse","public void onCreate() ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("mouse"," public int onStartCommand(Intent intent, int flags, int startId) "+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("mouse"," public void onDestroy() ");
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("mouse"," public IBinder onBind(Intent intent)");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        isInterrupt = true;
        return super.onUnbind(intent);
    }

    public void downloadMusic(){
        mythread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.i("mouse", "music is downloading-----");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isInterrupt){
                        Log.i("mouse","stop----");
                        break;
                    }
                }
            }
        });
        mythread.start();
    }
    public class LocalBinder extends Binder {
        //返回当前的Service对象
        LocalService  getService(){
            return LocalService.this;
        }
    }
}
