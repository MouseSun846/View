package com.example.view;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
        Log.i("mouse"," super(context);");
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("mouse","super(context, attrs);");
    }

    public void onMeasure(int  widthMeasureSpec,int heightMeasureSpec){
        //获得ViewGroup计算模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取ViewGroup宽高
        int sizewidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeheight = MeasureSpec.getSize(heightMeasureSpec);
        //测量所有子View的宽高
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        Log.i("mouse","测量宽度："+sizewidth+"  测量高度："+sizeheight);
        //如果没有子View，当前ViewGroup的宽高直接设置 为0
        if(getChildCount() <= 0){
           setMeasuredDimension(0,0);
        }else if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST){
            int measuredWidth = 0;
            int maxMeasuredHeight = 0; //测量高度子View 最大的高度
            for (int i = 0; i < getChildCount(); i++) {
                View child =getChildAt(i);
//                measuredWidth += child.getMeasuredWidth();
                MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
                measuredWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                if (child.getMeasuredHeight() > maxMeasuredHeight){
                    //如果当前的View大于之后View的高度
                    maxMeasuredHeight = child.getMeasuredHeight();
                }
            }
            setMeasuredDimension(measuredWidth,maxMeasuredHeight);

        }else {
            setMeasuredDimension(sizewidth,sizeheight);
        }
        setMeasuredDimension(sizewidth,sizeheight);

    }/**
     onMeasure方法执行两遍后再执行onLayout方法
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取子View的数量
        int count =getChildCount();
        int left = 0;
        for (int i = 0; i <count ; i++) {
            View child = getChildAt(i);
            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
            //获取子 View的宽度
            int childWidth = child.getMeasuredWidth();
            //获取子View的高度
            int childHeight = child.getMeasuredHeight();
            if (lp.position == MyLayoutParams.POSITION_RIGHT){
                //当前子View显示在ViewGroup右边
                child.layout(getWidth() - childWidth,0,getWidth(),childHeight);
            }else if (lp.position == MyLayoutParams.POSITION_BOTTOM){
                //当前子View显示在ViewGroup底部
                child.layout(left + lp.leftMargin,getHeight()-childHeight,left+childWidth+lp.leftMargin,getHeight());
            }else {
                //没有设置位置的View
                child.layout(left+lp.leftMargin,0,left+childWidth+lp.leftMargin,child.getMeasuredHeight());
            }
            //设置摆放的位置
//            child.layout(left,0,left+childWidth,childHeight);
//            left+=childWidth;
            left += childWidth + lp.leftMargin + lp.rightMargin;
        }

    }

    @Override
    public  LayoutParams generateLayoutParams(AttributeSet attrs){
        Log.i("mouse","generateLayoutParams");
        return new MyLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MyLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MyLayoutParams;
    }
}
