package com.example.view.Multithreading;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.view.R;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
        //后台线程使用
//        new DownloadFilesTask().execute("www.hbduiah.dcbus");
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



        //线程池技术
        MyRejectedExrcutionHandler mrhandler = new MyRejectedExrcutionHandler();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,3,30, TimeUnit.SECONDS,new
                LinkedBlockingDeque<Runnable>(6),sthreadFactory,mrhandler);
        for (int i = 0; i < 10; i++) {
            final  int iValue = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    Log.i("mouse","当前线程id:  "+android.os.Process.myTid()+"  iValue  " +iValue);
                }
            };
            threadPoolExecutor.execute(runnable);
        }


    }
    private static final ThreadFactory sthreadFactory = new ThreadFactory() {
        //可以 在高并发情况下达到原子更新，避免使用synchronized,而且性能非常高
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"ThreadPoolExecutor new Thread # "+mCount.getAndIncrement());
        }
    };

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (msg.what == 1){
               int num = new Random().nextInt(65535);
               tv_textView.setText("num = "+num);
           }
        }
    };


    private class  DownloadFilesTask extends AsyncTask<String,Integer,Long>{
        @Override
        protected void onPreExecute() {
            Log.i("mouse","执行任务之前");
        }

        @Override
        protected Long doInBackground(String... strings) {
            int count = strings[0].length();
            long totalSize = 0;

            for (int i = 0; i < count; i++) {
                totalSize +=i;
                //执行此方法，会调用onProgressUpdate方法更新下载进度
                publishProgress(i);
                //如果取消就结束任务
                if (isCancelled()){
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("mouse","当前下载进度："+values[0].intValue());
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Log.i("mouse","下载完成！");
        }
    }



}
