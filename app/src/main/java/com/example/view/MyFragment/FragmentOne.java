package com.example.view.MyFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.view.R;


public class FragmentOne extends Fragment {
    private View rootView;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (rootView !=null){
            rootView.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("mosue","Fragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i("mouse","Fragment  onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("mouse","Fragment  onDetach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("mouse","Fragment  onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("mouse","Fragment  onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("mouse","Fragment  onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("mouse","Fragment  onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("mouse","Fragment  onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("mouse","Fragment  onPause");
    }

}
