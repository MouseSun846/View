package com.example.view;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.view.BroadCastReceiver.MyBroadCastReceiver;
import com.example.view.BroadCastReceiver.MyLocalBroadCastManager;
import com.example.view.ContentProvider.MyContentProvider;
import com.example.view.GridViiew.MyGridView;
import com.example.view.ListView.MyListView;
import com.example.view.Multithreading.Multithreading;
import com.example.view.MyApplication.MyApplication;
import com.example.view.MyFragment.MyFragmntTest;
import com.example.view.MyHttp.MyHttp;
import com.example.view.MyNotification.NotificationActivity;
import com.example.view.MySharePreference.MySharePreference;
import com.example.view.Navigation.MyNavigation;
import com.example.view.RecycleView.GridRecycleView;
import com.example.view.RecycleView.MyRecycleView;
import com.example.view.RecycleView.MyStaggerView;
import com.example.view.Service.MyServiceActivity;
import com.example.view.SharedElement.MyShareElement;
import com.example.view.TextTureView.MyTextTureView;
import com.example.view.ViewerPage.MyViewPage;
import com.example.view.WebView.MyWebVIew;
import com.example.view.myToast.MyToast;

import java.util.List;
import java.util.Locale;

import static java.lang.Thread.sleep;

/**
 * 当前页面主要是通过点击相应的Button来实现相应的功能
 */
public class PopWD extends AppCompatActivity {
    private LocationManager locationManager;
    Button mbtn_popwdUP, mbtn_popwdDown, mbtn_dialogfrag;
    Button mbtn_listView, mbtn_gridview, mbtn_recycleView, mbtn_gridrecView;
    Button mbtn_staggerView, mbtn_ViewPage, mbtn_Toast, mbtn_gridrecycleview;
    Button mbtn_myServices, mbtn_myBroadCast, mbtn_myAPPBroadCast;
    Button mbtn_myContentProvider, mbtn_myFragment, mbtn_MyNavigation;
    Button mbtn_MultiThread, mbtn_MyHttp, mbtn_MySharePreference;
    Button mbtn_MyNotification, mbtn_TextTureView;
    Button mbtn_JumpBroswer, mbtn_WebView, mbtn_Dingwei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        popOnclick onclick = new popOnclick();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_wd);
        mbtn_popwdUP = findViewById(R.id.btn_popwdup);
        mbtn_popwdUP.setOnClickListener(onclick);
        mbtn_popwdDown = findViewById(R.id.btn_popwddown);
        mbtn_popwdDown.setOnClickListener(onclick);
        mbtn_dialogfrag = findViewById(R.id.btn_dialogFrag);
        mbtn_dialogfrag.setOnClickListener(onclick);
        mbtn_listView = findViewById(R.id.btn_listview);
        mbtn_listView.setOnClickListener(onclick);
        mbtn_gridview = findViewById(R.id.btn_gridview);
        mbtn_gridview.setOnClickListener(onclick);
        mbtn_recycleView = findViewById(R.id.btn_recycleview);
        mbtn_recycleView.setOnClickListener(onclick);
        mbtn_gridrecView = findViewById(R.id.btn_gridrecycleview);
        mbtn_gridrecView.setOnClickListener(onclick);
        mbtn_staggerView = findViewById(R.id.btn_Stagger_recycleview);
        mbtn_staggerView.setOnClickListener(onclick);
        mbtn_ViewPage = findViewById(R.id.btn_ViewPage);
        mbtn_ViewPage.setOnClickListener(onclick);
        mbtn_Toast = findViewById(R.id.btn_Toast);
        mbtn_Toast.setOnClickListener(onclick);
        mbtn_gridrecycleview = findViewById(R.id.btn_SharedElement);
        mbtn_gridrecycleview.setOnClickListener(onclick);
        mbtn_myServices = findViewById(R.id.btn_myServices);
        mbtn_myServices.setOnClickListener(onclick);
        mbtn_myBroadCast = findViewById(R.id.btn_myBroadCast);
        mbtn_myBroadCast.setOnClickListener(onclick);
        mbtn_myAPPBroadCast = findViewById(R.id.btn_myAPPBroadCast);
        mbtn_myAPPBroadCast.setOnClickListener(onclick);
        mbtn_myContentProvider = findViewById(R.id.btn_myContentProvider);
        mbtn_myContentProvider.setOnClickListener(onclick);
        mbtn_myFragment = findViewById(R.id.btn_myFragment);
        mbtn_myFragment.setOnClickListener(onclick);
        mbtn_MyNavigation = findViewById(R.id.btn_MyNavigation);
        mbtn_MyNavigation.setOnClickListener(onclick);
        mbtn_MultiThread = findViewById(R.id.btn_MultiThread);
        mbtn_MultiThread.setOnClickListener(onclick);
        mbtn_MyHttp = findViewById(R.id.btn_MyHttp);
        mbtn_MyHttp.setOnClickListener(onclick);
        mbtn_MySharePreference = findViewById(R.id.btn_MySharePreference);
        mbtn_MySharePreference.setOnClickListener(onclick);
        mbtn_MyNotification = findViewById(R.id.btn_MyNotification);
        mbtn_MyNotification.setOnClickListener(onclick);
        mbtn_TextTureView = findViewById(R.id.btn_TextTureView);
        mbtn_TextTureView.setOnClickListener(onclick);
        mbtn_JumpBroswer = findViewById(R.id.btn_JumpBroswer);
        mbtn_JumpBroswer.setOnClickListener(onclick);
        mbtn_WebView = findViewById(R.id.btn_WebView);
        mbtn_WebView.setOnClickListener(onclick);
        mbtn_Dingwei = findViewById(R.id.btn_Dingwei);
        mbtn_Dingwei.setOnClickListener(onclick);



    }

    public class popOnclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_popwdup:
                    showAsDropDown();
                    break;
                case R.id.btn_popwddown:
                    showBottomPopupWindow();
                    break;
                case R.id.btn_dialogFrag:
//                    MyDialogFragment.newInstance().show(getSupportFragmentManager(),"MyDialog");
                    MyDialogFragment myDialogFragment = MyDialogFragment.newInstance();
                    myDialogFragment.setOnClickListener(onClickListener);
                    myDialogFragment.show(getSupportFragmentManager(), "Plant");
                    break;
                case R.id.btn_listview:            //列表视图
                    intent = new Intent(PopWD.this, MyListView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_gridview:             //网格视图
                    intent = new Intent(PopWD.this, MyGridView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_recycleview:      //循环列表视图
                    intent = new Intent(PopWD.this, MyRecycleView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_gridrecycleview:      //循环网格视图
                    intent = new Intent(PopWD.this, GridRecycleView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Stagger_recycleview:      //瀑布流
                    intent = new Intent(PopWD.this, MyStaggerView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_ViewPage:             //翻页视图
                    intent = new Intent(PopWD.this, MyViewPage.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Toast:
                    intent = new Intent(PopWD.this, MyToast.class);
                    startActivity(intent);
                    break;
                case R.id.btn_SharedElement:
                    intent = new Intent(PopWD.this, MyShareElement.class);
                    startActivity(intent);
                    break;
                case R.id.btn_myServices:
                    intent = new Intent(PopWD.this, MyServiceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_myBroadCast:
                    //广播
                    intent = new Intent(PopWD.this, MyBroadCastReceiver.class);
                    startActivity(intent);
                    break;
                case R.id.btn_myAPPBroadCast:
                    //应用内广播
                    intent = new Intent(PopWD.this, MyLocalBroadCastManager.class);
                    startActivity(intent);
                    break;
                case R.id.btn_myContentProvider:
                    //内容提供者
                    intent = new Intent(PopWD.this, MyContentProvider.class);
                    startActivity(intent);
                    break;
                case R.id.btn_myFragment:
                    intent = new Intent(PopWD.this, MyFragmntTest.class);
                    startActivity(intent);
                    break;
                case R.id.btn_MyNavigation:
                    intent = new Intent(PopWD.this, MyNavigation.class);
                    startActivity(intent);
                    break;
                case R.id.btn_MultiThread:
                    intent = new Intent(PopWD.this, Multithreading.class);
                    startActivity(intent);
                    break;
                case R.id.btn_MyHttp:
                    intent = new Intent(PopWD.this, MyHttp.class);
                    startActivity(intent);
                    break;
                case R.id.btn_MySharePreference:
                    intent = new Intent(PopWD.this, MySharePreference.class);
                    startActivity(intent);
                    break;
                case R.id.btn_MyNotification:
                    ShowMyNotification();
                    break;
                case R.id.btn_TextTureView:
                    intent = new Intent(PopWD.this, MyTextTureView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_JumpBroswer:
                    Uri uri = Uri.parse("http://www.baidu.com");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
                case R.id.btn_WebView:
                    intent = new Intent(PopWD.this, MyWebVIew.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Dingwei:
                    //获取定位
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    boolean gps = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    if (!gps) {
                        Toast.makeText(PopWD.this, "请设置界面开启GPS定位服务", Toast.LENGTH_LONG).show();
                        return;
                    }

                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            showmyLocation(location);
                            break;
                        } else {
                            Toast.makeText(PopWD.this, "NULL", Toast.LENGTH_LONG).show();
                        }
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);

                    break;
            }
        }
    }

    /**
     *监听位置变化
     * 参数1：定位方式，主要有GPS_PROVIDER和NETWORK_PROVIDER,前者是GPS，或者是GPRS以及WIFI定位
     * 参数2：位置信息更新周期，单位是 毫秒
     * 参数3：位置变化最小周期。当位置变化超过此值时，将更新位置信息
     * 参数4：监听
     */
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showmyLocation(location);
        }
        //当位置提供者的 状态发生改变
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        //位置信息提供者可用时自动调用，例如GPS开启
        @Override
        public void onProviderEnabled(String provider) {

        }
        //位置信息不可用时自动调用，例如禁用GPS
        @Override
        public void onProviderDisabled(String provider) {

        }
    };




    public void showmyLocation(Location  location){
        Toast.makeText(PopWD.this,"经度："+location.getLongitude()+"\n维度："+location.getLatitude(),Toast.LENGTH_LONG).show();
        Log.i("mouse","经度："+location.getLongitude()+"\n维度："+location.getLatitude());
        Geocoder geocoder = new Geocoder(this, Locale.CHINA);
        try{
            //参数1 ： 纬度  参数 2：经度 参数 3：返回地址的数目(由于同一纬度肯对应多个地址，该参数1--5
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            Log.i("mouse","len +"+addressList.size());
            for (Address address : addressList) {
                Log.i("mouse",address.toString());
            }
            Toast.makeText(PopWD.this,addressList.get(0).getAddressLine(0),Toast.LENGTH_LONG).show();
        }catch (Exception e){
               Log.i("mouse",e.toString());
        }
    }
    /**
     * 函数功能：主要是弹出对话框 显示在桌面中间
     */
    private void showAsDropDown(){
        View popView = LayoutInflater.from(this).inflate(R.layout.popdropdown,null);
        PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popwd));
        popupWindow.showAsDropDown(mbtn_popwdUP,200,200);
    }

    /**
     *Dialog从底部弹出
     */
    private void showBottomPopupWindow(){
        View popView = LayoutInflater.from(this).inflate(R.layout.popdropdown,null);
        PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x30000000));
        //添加动画
        popupWindow.setAnimationStyle(R.style.Animation_Bottom_Dialog);
        //参数一 根视图，整个Window界面对的最顶层View
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM,0,0);
    }

    /**
     * 函数功能：主要实现对 Dialog的按钮点击事件的监听
     */
    private DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Log.i("mouse","ok");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Log.i("mouse","Cancel");
                    break;
            }
        }
    };


    /**
     * 通知代码
     */
    public void ShowMyNotification(){
//        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.demo).setContentTitle("测试").setContentText("内容");
//        Intent intent = new Intent(PopWD.this, NotificationActivity.class);
//        PendingIntent clickPending = PendingIntent.getActivity(this,0,intent,0);
//        mbuilder.setContentIntent(clickPending);
        //自定义布局
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_custom_notification);
        Notification mnotification = new Notification();
        //必须设置,否则不能 通知消息
        mnotification.icon = R.mipmap.demo;
        mnotification.contentView = remoteViews;
        //点击这条通知自动从通知栏取消
//        mbuilder.setAutoCancel(true);
        final NotificationManager mnotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mnotificationManager.notify(1111,mbuilder.build());
        mnotificationManager.notify(111,mnotification);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    //删除通知
                    mnotificationManager.cancel(1111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }
}
