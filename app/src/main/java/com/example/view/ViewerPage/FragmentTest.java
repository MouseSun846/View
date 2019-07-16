package com.example.view.ViewerPage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.view.R;

/**
 * 通过这个类进行页面控件的内容填充
 */
@SuppressLint("ValidFragment")
public class FragmentTest extends Fragment {
    private String content;
    private int backgroundResourceId;
    public FragmentTest(String content, int backgroundResourceId){
        this.content = content;
        this.backgroundResourceId = backgroundResourceId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test,null);
        TextView textView = view.findViewById(R.id.tv_content);
        textView.setText(content);
        view.setBackgroundResource(backgroundResourceId);
        view.setRotation(-90);
        return view;
    }
}
