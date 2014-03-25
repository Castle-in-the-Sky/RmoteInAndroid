package com.audiomobile.remote;




import com.audiomobile.remote.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;


public class LightRemote extends   Fragment {
	

	private View remoteView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		remoteView = inflater.inflate(R.layout.remote_light, null);
		return remoteView;
	}
	@Override
	public void onStart()
	{
		super.onStart();
	
	}
	

	
		
}