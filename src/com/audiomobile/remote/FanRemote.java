package com.audiomobile.remote;



import com.audiomobile.data.Value;
import com.audiomobile.ircore.KeyTreate;
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


public class FanRemote extends Fragment implements OnClickListener {
	

	private View remoteView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		remoteView = inflater.inflate(R.layout.remote_fan, null);
		
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = (int) (dm.widthPixels/4);
//		int screenHeight = dm.heightPixels;
		
		Button fan_key0 = (Button) remoteView.findViewById(R.id.fan_key0);
		fan_key0.setOnClickListener(this);
//		fan_key0.setWidth(screenWidth);
//		fan_key0.setHeight((screenHeight) / 11);
		
		
		Button fan_key1 = (Button) remoteView.findViewById(R.id.fan_key1);
		fan_key1.setOnClickListener(this);
//		fan_key1.setWidth(screenWidth);
//		fan_key1.setHeight((screenHeight) / 11);
//		
		
		Button fan_key2 = (Button) remoteView.findViewById(R.id.fan_key2);
		fan_key2.setOnClickListener(this);
//		fan_key2.setWidth(screenWidth);
//		fan_key2.setHeight((screenHeight) / 11);
		
		
		Button fan_key3 = (Button) remoteView.findViewById(R.id.fan_key3);
		fan_key3.setOnClickListener(this);
//		fan_key3.setWidth(screenWidth);
//		fan_key3.setHeight((screenHeight) / 11);
//		
		
		Button fan_key4 = (Button) remoteView.findViewById(R.id.fan_key4);
		fan_key4.setOnClickListener(this);
//		fan_key4.setWidth(screenWidth);
//		fan_key4.setHeight((screenHeight) / 11);
	
		
		Button fan_key5 = (Button) remoteView.findViewById(R.id.fan_key5);
		fan_key5.setOnClickListener(this);
//		fan_key5.setWidth(screenWidth);
//		fan_key5.setHeight((screenHeight) / 11);
	
		
		Button fan_key6 = (Button) remoteView.findViewById(R.id.fan_key6);
		fan_key6.setOnClickListener(this);
//		fan_key6.setWidth(screenWidth);
//		fan_key6.setHeight((screenHeight) / 11);
		
		
		Button fan_key7 = (Button) remoteView.findViewById(R.id.fan_key7);
		fan_key7.setOnClickListener(this);
//		fan_key7.setWidth(screenWidth);
//		fan_key7.setHeight((screenHeight) / 11);
	
		Button fan_key8 = (Button) remoteView.findViewById(R.id.fan_key8);
		fan_key8.setOnClickListener(this);
//		fan_key8.setWidth(screenWidth);
//		fan_key8.setHeight((screenHeight) / 11);
		
		
		Button fan_key9 = (Button) remoteView.findViewById(R.id.fan_key9);
		fan_key9.setOnClickListener(this);
//		fan_key9.setWidth(screenWidth);
//		fan_key9.setHeight((screenHeight) / 11);
//	
		
		Button fan_power = (Button) remoteView.findViewById(R.id.fan_power);
		fan_power.setOnClickListener(this);
		fan_power.setWidth(screenWidth);
//		fan_power.setHeight((screenHeight) / 11);
//	
		
		Button fan_timer = (Button) remoteView.findViewById(R.id.fan_timer);
		fan_timer.setOnClickListener(this);
//		fan_timer.setWidth(screenWidth);
//		fan_timer.setHeight((screenHeight) / 11);
	
		
		Button fan_li = (Button) remoteView.findViewById(R.id.fan_li);
		fan_li.setOnClickListener(this);
//		fan_li.setWidth(screenWidth);
//		fan_li.setHeight((screenHeight) / 11);
	
