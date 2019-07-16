package com.example.view.RecycleView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleView extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    List<String> data;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  final int PULL_TO_REFRESH = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.my_fresh_swipe);
        //设置监听状态
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置下拉刷新的箭头的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        initdata();
        recyclerView = findViewById(R.id.recycleView);
        //构建样式
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecycleView.this));
        //构建item的边界
        recyclerView.addItemDecoration(new MyDecoration(this,LinearLayoutManager.VERTICAL,R.color.colorAccent,10));
        adapter = new RecyclerViewAdapter(MyRecycleView.this,data);
        recyclerView.setAdapter(adapter);
        //设置滑动监听事件
        recyclerView.addOnScrollListener(onScrollListener);

    }

    private void initdata(){
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Time time =  new Time();

            if (i % 2 == 0){
                time.setToNow();
                data.add("当前时间  "+time.format("%Y-%m-%d T %H:%M:%S.000"));
            }else {
                data.add("对不起，我错了"+i);
            }

        }
    }

    //绘制Item的边界
    class MyDecoration extends RecyclerView.ItemDecoration{
        private int morientation = LinearLayoutManager.VERTICAL;
        private int mItemSize = 1;  //item之间的分割线的宽度
        private Paint mpaint;    //绘制item分割线的画笔，并设置其属性
        /**
         *
         * @param context
         * @param orientation 绘制方向
         * @param dividerColor  分割线颜色，颜色资源ID
         * @param mItemSize 分割线宽度，传入dp值
         */
        //绘制Item的构造函数
        public MyDecoration(Context context,int orientation,int  dividerColor,int mItemSize){
            this.morientation = orientation;
            if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL){
                try {
                    throw new IllegalAccessException("请输入正确的参数！");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //将dp值转换为px值
            this.mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mItemSize,context.getResources().getDisplayMetrics());
            mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mpaint.setColor(context.getResources().getColor(dividerColor));
        }
        public void onDraw(Canvas canvas,RecyclerView parent,RecyclerView.State state){
            if (morientation == LinearLayoutManager.VERTICAL){
                drawVertical(canvas,parent);
            }
            else if (morientation == LinearLayoutManager.HORIZONTAL){
                drawHorizontal(canvas,parent);
            }
        }

        /**
         *
         * 绘制纵向item分割线
         *
         */
        private void drawVertical(Canvas canvas,RecyclerView parent){
            final int left = parent.getPaddingLeft();
            final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            final int childsize = parent.getChildCount();
            for (int i = 0; i < childsize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + layoutParams.bottomMargin;
                final int bottom = top + mItemSize;
                canvas.drawRect(left,top,right,bottom,mpaint);
//                Log.i("mouse"," child.getBottom() "+child.getBottom()+" layoutParams.bottomMargin "+layoutParams.bottomMargin+"  top "+top+"  mItemSize "+mItemSize);
//                Log.i("mouse","Left "+left+" right "+right);
            }

        }


        /**
         *
         * 绘横向item分割线
         *
         */
        private void drawHorizontal(Canvas canvas,RecyclerView parent){
            final int top = parent.getPaddingTop();
            final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
            final int childsize = parent.getChildCount();
            for (int i = 0; i < childsize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getRight() + layoutParams.rightMargin;
                final int right = left + mItemSize;
                canvas.drawRect(left,top,right,bottom,mpaint);
            }
        }

        //设置分割线的间距
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            if (morientation == LinearLayoutManager.VERTICAL){
               //垂直排列，底部偏移
                outRect.set(0,0,0,mItemSize);
            }
            else if (morientation == LinearLayoutManager.HORIZONTAL){
                //水平排列，右边偏移
                outRect.set(0,0,mItemSize,0);
            }
        }
    }

    private RecyclerView.OnScrollListener onScrollListener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
            int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            int totalItemCount = mLayoutManager.getItemCount();
            //最后一项显示&&下滑状态的时候 加载更多
            //dy>0 下滑  dy<0 上滑
            if (lastVisibleItem >= totalItemCount-1 && dy > 0) {
                for (int i = 0; i < 10; i++) {
                    data.add("update"+i);
                }
                adapter.notifyDataSetChanged();
            }
        }
    };

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PULL_TO_REFRESH://下拉刷新
                        swipeRefreshLayout.setRefreshing(false);//false:刷新完成  true:正在刷新
                    break;
            }
        }
    };
    @Override
    public void onRefresh() {
        //延迟3000毫秒,发送空消息跟handle，handle的handleMessage方法会接收到
        handler.sendEmptyMessageDelayed(PULL_TO_REFRESH,3000);
    }

}
