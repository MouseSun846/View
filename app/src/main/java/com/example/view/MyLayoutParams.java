package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class MyLayoutParams extends MarginLayoutParams {
    public static int POSITION_RIGHT = 1; //右边
    public static int POSITION_BOTTOM = 2; //底部
    public int position = -1;
    public MyLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
        TypedArray a = c.obtainStyledAttributes(attrs,R.styleable.CustomLayoutLP);
        position = a.getInt(R.styleable.CustomLayoutLP_layout_position,position);
        a.recycle();
    }
    public MyLayoutParams(int width, int height) {
        super(width, height);
    }

    public MyLayoutParams(LayoutParams source) {
        super(source);
    }


}

