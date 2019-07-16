package com.example.view.MyFragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.view.R;

public class MyFragmntTest extends AppCompatActivity implements View.OnClickListener {
    private Button mbtn_show,mbtn_hide;
    private FragmentOne fragmentOne;
    private boolean  isfirst = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragmnt_test);
        mbtn_show = findViewById(R.id.btn_show);
        mbtn_show.setOnClickListener(this);
        mbtn_hide = findViewById(R.id.btn_hide);
        mbtn_hide.setOnClickListener(this);
       //实例化Fragment对象
        fragmentOne = new FragmentOne();
        fragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyFragmntTest.this,"fragment clicked---",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = null;
        //开启一个Fragment事务
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!isfirst){
            fragmentTransaction.add(R.id.fragment_one,fragmentOne);
            isfirst = true;
        }
        switch (v.getId()){
            case R.id.btn_show:
                    fragmentTransaction.show(fragmentOne);
                break;
            case R.id.btn_hide:
                    fragmentTransaction.hide(fragmentOne);
                break;
        }
        Log.i("mosue","fragmentTransaction "+fragmentTransaction.hashCode());
        fragmentTransaction.commit();
    }
}