		Button fan_cool = (Button) remoteView.findViewById(R.id.fan_cool);
		fan_cool.setOnClickListener(this);
//		fan_cool.setWidth(screenWidth);
//		fan_cool.setHeight((screenHeight) / 11);
		
		
		Button fan_mode = (Button) remoteView.findViewById(R.id.fan_mode);
		fan_mode.setOnClickListener(this);
//		fan_mode.setWidth(screenWidth);
//		fan_mode.setHeight((screenHeight) / 11);
		
		
		Button fan_sleep = (Button) remoteView.findViewById(R.id.fan_sleep);
		fan_sleep.setOnClickListener(this);
//		fan_sleep.setWidth(screenWidth);
//		fan_sleep.setHeight((screenHeight) / 11);
	
		
		Button fan_light = (Button) remoteView.findViewById(R.id.fan_light);
		fan_light.setOnClickListener(this);
//		fan_light.setWidth(screenWidth);
//		fan_light.setHeight((screenHeight) / 11);
	
		
		Button fan_speed = (Button) remoteView.findViewById(R.id.fan_speed);
		fan_speed.setOnClickListener(this);
//		fan_speed.setWidth(screenWidth);
//		fan_speed.setHeight((screenHeight) / 11);
		
		
		Button fan_speedlow = (Button) remoteView.findViewById(R.id.fan_speedlow);
		fan_speedlow.setOnClickListener(this);
//		fan_speedlow.setWidth(screenWidth);
//		fan_speedlow.setHeight((screenHeight) / 11);
		
		
		Button fan_speedmiddle = (Button) remoteView.findViewById(R.id.fan_speedmiddle);
		fan_speedmiddle.setOnClickListener(this);
//		fan_speedmiddle.setWidth(screenWidth);
//		fan_speedmiddle.setHeight((screenHeight) / 11);
//		
		
		Button fan_speedhight = (Button) remoteView.findViewById(R.id.fan_speedhight);
		fan_speedhight.setOnClickListener(this);
//		fan_speedhight.setWidth(screenWidth);
//		fan_speedhight.setHeight((screenHeight) / 11);
	
		
		Button fan_freq = (Button) remoteView.findViewById(R.id.fan_freq);
		fan_freq.setOnClickListener(this);
//		fan_freq.setWidth(screenWidth);
//		fan_freq.setHeight((screenHeight) / 11);
		return remoteView;

	}
	@Override
	public void onStart()
	{
		super.onStart();
	
	}
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.fan_key0:
			Value.currentKey = "fan_key0";
			break;
		case R.id.fan_key1:
			Value.currentKey = "fan_key1";
			break;
		case R.id.fan_key2:
			Value.currentKey = "fan_key2";
			break;
		case R.id.fan_key3:
			Value.currentKey = "fan_key3";
			break;
		case R.id.fan_key4:
			Value.currentKey = "fan_key4";
			break;
		case R.id.fan_key5:
			Value.currentKey = "fan_key5";
			break;
		case R.id.fan_key6:
			Value.currentKey = "fan_key6";
			break;
		case R.id.fan_key7:
			Value.currentKey = "fan_key7";
			break;
		case R.id.fan_key8:
			Value.currentKey = "fan_key8";
			break;
		case R.id.fan_key9:
			Value.currentKey = "fan_key9";
			break;
		case R.id.fan_sleep:
			Value.currentKey = "fan_sleep";
			break;
		case R.id.fan_power:
			Value.currentKey = "fan_power";
			break;
		case R.id.fan_mode:
			Value.currentKey = "fan_mode";
			break;
		case R.id.fan_cool:
			Value.currentKey = "fan_cool";
			break;
		case R.id.fan_li:
			Value.currentKey = "fan_li";
			break;
		case R.id.fan_timer:
			Value.currentKey = "fan_timer";
			break;
		case R.id.fan_speed:
			Value.currentKey = "fan_speed";
			break;
		case R.id.fan_light:
			Value.currentKey = "fan_light";
			break;
		case R.id.fan_speedlow:
			Value.currentKey = "fan_speedlow";
			break;
		case R.id.fan_speedmiddle:
			Value.currentKey = "fan_speedmiddle";
			break;
		case R.id.fan_speedhight:
			Value.currentKey = "fan_speedhight";
			break;
		case R.id.fan_freq:
			Value.currentKey = "fan_freq";
			break;
		default:
			Value.currentKey = null;
		}
		if (Value.currentKey != null) {
			try {
				KeyTreate.getInstance().keyHandler(Value.currentDevice ,Value.currentKey);	

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}