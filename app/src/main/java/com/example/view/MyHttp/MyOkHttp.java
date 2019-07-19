package com.example.view.MyHttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyOkHttp {
    private OkHttpClient client = new OkHttpClient();
    private Request.Builder builder = new Request.Builder().url("http://www.taolu5.com/sort/x?q=");
    private String rescontent;
    //执行请求
    public  void execute(){
        Call call = client.newCall(builder.build());
        call.enqueue(callback);//加入调度队列
    }
    //请求回调
    private  Callback  callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
            Log.i("mouse","Failure");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            //从Response
            String str = new String(response.body().bytes(),"utf-8");
            rescontent = str;
            Log.i("mouse","onResponse"+str);

        }
    };
    public String getStr(){
        for (;;){
            if (rescontent !=null){
                return rescontent;
            }
        }
    }

}
