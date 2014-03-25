package com.audiomobile.remote;



import com.audiomobile.data.Value;
import com.audiomobile.ircore.KeyTreate;
import com.audiomobile.remote.R;




import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;


public class AvRemote extends Fragment implements OnClickListener {
//	TextView keyValueIndex;
	
	private View remoteView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		remoteView = inflater.inflate(R.layout.remote_dvd, null);
		
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = (int) (dm.widthPixels/4);
		
		
		
		Button dvd_av = (Button)remoteView.findViewById(R.id.dvd_av);
		dvd_av.setOnClickListener(this);
	/*	dvd_av.setWidth(screenWidth);
		dvd_av.setHeight((screenHeight) / 10);*/
		
		
		Button dvd_back = (Button)remoteView.findViewById(R.id.dvd_back);
		dvd_back.setOnClickListener(this);
//		dvd_back.setWidth(screenWidth);
//		dvd_back.setHeight((screenHeight) / 10);
		
		
		Button dvd_down = (Button)remoteView.findViewById(R.id.dvd_down);
		dvd_down.setOnClickListener(this);
//		dvd_down.setWidth(screenWidth);
//		dvd_down.setHeight((screenHeight) / 10);
		
		
		Button dvd_downsong = (Button)remoteView.findViewById(R.id.dvd_downsong);
		dvd_downsong.setOnClickListener(this);
//		dvd_downsong.setWidth(screenWidth);
//		dvd_downsong.setHeight((screenHeight) / 10);
		
		
		Button dvd_left = (Button)remoteView.findViewById(R.id.dvd_left);
		dvd_left.setOnClickListener(this);
//		dvd_left.setWidth(screenWidth);
//		dvd_left.setHeight((screenHeight) / 10);
	
		
		Button dvd_menu = (Button)remoteView.findViewById(R.id.dvd_menu);
		dvd_menu.setOnClickListener(this);
//		dvd_menu.setWidth(screenWidth);
//		dvd_menu.setHeight((screenHeight) / 10);
		
		
		Button dvd_mute = (Button)remoteView.findViewById(R.id.dvd_mute);
		dvd_mute.setOnClickListener(this);
//		dvd_mute.setWidth(screenWidth);
//		dvd_mute.setHeight((screenHeight) / 10);
		
		
		Button dvd_ok = (Button)remoteView.findViewById(R.id.dvd_ok);
		dvd_ok.setOnClickListener(this);
//		dvd_ok.setWidth(screenWidth);
//		dvd_ok.setHeight((screenHeight) / 10);
		
		
		Button dvd_open = (Button)remoteView.findViewById(R.id.dvd_open);
		dvd_open.setOnClickListener(this);
//		dvd_open.setWidth(screenWidth);
//		dvd_open.setHeight((screenHeight) / 10);
		
		
		Button dvd_pause = (Button)remoteView.findViewById(R.id.dvd_pause);
		dvd_pause.setOnClickListener(this);
//		dvd_pause.setWidth(screenWidth);
//		dvd_pause.setHeight((screenHeight) / 10);
		
		
		Button dvd_play = (Button)remoteView.findViewById(R.id.dvd_play);
		dvd_play.setOnClickListener(this);	
//		dvd_play.setWidth(screenWidth);
//		dvd_play.setHeight((screenHeight) / 10);
	
		
		Button dvd_power = (Button)remoteView.findViewById(R.id.dvd_power);
		dvd_power.setOnClickListener(this);
		dvd_power.setWidth(screenWidth);
//		dvd_power.setHeight((screenHeight) / 10);
	
		
		Button dvd_quickback = (Button)remoteView.findViewById(R.id.dvd_quickback);
		dvd_quickback.setOnClickListener(this);
//		dvd_quickback.setWidth(screenWidth);
//		dvd_quickback.setHeight((screenHeight) / 10);

		
		Button dvd_quickforward = (Button)remoteView.findViewById(R.id.dvd_quickforward);
		dvd_quickforward.setOnClickListener(this);
//		dvd_quickforward.setWidth(screenWidth);
//		dvd_quickforward.setHeight((screenHeight) / 10);

		
		Button dvd_right = (Button)remoteView.findViewById(R.id.dvd_right);
		dvd_right.setOnClickListener(this);
//		dvd_right.setWidth(screenWidth);
//		dvd_right.setHeight((screenHeight) / 10);

		
		Button dvd_stop = (Button)remoteView.findViewById(R.id.dvd_stop);
		dvd_stop.setOnClickListener(this);
//		dvd_stop.setWidth(screenWidth);
//		dvd_stop.setHeight((screenHeight) / 10);

		
		Button dvd_title = (Button)remoteView.findViewById(R.id.dvd_title);
		dvd_title.setOnClickListener(this);
//		dvd_title.setWidth(screenWidth);
//		dvd_title.setHeight((screenHeight) / 10);

		
		Button dvd_up = (Button)remoteView.findViewById(R.id.dvd_up);
		dvd_up.setOnClickListener(this);
//		dvd_up.setWidth(screenWidth);
//		dvd_up.setHeight((screenHeight) / 10);
	
		
		Button dvd_upsong = (Button)remoteView.findViewById(R.id.dvd_upsong);
		dvd_upsong.setOnClickListener(this);
//		dvd_upsong.setWidth(screenWidth);
//		dvd_upsong.setHeight((screenHeight) / 10);
	
		
//		 keyValueIndex = (TextView) remoteView.findViewById(R.id.dvd_showkey);
//		 keyValueIndex.setTextSize(24);
//		 keyValueIndex.setWidth(screenWidth);
//		 keyValueIndex.setHeight((screenHeight) / 10);
		return remoteView;

	}
	@Override
	public void onStart()
	{
		super.onStart();
	
		
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
	
		

		case R.id.dvd_av:
			Value.currentKey =  "dvd_av";
			break;
		case R.id.dvd_back:
			Value.currentKey = "dvd_back";
			break;
		case R.id.dvd_down:
			Value.currentKey ="dvd_down";
			break;
		case R.id.dvd_downsong:
			Value.currentKey = "dvd_downsong";
			break;
		case R.id.dvd_left:
			Value.currentKey ="dvd_left";
			break;
		case R.id.dvd_menu:
			Value.currentKey = "dvd_menu";
			break;
		case R.id.dvd_mute:
			Value.currentKey ="dvd_mute";
			break;
		case R.id.dvd_open:
			Value.currentKey ="dvd_open";
			break;
		case R.id.dvd_pause:
			Value.currentKey ="dvd_pause";
			break;
		case R.id.dvd_play:
			Value.currentKey ="dvd_play";
			break;
		case R.id.dvd_ok:
			Value.currentKey = "dvd_ok";
			break;
		case R.id.dvd_power:
			Value.currentKey ="dvd_power";
			break;
		case R.id.dvd_quickback:
			Value.currentKey = "dvd_quickback";
			break;
		case R.id.dvd_quickforward:
			Value.currentKey = "dvd_quickforward";
			break;
		case R.id.dvd_right:
			Value.currentKey = "dvd_right";
			break;
		case R.id.dvd_title:
			Value.currentKey = "dvd_title";
			break;
		case R.id.dvd_up:
			Value.currentKey = "dvd_up";
			break;
		case R.id.dvd_upsong:
			Value.currentKey = "dvd_upsong";
			break;
		case R.id.dvd_stop:
			Value.currentKey = "dvd_stop";
			break;
		default:
			Value.currentKey = null;
		}
		if (Value.currentKey != null) {
			KeyTreate.getInstance().keyHandler(Value.currentDevice ,Value.currentKey);	

			
		}
	}
}