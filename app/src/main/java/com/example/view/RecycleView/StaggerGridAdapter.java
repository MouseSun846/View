package com.example.view.RecycleView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.view.R;

import java.util.List;
import java.util.Random;

public class StaggerGridAdapter extends RecyclerView.Adapter<StaggerGridAdapter.MyViewHolder> {
    private List<String> datas;
    private LayoutInflater inflater;
    private StaggerViewOnItemClickListener staggerViewOnItemClickListener;

    public void setStaggerViewOnItemClickListener(StaggerViewOnItemClickListener staggerViewOnItemClickListener) {
        this.staggerViewOnItemClickListener = staggerViewOnItemClickListener;
    }

    public StaggerGridAdapter(Context context, List<String> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }
    //给每一行View填充数据
    public StaggerGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = inflater.inflate(R.layout.reclyer_staggered_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.textView.setText(datas.get(i));
        myViewHolder.textView.setHeight(Integer.parseInt(datas.get(i)));
        //给item设置监听事件
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                staggerViewOnItemClickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.tv_staggerview);
        }
    }

    public interface StaggerViewOnItemClickListener{
        void onItemClick(int position);
    }
}
