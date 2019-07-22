package com.example.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class FullScreen extends AppCompatActivity {
    TextView  tvName;
    ProgressBar progressBar;
    Button mbtn_plus,mbtn_minus,mbtn_pDialog,mbtn_paltDialog,mbtn_next;
    SeekBar my_seekbar;
    private ProgressDialog progressDialog;
    int value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        OnClick onClick = new OnClick();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        tvName = findViewById(R.id.name);
        tvName.setText(getString(R.string.demo,new Object[]{"刘","尚","阳 "}));
        mbtn_plus = findViewById(R.id.btn_plus);
        mbtn_plus.setOnClickListener(onClick);
        mbtn_minus = findViewById(R.id.btn_minus);
        mbtn_minus.setOnClickListener(onClick);
        progressBar = findViewById(R.id.progress);
        mbtn_pDialog = findViewById(R.id.btn_pbarDialog);
        mbtn_pDialog.setOnClickListener(onClick);
        mbtn_paltDialog = findViewById(R.id.btn_paltDialog);
        mbtn_paltDialog.setOnClickListener(onClick);
        mbtn_next = findViewById(R.id.next_page);
        mbtn_next.setOnClickListener(onClick);
        my_seekbar = findViewById(R.id.sbar_seekbar);
        my_seekbar.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            my_seekbar.setMin(0);
        }
        my_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("mouse","current: "+seekBar.getProgress());
            }
        });


    }
    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_plus:
                    if(value >= 100){
                        value = 100;
                    }else {
                        value += 10;
                    }
                    progressBar.setProgress(value);
                    break;
                case R.id.btn_minus:
                    if(value <= 0){
                        value = 0;
                    }else {
                        value -= 10;
                    }
                    progressBar.setProgress(value);
                    break;
                case R.id.btn_pbarDialog:
                    progressDialog = new ProgressDialog(FullScreen.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setTitle("Demo");
                    progressDialog.setMessage("progressDialog Test!!!");
                    progressDialog.show();


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sleep(5000);
                            }catch (Exception e){

                            }

                            handler.sendEmptyMessage(0);
                            Log.i("mouse","seend_cancel");

                        }
                    }).start();
                    break;
                case R.id.btn_paltDialog:
                    //提示对话框
                    show_altDialog();
                    break;
                case R.id.next_page:
                    Intent intent = new Intent(FullScreen.this,PopWD.class);
                    startActivity(intent);
                    break;

            }
        }
    }
    private void show_altDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Love Chooseing ?");
        builder.setMessage("你确定要选择我吗？");
        builder.setIcon(R.mipmap.xinaixin);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                FullScreen.this.finish();   //结束当前的activity
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            progressDialog.dismiss();
            Log.i("mouse","cancel");
        }
    };
}
