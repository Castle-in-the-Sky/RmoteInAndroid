package com.audiomobile.remote;



import com.audiomobile.data.Value;
import com.audiomobile.ircore.KeyTreate;
import com.audiomobile.remote.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.TextView;


public class STBRemote extends Fragment implements OnClickListener {

	private View remoteView;

	//TextView keyValueIndex;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		remoteView = inflater.inflate(R.layout.remote_stb, null);
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = (int) (dm.widthPixels/4);


		
		
		Button stb_key1 = (Button) remoteView.findViewById(R.id.stb_key1);
		stb_key1.setOnClickListener(this);
//		stb_key1.setWidth(screenWidth);
//		stb_key1.setHeight((screenHeight) / 11);
//		stb_key1.setBackgroundResource(R.drawable.button);
		
		Button stb_key2 = (Button) remoteView.findViewById(R.id.stb_key2);
		stb_key2.setOnClickListener(this);
//		stb_key2.setWidth(screenWidth);
//		stb_key2.setHeight((screenHeight) / 11);
//		stb_key2.setBackgroundResource(R.drawable.button);
		
		Button stb_key3 = (Button) remoteView.findViewById(R.id.stb_key3);
		stb_key3.setOnClickListener(this);
//		stb_key3.setWidth(screenWidth);
//		stb_key3.setHeight((screenHeight) / 11);
//		stb_key3.setBackgroundResource(R.drawable.button);
		
		Button stb_wait = (Button) remoteView.findViewById(R.id.stb_wait);
		stb_wait.setOnClickListener(this);
		stb_wait.setWidth(screenWidth);
//		stb_wait.setHeight((screenHeight) / 11);
//		stb_wait.setBackgroundResource(R.drawable.button_power);
		
		Button stb_key4 = (Button) remoteView.findViewById(R.id.stb_key4);
		stb_key4.setOnClickListener(this);
//		stb_key4.setWidth(screenWidth);
//		stb_key4.setHeight((screenHeight) / 11);
//		stb_key4.setBackgroundResource(R.drawable.button);
		
		Button stb_key5 = (Button) remoteView.findViewById(R.id.stb_key5);
		stb_key5.setOnClickListener(this);
//		stb_key5.setWidth(screenWidth);
//		stb_key5.setHeight((screenHeight) / 11);
//		stb_key5.setBackgroundResource(R.drawable.button);
//		
		Button stb_key6 = (Button) remoteView.findViewById(R.id.stb_key6);
		stb_key6.setOnClickListener(this);
//		stb_key6.setWidth(screenWidth);
//		stb_key6.setHeight((screenHeight) / 11);
//		stb_key6.setBackgroundResource(R.drawable.button);
		
		Button stb_chadd = (Button) remoteView.findViewById(R.id.stb_chadd);
		stb_chadd.setOnClickListener(this);
//		stb_chadd.setWidth(screenWidth);
//		stb_chadd.setHeight((screenHeight) / 11);
//		stb_chadd.setBackgroundResource(R.drawable.button);
		
		Button stb_key7 = (Button) remoteView.findViewById(R.id.stb_key7);
		stb_key7.setOnClickListener(this);
//		stb_key7.setWidth(screenWidth);
//		stb_key7.setHeight((screenHeight) / 11);
//		stb_key7.setBackgroundResource(R.drawable.button);
		
		Button stb_key8 = (Button) remoteView.findViewById(R.id.stb_key8);
		stb_key8.setOnClickListener(this);
//		stb_key8.setWidth(screenWidth);
//		stb_key8.setHeight((screenHeight) / 11);
//		stb_key8.setBackgroundResource(R.drawable.button);
		
		Button stb_key9 = (Button) remoteView.findViewById(R.id.stb_key9);
		stb_key9.setOnClickListener(this);
//		stb_key9.setWidth(screenWidth);
//		stb_key9.setHeight((screenHeight) / 11);
//		stb_key9.setBackgroundResource(R.drawable.button);
		
		Button stb_chsub = (Button) remoteView.findViewById(R.id.stb_chsub);
		stb_chsub.setOnClickListener(this);
//		stb_chsub.setWidth(screenWidth);
//		stb_chsub.setHeight((screenHeight) / 11);
//		stb_chsub.setBackgroundResource(R.drawable.button);
		
		Button stb_voladd = (Button) remoteView.findViewById(R.id.stb_voladd);
		stb_voladd.setOnClickListener(this);
//		stb_voladd.setWidth(screenWidth);
//		stb_voladd.setHeight((screenHeight) / 11);
//		stb_voladd.setBackgroundResource(R.drawable.button);
		
		Button stb_key0 = (Button) remoteView.findViewById(R.id.stb_key0);
		stb_key0.setOnClickListener(this);
//		stb_key0.setWidth(screenWidth);
//		stb_key0.setHeight((screenHeight) / 11);
//		stb_key0.setBackgroundResource(R.drawable.button);
		
