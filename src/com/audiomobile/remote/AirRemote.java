package com.audiomobile.remote;





import com.audiomobile.data.AirData;
import com.audiomobile.data.Value;
import com.audiomobile.ircore.KeyTreate;

import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.utils.MyAppInfo;
import com.audiomobile.remote.R;
import com.database.UserDB;



import android.app.Activity;
import android.content.Intent;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AirRemote extends Fragment implements OnClickListener {
	private static final String TAG = "AirRemote";
	TextView airShow;
	ImageView airMode;
	ImageView airWindDir;	
	ImageView airWind;
	LinearLayout airBg;
	byte    airKey;
	private View remoteView;
	private AirData airData;
	private int current;
	
	// private boolean mIsOpen = false;
	// private boolean mIsOther = false;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		airData = new AirData(5003, 1, 24, 0, 1, 1, 1, 0);
		remoteView = inflater.inflate(R.layout.remote_air, null);
		
		
		Typeface type= Typeface.createFromAsset(getActivity().getAssets(),"fonts/lcd.TTF");
		DisplayMetrics dm = new DisplayMetrics();
		
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		int screenWidth = dm.widthPixels;
	//	int screenHeight = dm.heightPixels;

		Button air_power = (Button) remoteView.findViewById(R.id.air_power);
		air_power.setOnClickListener(this);
		air_power.setWidth((screenWidth) / 3);

		Button air_mode = (Button) remoteView.findViewById(R.id.air_mode);
		air_mode.setOnClickListener(this);
		
	

		Button air_tempadd = (Button) remoteView.findViewById(R.id.air_tempadd);
		air_tempadd.setOnClickListener(this);
	
		
		
		Button air_tempsub = (Button) remoteView.findViewById(R.id.air_tempsub);
		air_tempsub.setOnClickListener(this);
		
		

		Button air_wind_auto_dir = (Button) remoteView.findViewById(R.id.air_wind_auto_dir);
		air_wind_auto_dir.setOnClickListener(this);
		
		

		Button air_wind_count = (Button) remoteView.findViewById(R.id.air_wind_count);
		air_wind_count.setOnClickListener(this);
		
		

		Button air_wind_dir = (Button) remoteView.findViewById(R.id.air_wind_dir);
		air_wind_dir.setOnClickListener(this);
		
		
		
		 airShow=((TextView) remoteView.findViewById(R.id.temp_show));
		 airShow.setTypeface(type);
		
		// airShow.setText("25");
		 airMode=((ImageView) remoteView.findViewById(R.id.image_mode));
	
		// airMode.setText("cold");
		 airWindDir=((ImageView) remoteView.findViewById(R.id.image_wind_dir));
		
		// airWindDir.setText("vertical");
		 airWind=((ImageView) remoteView.findViewById(R.id.image_wind_count));
		 airBg = (LinearLayout) remoteView.findViewById(R.id.air_window);
		 return remoteView;
		// airWind.setText("low");
		
	}
	@Override
	public void onStart()
	{
		super.onStart();
//		airData = new AirData();
//		UserDB mUserDB = new UserDB(Value.mAppContext);
//		airData = Value.rmtDevs.get(Value.currentDevice).getAirData();
//		Log.v(TAG, airData.getInfo());
//		mUserDB.open();
//	//	airData.setCode(5003);
//		mUserDB.getAirData(Value.currentDevice);
//		airData = MyAppInfo.getAirInfo(getActivity().getBaseContext(),Value.currentDevice);
//		Log.v(TAG, "current is " + Value.currentDevice);
//		Log.v(TAG, airData.getInfo());
//		airShow.setText(getTempStr(airData));
//		airMode.setBackgroundResource(getMode(airData));
//		airWindDir.setBackgroundResource(getWindDir(airData));
//		airWind.setBackgroundResource(getWind(airData));
//		mUserDB.close();
	}
	
	public void onResume()
	{
		super.onResume();
//		airData = new AirData();
//		UserDB mUserDB = new UserDB(Value.mAppContext);
//		airData = Value.rmtDevs.get(Value.currentDevice).getAirData();
//		Log.v(TAG, airData.getInfo());
//		mUserDB.open();
//	//	airData.setCode(5003);
//		mUserDB.getAirData(Value.currentDevice);
		current = Value.currentDevice;
		airData = MyAppInfo.getAirInfo(getActivity().getBaseContext(),current);
		Log.v(TAG, "current is " + current);
		Log.v(TAG, airData.getInfo());
		airShow.setText(getTempStr(airData));
		airMode.setBackgroundResource(getMode(airData));
		airWindDir.setBackgroundResource(getWindDir(airData));
		airWind.setBackgroundResource(getWind(airData));
//		mUserDB.close();
	}
	public void onPause()
	{
		super.onPause();
//		airData = new AirData();
//		UserDB mUserDB = new UserDB(Value.mAppContext);
//		airData = Value.rmtDevs.get(Value.currentDevice).getAirData();
//		Log.v(TAG, airData.getInfo());
//		mUserDB.open();
//	//	airData.setCode(5003);
//		mUserDB.getAirData(Value.currentDevice);
		 MyAppInfo.saveAirInfo(getActivity().getBaseContext(),airData,current);
		Log.v(TAG, "current is " + current);
		Log.v(TAG, airData.getInfo());
//		mUserDB.close();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		UserDB mUserDB = new UserDB(Value.mAppContext);
//		mUserDB.open();
////		mUserDB.updateAirData(Value.currentDevice);
//		mUserDB.close();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		UserDB mUserDB = new UserDB(Value.mAppContext);
//		mUserDB.open();
//	//	mUserDB.updateAirData(Value.currentDevice);
//		mUserDB.close();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			if (event.isLongPress()) {
		//		openOptionsMenu();
				return true;
			}
			return true;
		} else {
			return false;
		//	return super.onKeyDown(keyCode, event);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(airData.getmPower() == 0x00 && v.getId() != R.id.air_power)
		{
			return;
		}
		int data;
		String currentKey = "";
		switch (v.getId()) {
		
		case R.id.air_mode:
			currentKey  = "air_mode";
			data= airData.getmMode();
			data++;
			
			if(data>4){
				data = 0;
			}
			airData.setmMode(data);
			
			airKey = 1;
			airData.setMkey(airKey);
			// mIsOther = true;
			break;
		case R.id.air_power:
			currentKey ="air_power";
			if(airData.getmPower()==0){
				airData.setmPower(1);
			}else{
				airData.setmPower(0);
			}
			airKey = 0;
			airData.setMkey(airKey);
			break;
		case R.id.air_tempadd:
			currentKey = "air_tempadd";
			data= airData.getmTmp();
			data++;
			
			if(data>30){
				data = 30;
			}
			airData.setmTmp(data);
			airKey = 3;
			airData.setMkey(airKey);
			break;
		case R.id.air_tempsub:
			currentKey = "air_tempsub";
			data= airData.getmTmp();
			data--;
			
			if(data<16){
				data = 16;
			}
			airData.setmTmp(data);
			airKey =4 ;
			airData.setMkey(airKey);
			break;
		case R.id.air_wind_auto_dir:
			currentKey = "air_wind_auto_dir";
			if(airData.getmWindAuto()==0){
				airData.setmWindAuto(1);
			}else{
				airData.setmWindAuto(0);
			}
			airKey = 6;
			airData.setMkey(airKey);
			break;
		case R.id.air_wind_count:
			currentKey ="air_wind_count";
			data= airData.getmWindCount();
			data++;
			
			if(data>3){
				data = 0;
			}
			airData.setmWindCount(data);
			airKey = 2;
			airData.setMkey(airKey);
			break;
		case R.id.air_wind_dir:
			data=airData.getmWindDir();
			data++;
			if(data>3){
				data = 0;
			}
			
			airData.setmWindDir(data);
			airData.setmWindAuto(0);
			airKey = 5;
			airData.setMkey(airKey);
			break;
		default:
			break;
		}
		if (currentKey != null) {
//		RemoteCommunicate.sendaudiomobileAirRemote(airData);	
			KeyTreate.getInstance().airKeyHandler(airData);	
			
		}
	
		airShow.setText(getTempStr(airData));
		airMode.setBackgroundResource(getMode(airData));
		airWindDir.setBackgroundResource(getWindDir(airData));
		airWind.setBackgroundResource(getWind(airData));
		
	}
	
	
	
	
	public  int getMode(AirData airdata) {
		int mMode = 0;
		if (airdata.getmPower() == 0x01) {
			airBg.setBackgroundResource(R.drawable.air_bg_on);
			if (airdata.getmMode() == 0x00) {
				mMode = R.drawable.mode_auto;
			}
			if (airdata.getmMode()  == 0x01) {
				mMode = R.drawable.mode_cold;
			}
			if (airdata.getmMode()  == 0x02) {
				mMode = R.drawable.mode_drying;
			}
			if (airdata.getmMode()  == 0x03) {
				mMode = R.drawable.mode_wind;
			}
			if (airdata.getmMode()  == 0x04) {
				mMode = R.drawable.mode_warm;
			}
		}
		else{
			airBg.setBackgroundResource(R.drawable.air_bg_off);
			mMode = 0;	
		}
		
		return mMode;
	}
	public  int getWind(AirData airdata) {
		int mWind = 0;
		
		if (airdata.getmPower() == 0x01) {
		//	mWindStr = getString(R.string.air_wind_count);
			if (airdata.getmWindCount() == 0x00) {
				mWind = R.drawable.wind_auto;
			}
			if (airdata.getmWindCount()  == 0x01) {
				mWind = R.drawable.wind_1;
			}
			if (airdata.getmWindCount()  == 0x02) {
				mWind = R.drawable.wind_2;
			}
			if (airdata.getmWindCount()  == 0x03) {
				mWind = R.drawable.wind_3;
			}
			
		}
		else{
		//	mWindStr = "";	
		}
		
		return mWind;
	}
	

	public  int getWindDir(AirData airdata) {
		int mWindDir = 0;
		
		if (airdata.getmPower() == 0x01) {
			
			if (airdata.getmWindAuto() == 0x00){
			if (airdata.getmWindDir() == 0x00) {
				mWindDir = R.drawable.wind_horizontal_colourful;
			}else if (airdata.getmWindDir()  == 0x01) {
				mWindDir = R.drawable.wind_horizontal_colourful;
			}else if (airdata.getmWindDir()  == 0x02) {
				mWindDir = R.drawable.wind_horizontal_colourful;
			}else if (airdata.getmWindDir()  == 0x03) {
				mWindDir = R.drawable.wind_horizontal_colourful;
			}
			}else {
				mWindDir = R.drawable.wind_vertical_colourful;
			}
					
		}
		else{
		
		}
		
		return mWindDir;
	}
	
	
	
	public  String getTempStr(AirData airdata) {
		String mWindStr = null;
		if (airdata.getmPower() == 0x01) {
			mWindStr=String.valueOf(airData.getmTmp())+"℃";
					
		}
		else{
			mWindStr = "";	
		}
		
		return mWindStr;
	}
	
	
	
}