package com.example.view.Navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.view.R;

/**
 * 动态
 * 
 * @author Ansen
 * @create time 2015-09-08
 */
public class DynamicFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dynamic, null);
		return rootView;
	}
}
