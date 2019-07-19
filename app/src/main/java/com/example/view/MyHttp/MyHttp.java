package com.example.view.MyHttp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ansen.http.entity.HttpConfig;
import com.ansen.http.net.HTTPCaller;
import com.ansen.http.net.RequestDataCallback;
import com.google.gson.Gson;
import com.example.view.PopWD;
import com.example.view.R;
import com.google.gson.reflect.TypeToken;

import org.apache.http.params.CoreConnectionPNames;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MyHttp extends AppCompatActivity {
    private Gson gson = new Gson();
    private TextView mtv_resHttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_http);
        mtv_resHttp = findViewById(R.id.tv_resHttp);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //GET方式获取信息
//                String res = getUserInfo();
//                //POST方式获取信息---没有找到合适的网站
////                String res = Postuseinfo();
////                Log.i("mouse",res);
//                Message message = mhandler.obtainMessage();
//                message.what = 1;
//                message.obj = (Object) res;
//                mhandler.sendMessage(message);
//
//            }
//        }).start();
        //json 操作
//        parseObject();
//        parseArray();
//        pardseMap();
//        objParseJson();
        //MyOkHttp
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.execute();
        mtv_resHttp.setText(myOkHttp.getStr());
    }

    //json 解析成对象
    private void parseObject(){
        String jsonstr = "{'name':'adqw','age':'20'}";
        User user = gson.fromJson(jsonstr,User.class);
        Log.i("mouse",user.toString());
    }
    //解析成数组
    private void parseArray(){
        String jsonstr = "[{'name':'Uini','age':30},{'name':'Lina','age':10}]";
        List<User> users = gson.fromJson(jsonstr,new TypeToken<List<User>>(){}.getType());
        for (int i = 0; i < users.size(); i++) {
            Log.i("mouse",users.get(i)+"");
        }
    }

    //解析成Map
    private void pardseMap(){
        String jsonstr = "{'1':{'name':'hndu','age':23},'2':{'name':'jdeis','age':22}}";
        Map<String,User> users = gson.fromJson(jsonstr,new TypeToken<Map<String,User>>(){}.getType());
        for (String key:users.keySet()){
            Log.i("mouse",""+key+" "+users.get(key));
        }
    }
    //对象解析成JSON字符串
    private void objParseJson(){
        User user = new User();
        user.setAge(11);;
        user.setName("mosue");
        String jsonstr = gson.toJson(user);
        Log.i("mouse",""+jsonstr);
    }

    //GET方法
    private String  getUserInfo(){
        String path = "http://www.taolu5.com/sort/x?q=";
        try{
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);      //设置连接超时时间
            httpURLConnection.setRequestMethod("GET");      //设置以GET方式提交数据
            if (httpURLConnection.getResponseCode() == 200){  //请求 成功
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len = 0;
                try{
                    while((len = inputStream.read(data)) != -1){
                        byteArrayOutputStream.write(data,0,len);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                String resultdatda = new String(byteArrayOutputStream.toByteArray());
                return resultdatda;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //POST方法
    private String Postuseinfo(){
//        URLEncoderURI.encode(url, "UTF-8");

        //CSDN登录 post方法
        String path = "https://passport.csdn.net/login?code=public";
        String jsonstr = "{'loginType':'1','pwdOrVerifyCode':'csdn2500684440','userIdentification':'13291658473'}";
        try {
            URL url = new URL(path);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");//以POST方法提交数据
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Content-Length",jsonstr.length()+"");
//            connection.setRequestProperty("X-Requested-With","XMLHttpRequest");
//            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");


            //post方法提交数据
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonstr.getBytes());
            int res = connection.getResponseCode();
            Log.i("mouse","res " + res);
            if ( res == 200){
//                Log.i("mouse","okhtttp");
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len = 0;
                try{
                    while((len = inputStream.read(data))  != -1 ){
                        byteArrayOutputStream.write(data,0,len);
                    }
                    String resultdata = new String(byteArrayOutputStream.toByteArray());
                    return resultdata;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (1 == msg.what){
                mtv_resHttp.setText((String)msg.obj);
            }
        }
    };




    public void onInit() {
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setAgent(true);
        httpConfig.setDebug(true);
        httpConfig.setTagName("mouse");
        httpConfig.addCommonField("pf","android");
        httpConfig.addCommonField("version_code","1");
        //初始化HTTPCaller
        HTTPCaller.getInstance().setHttpConfig(httpConfig);

        HTTPCaller.getInstance().get(User.class,"http://www.taolu5.com/sort/x?q=",null,requestDataCallback);
    }
    //请求回调 函数
    private RequestDataCallback requestDataCallback = new RequestDataCallback<User>() {
        @Override
        public void dataCallback(User user) {
            if (user == null){
                Log.i("mouse","请求失败");
            }else {
                Log.i("mouse","获取用户信息："+user.toString());
            }
        }
    };

}
