package com.example.view.RecycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

public class GridRecycleView extends AppCompatActivity {
    private List<String> datas;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycle_view);
        initData();
        recyclerView = findViewById(R.id.grid_recyclerview);
        //构建样式改变了---横向显示 2列
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4,GridLayoutManager.HORIZONTAL,false));
        //构建item的边界也需要变化
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, LinearLayoutManager.VERTICAL,R.color.colorAccent,1));
        //适配器里面的数据就不变了
        adapter = new RecyclerViewAdapter(this,datas);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("item:" + i);
            Log.i("mouse","item:" + i);
        }
    }
}
