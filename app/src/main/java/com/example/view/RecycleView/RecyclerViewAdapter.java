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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<String> datas;
    private LayoutInflater inflater;
    public RecyclerViewAdapter(Context context,List<String> datas){
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }
    //创建每一行的View用RecycleView.ViewHolder
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_recycleview,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    //给每一行View填充数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.textview.setText(datas.get(i));
    }

    //数据源的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;
        public MyViewHolder(View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.rtextview);
        }
    }
}
