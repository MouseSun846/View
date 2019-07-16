package com.example.view.ListView;

import android.content.Context;
import android.support.annotation.StyleableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

public class MyListView extends AppCompatActivity {
    private List<Message> items;
    ListView mlistView;
    private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);
        initdata();
        mlistView = findViewById(R.id.listview);
        myListViewAddHeader();
        mlistView.setAdapter(adapter = new ListViewAdapter(this,items));
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mouse","position "+position+"  id  "+id);
            }
        });
        //设置显示位置
        mlistView.setSelection(10);
    }
    //给listview添加头部
    private void myListViewAddHeader(){
        View headeer = LayoutInflater.from(this).inflate(R.layout.listviewer_header,null);
        mlistView.addHeaderView(headeer);
    }
    //初始化数据
    private void initdata(){
        items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0){
                items.add(new Message("我不帅，真的有点坏！！！",false));
            }else {
                items.add(new Message("不懂爱，真的菜！！！",true));
            }

        }
    }

    //构造一个适配器 继承于BaseAdapter
    public class ListViewAdapter extends BaseAdapter{
        private List<Message> message;
        private LayoutInflater inflater;
        private final int TYPE_SEND = 0;//消息发送
        private final int TYPE_ACCEPT = 1;//消息接收
        public ListViewAdapter(Context context, List<Message> message) {
            inflater = LayoutInflater.from(context);
            this.message = message;
        }

        //数据源长度
        @Override
        public int getCount() {
            return message.size();
        }
        //每一行绑定的数据源
        @Override
        public Object getItem(int position) {
            return message.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        //item 类型数量
        @Override
        public int getViewTypeCount() {
            return TYPE_ACCEPT+1;
        }

        //每个类型对应的int类型的值 必须从0开始
        @Override
        public int getItemViewType(int position) {
            if(message.get(position).isSended()){
                return TYPE_SEND;//发送类型
            }
            return TYPE_ACCEPT;//接收类型
        }
        //获取每一行的View
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            Message msg = message.get(position);
            ViewHolder viewHolder;
            if(convertView == null){
                //从xml加载view
                viewHolder = new ViewHolder();
                if(type == TYPE_SEND){  //发送消息
                    convertView = inflater.inflate(R.layout.item_message_chat_send,null);
                }else if (type == TYPE_ACCEPT){ //接收消息
                    convertView = inflater.inflate(R.layout.item_message_chat_accept,null);
                }
                viewHolder.text1 = convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.text1.setText(msg.getContent());
            return convertView;
        }
        private class ViewHolder{
            private TextView text1;
        }

    }



}
