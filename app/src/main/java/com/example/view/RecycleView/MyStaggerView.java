package com.example.view.RecycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyStaggerView extends AppCompatActivity {
    List<String> datas;
    private RecyclerView recyclerView;
    private StaggerGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stagger_view);
        initdata();
        recyclerView = findViewById(R.id.rev_my_staggerView);
        //设置构建样式
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        adapter =new StaggerGridAdapter(this,datas);
        //设置监听事件
        adapter.setStaggerViewOnItemClickListener(new StaggerGridAdapter.StaggerViewOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.i("mouse","position  "+position);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    private void initdata(){
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(""+new Random().nextInt(100)+1);
        }
    }
}