		Button stb_volsub = (Button) remoteView.findViewById(R.id.stb_volsub);
		stb_volsub.setOnClickListener(this);
//		stb_volsub.setWidth(screenWidth);
//		stb_volsub.setHeight((screenHeight) / 11);
//		stb_volsub.setBackgroundResource(R.drawable.button);
		
		
		
		Button stb_up = (Button) remoteView.findViewById(R.id.stb_up);
		stb_up.setOnClickListener(this);
//		stb_up.setWidth(screenWidth);
//		stb_up.setHeight((screenHeight) / 11);
//		stb_up.setBackgroundResource(R.drawable.button);
		
		Button stb_down = (Button) remoteView.findViewById(R.id.stb_down);
		stb_down.setOnClickListener(this);
//		stb_down.setWidth(screenWidth);
//		stb_down.setHeight((screenHeight) / 11);
//		stb_down.setBackgroundResource(R.drawable.button);
		
		Button stb_left = (Button) remoteView.findViewById(R.id.stb_left);
		stb_left.setOnClickListener(this);
//		stb_left.setWidth(screenWidth);
//		stb_left.setHeight((screenHeight) / 11);
//		stb_left.setBackgroundResource(R.drawable.button);
		
		Button stb_right = (Button) remoteView.findViewById(R.id.stb_right);
		stb_right.setOnClickListener(this);
//		stb_right.setWidth(screenWidth);
//		stb_right.setHeight((screenHeight) / 11);
//		stb_right.setBackgroundResource(R.drawable.button);
		
		Button stb_ok = (Button) remoteView.findViewById(R.id.stb_ok);
		stb_ok.setOnClickListener(this);
//		stb_ok.setWidth(screenWidth);
//		stb_ok.setHeight((screenHeight) / 11);
//		stb_ok.setBackgroundResource(R.drawable.button);
//		
		Button stb_back = (Button) remoteView.findViewById(R.id.stb_back);
		stb_back.setOnClickListener(this);
//		stb_back.setWidth(screenWidth);
//		stb_back.setHeight((screenHeight) / 11);
//		stb_back.setBackgroundResource(R.drawable.button);
		
		Button stb_menu = (Button) remoteView.findViewById(R.id.stb_menu);
		stb_menu.setOnClickListener(this);
//		stb_menu.setWidth(screenWidth);
//		stb_menu.setHeight((screenHeight) / 11);
//		stb_menu.setBackgroundResource(R.drawable.button);
		
//		 keyValueIndex = (TextView) remoteView.findViewById(R.id.stb_showkey);
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
		switch (v.getId()) {
		
		case R.id.stb_key0:
			Value.currentKey = "stb_key0";
			break;
		case R.id.stb_key1:
			Value.currentKey ="stb_key1";
			break;
		case R.id.stb_key2:
			Value.currentKey = "stb_key2";
			break;
		case R.id.stb_key3:
			Value.currentKey ="stb_key3";
			break;
		case R.id.stb_key4:
			Value.currentKey = "stb_key4";
			break;
		case R.id.stb_key5:
			Value.currentKey = "stb_key5";
			break;
		case R.id.stb_key6:
			Value.currentKey = "stb_key6";
			break;
		case R.id.stb_key7:
			Value.currentKey = "stb_key7";
			break;
		case R.id.stb_key8:
			Value.currentKey = "stb_key8";
			break;
		case R.id.stb_key9:
			Value.currentKey = "stb_key9";
			break;
		case R.id.stb_wait:
			Value.currentKey = "stb_wait";
			break;
//		case R.id.stb_watch:
//			Value.currentKey = "stb_watch";
//			break;
		case R.id.stb_voladd:
			Value.currentKey = "stb_voladd";
			break;
		case R.id.stb_volsub:
			Value.currentKey = "stb_volsub";
			break;
		case R.id.stb_chadd:
			Value.currentKey = "stb_chadd";
			break;
		case R.id.stb_chsub:
			Value.currentKey = "stb_chsub";
			break;
		case R.id.stb_up:
			Value.currentKey = "stb_up";
			break;
		case R.id.stb_down:
			Value.currentKey = "stb_down";
			break;
		case R.id.stb_left:
			Value.currentKey = "stb_left";
			break;
		case R.id.stb_right:
			Value.currentKey = "stb_right";
			break;
		case R.id.stb_ok:
			Value.currentKey = "stb_ok";
			break;
		case R.id.stb_back:
			Value.currentKey = "stb_back";
			break;
		case R.id.stb_menu:
			Value.currentKey = "stb_menu";
			break;
		default:
			Value.currentKey = null;
		}
		if (Value.currentKey != null) {
			KeyTreate.getInstance().keyHandler(Value.currentDevice ,Value.currentKey);	
//			KeyValue kv = Value.keyValueTab.get(Value.currentKey);
//			if (kv.getIsLearned()==1){
//			keyValueIndex.setText("Learn key value     " +Value.currentKey);
//			keyValueIndex.setTextColor(Color.RED);
//			}
//			else {
//				keyValueIndex.setText("normal key value     " +Value.currentKey);
//				keyValueIndex.setTextColor(Color.BLUE);	
//			}
			
		}
	}
}
