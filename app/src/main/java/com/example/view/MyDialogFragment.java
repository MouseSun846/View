package com.example.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MyDialogFragment extends DialogFragment {
    private DialogInterface.OnClickListener onClickListener;
    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_dialog, container, false);
//    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.plant).setTitle("Choose ?");
        if (onClickListener != null){
            builder.setPositiveButton("OK",onClickListener);
            builder.setNegativeButton("Cancel",onClickListener);
            builder.setNeutralButton("Not Care",onClickListener);
            builder.setMessage("你好吗？");
        }
        return builder.create();
    }
    public void setOnClickListener(DialogInterface.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
