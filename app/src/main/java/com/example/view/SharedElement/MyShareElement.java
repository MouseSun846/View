package com.example.view.SharedElement;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.MyApplication.MyApplication;
import com.example.view.R;

public class MyShareElement extends AppCompatActivity implements View.OnClickListener{
    private View viewshareOne;
    private TextView viewshareTwo;
    private Button mbtn_close;
    private MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share_element);
        viewshareOne = findViewById(R.id.view_share_one);
        viewshareTwo = findViewById(R.id.view_share_two);
        findViewById(R.id.r1_root).setOnClickListener(this);
        viewshareTwo.setOnClickListener(this);
        mbtn_close = findViewById(R.id.btn_act_close);
        mbtn_close.setOnClickListener(this);
        myApplication = (MyApplication) getApplication();
        myApplication.addActivity(this);
    }
    @Override
    public void onClick(View view){
        if (view.getId() == R.id.r1_root){
            finish();
        }else if (view.getId() == R.id.view_share_two){ //开启过渡效果
            Intent intent = new Intent(this,SecondShareElelment.class);
            intent.putExtra("senddata","数据");
            Pair onepair = new Pair<>(viewshareOne, ViewCompat.getTransitionName(viewshareOne));
            Pair twopair = new Pair<>(viewshareTwo, ViewCompat.getTransitionName(viewshareTwo));
//            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,onepair,twopair);
//            ActivityCompat.startActivity(this,intent,transitionActivityOptions.toBundle());
            //
            startActivityForResult(intent,1);

        }
        if (view.getId() == R.id.btn_act_close){
            //强制退出终止程序--但是会重启，安卓系统会重新创建一个新的进程。
//            System.exit(0);
//            android.os.Process.killProcess(android.os.Process.myPid());
            myApplication.finishAllActivity();
        }
    }
//重写 onActivityResult 这个方法，通过判断resultCode是不是自己启动的，然后通过Intent获取返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            String  res = data.getStringExtra("backdata");
            Toast.makeText(this,res,Toast.LENGTH_LONG).show();
        }
        Log.i("mouse"," "+requestCode + " " +resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
