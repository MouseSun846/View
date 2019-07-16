package com.example.view;


import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mbtnJump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //切换动画
//                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
        //5.0之后切换动画方式
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Transition  transition = TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.fade);
            getWindow().setEnterTransition(transition);
        }
        setContentView(R.layout.activity_main);
        mbtnJump = findViewById(R.id.fullscreen);
        mbtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,FullScreen.class);
        //启动Actity,并设置动画
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());  //跳转到全屏界面


            }
        });
    }

//    public void getAndroiodScreenProperty() {
//        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;         // 屏幕宽度（像素）
//        int height = dm.heightPixels;       // 屏幕高度（像素）
//        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
//        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
//        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
//        int screenHeight = (int) (height / density);// 屏幕高度(dp)
//
//
//        Log.d("h_bl", "屏幕宽度（像素）：" + width);
//        Log.d("h_bl", "屏幕高度（像素）：" + height);
//        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
//        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
////        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);         //640dp
////        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);        //360dp
//    }
}
