package com.example.view.Navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.view.R;

/**
 * 首页
 * @author Ansen
 * @create time 2015-09-08
 */
public class HomeFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, null);
		return rootView;
	}
}
