package com.example.view.SharedElement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.view.R;

public class SecondShareElelment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senond_share_elelment);
        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();//退出进行动画
            }
        });
        findViewById(R.id.r1_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String value = getIntent().getStringExtra("senddata");
        Toast.makeText(this,value,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //系统返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            Intent  intent = new Intent();
            intent.putExtra("backdata","回传值");
            setResult(RESULT_OK,intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
