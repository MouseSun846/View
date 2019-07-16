package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import static java.lang.Thread.sleep;


public class MyView extends View{
    private Thread mythread;
    private Paint paint; //画笔
    private RectF rectF = new RectF(400,0,800,380);
    private int sweepAngle = 0; //弧度当前度数
    private int sweepAngleAdd = 20; //弧度每次增加的次数
    private Random random = new Random();
    private boolean running = true; //控制循环

    public MyView(Context context){
        this(context,null);
    }
    public  MyView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context,attrs);
    }


    //初始化
    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.customStyleView);
        sweepAngleAdd = typedArray.getInt(R.styleable.customStyleView_sweepAngleAdd,0);
        typedArray.recycle();       //使用后进行回收
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextSize(30);

    }

    protected void onDraw(Canvas canvas){

        if(null == mythread){
            mythread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running){
                        logic();
                        postInvalidate();//重新绘制，会调用onDraw
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            mythread.start();
        }
        else {
//            canvas.drawRoundRect(rectF,10,10,paint);
            int x = random.nextInt(200);
            int y = random.nextInt(480);

            canvas.drawText("Canvas Demo",x,y,paint);
            canvas.drawLine(80,280,x,y,paint);
            canvas.drawArc(rectF,0,sweepAngle,true,paint);
        }
    }

    protected void logic(){
        sweepAngleAdd = random.nextInt(100);
        sweepAngle += sweepAngleAdd; //每次增加弧度
//        Log.d("logic","weepAngle  "+sweepAngle);
        //随机设置画笔的颜色
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        paint.setARGB(255,r,g,b);
        if(sweepAngle >= 360  ) {
            sweepAngle = 0;
        }
    }

    protected void onDetachedFromWindow(){
        running = false;        //销毁View,退出无线循环
        super.onDetachedFromWindow();
    }



    protected  void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
         //获得父容器为它设置的测量模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY){//指定宽度或者match parent
            width = widthSize;
        }else {
            width = (int) (getPaddingLeft() + getPaddingRight()  +rectF.width() * 2);
        }

        if (heightMode == MeasureSpec.EXACTLY){//指定高度或者match parent
            height = heightSize;
        }else {
            height = (int) (getPaddingTop() + getPaddingBottom() + rectF.height() * 2);
        }
        setMeasuredDimension(width,height);

    }
}
