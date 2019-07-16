package com.example.view.MyApplication;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.Stack;

public class MyApplication extends Application {
    private static Stack<Activity> activityStack;   //activity栈
    /**
     * 添加Activityy到堆栈
     */
    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        if (!activityStack.contains(activity)){
            Log.i("mouse","添加Activity"+activity.getLocalClassName());
            activityStack.add(activity);
        }
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 删除一个Activity
     */
    public void removeActivity(Activity activity){
        if (activity != null && activityStack.contains(activity)){
            Log.i("mouse","删除Activity:"+activity.getLocalClassName());
            activityStack.remove(activity);
        }
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity(){
        for (int i = 0,size = activityStack.size(); i <size ; i++) {
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        Log.i("mouse","结束所有Activity");
    }
}
