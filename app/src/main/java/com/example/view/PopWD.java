package com.example.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.view.BroadCastReceiver.MyBroadCastReceiver;
import com.example.view.BroadCastReceiver.MyLocalBroadCastManager;
import com.example.view.ContentProvider.MyContentProvider;
import com.example.view.GridViiew.MyGridView;
import com.example.view.ListView.MyListView;
import com.example.view.Multithreading.Multithreading;
import com.example.view.MyApplication.MyApplication;
import com.example.view.MyFragment.MyFragmntTest;
import com.example.view.Navigation.MyNavigation;
import com.example.view.RecycleView.GridRecycleView;
import com.example.view.RecycleView.MyRecycleView;
import com.example.view.RecycleView.MyStaggerView;
import com.example.view.Service.MyServiceActivity;
import com.example.view.SharedElement.MyShareElement;
import com.example.view.ViewerPage.MyViewPage;
import com.example.view.myToast.MyToast;

/**
 * 当前页面主要是通过点击相应的Button来实现相应的功能
 */
public class PopWD extends AppCompatActivity {
    Button mbtn_popwdUP,mbtn_popwdDown,mbtn_dialogfrag;
    Button mbtn_listView,mbtn_gridview,mbtn_recycleView,mbtn_gridrecView;
    Button mbtn_staggerView,mbtn_ViewPage,mbtn_Toast,mbtn_gridrecycleview;
    Button mbtn_myServices,mbtn_myBroadCast,mbtn_myAPPBroadCast;
    Button  mbtn_myContentProvider,mbtn_myFragment,mbtn_MyNavigation;
    Button mbtn_MultiThread;

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



    }
    public class popOnclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_popwdup:
                    showAsDropDown();
                break;
                case R.id.btn_popwddown:
                    showBottomPopupWindow();
                    break;
                case  R.id.btn_dialogFrag:
//                    MyDialogFragment.newInstance().show(getSupportFragmentManager(),"MyDialog");
                    MyDialogFragment myDialogFragment = MyDialogFragment.newInstance();
                    myDialogFragment.setOnClickListener(onClickListener);
                    myDialogFragment.show(getSupportFragmentManager(),"Plant");
                    break;
                case  R.id.btn_listview:            //列表视图
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
            }
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
}
