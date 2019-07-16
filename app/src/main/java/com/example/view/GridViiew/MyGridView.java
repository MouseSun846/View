package com.example.view.GridViiew;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

public class MyGridView extends AppCompatActivity {
    private GridView gridView;
    private List<Integer> images;
    private MyGridViewAdapter myGridViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grid_view);
        initdata();
        gridView = findViewById(R.id.btn_mygridview);
        gridView.setAdapter(myGridViewAdapter = new MyGridViewAdapter(this,images));
    }

    private void initdata(){
        images = new ArrayList<>();
        for (int i = 0; i < 1200; i++) {
            if (i % 2 == 0){
                images.add(R.mipmap.biu);
            }
            else {
                images.add(R.mipmap.plant);
            }

        }

    }
    //构造一个适配器
    private class MyGridViewAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private List<Integer> images;
        public MyGridViewAdapter(Context context, List<Integer> images){
            inflater = LayoutInflater.from(context);
            this.images = images;
        }
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder viewholder;
            if(convertView == null){
                viewholder = new Viewholder();
                convertView = inflater.inflate(R.layout.actity_grid_item,parent,false);
                viewholder.imageView = convertView.findViewById(R.id.imageview);
                convertView.setTag(viewholder);
            }else {
                viewholder = (Viewholder) convertView.getTag();
            }
            viewholder.imageView.setImageResource(images.get(position));
            return convertView;
        }
    }

    private class Viewholder{
        private ImageView imageView;
    }
}
