package com.example.view.ViewerPage;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.view.R;

public class MyViewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_page);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPage);
        //设置缓存页数
        viewPager.setOffscreenPageLimit(2);
        //设置当前显示的item 0 表示第一个
        viewPager.setCurrentItem(0);
        //设置page垂直滑动
        viewPager.setRotation(90);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new FragmentTest("Page 1",R.color.colorAccent));
        fragmentAdapter.addFragment(new FragmentTest("Page 2",R.color.holo_green_dark));
        fragmentAdapter.addFragment(new FragmentTest("Page 3",R.color.button_press));
        fragmentAdapter.addFragment(new FragmentTest("Page 4",R.color.button_unpress));
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener =new ViewPager.OnPageChangeListener() {
       //页面滑动
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
        //页面选择
        @Override
        public void onPageSelected(int i) {
            Toast.makeText(MyViewPage.this, "当前选择了"+i+"界面", Toast.LENGTH_SHORT).show();
        }
        //页面 滑动状态改变
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
