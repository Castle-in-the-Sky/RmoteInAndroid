package com.audiomobile.remote;







import com.audiomobile.data.Value;
import com.audiomobile.ircore.KeyTreate;
import com.audiomobile.ui.ImageViewOpaque;
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
import android.view.View.OnLongClickListener;
import android.widget.Button;




public class TVRemote extends Fragment implements OnClickListener,
		OnLongClickListener {
    final static String TAG = "TVActivity";
	//NumButton numberButtonWindows;
//	FuncationButton funButtonWindows;
	private View remoteView;
	private String currentKey;
	
//	TextView keyValueIndex;
//	String TVCodeIndex;
//	HashMap<String , KeyValue> keyTab = new HashMap<String , KeyValue>();   
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		remoteView = inflater.inflate(R.layout.remote_tv, null);
		
		
		
		
		
//		Log.v(TAG, "dm widthpixels--->"+screenWidth+"       dm heightPixels ------>"+ screenHeight);
		Button tv_key0 = (Button) remoteView.findViewById(R.id.tv_key0);
		tv_key0.setOnClickListener(this);
		
	
	
		

		Button tv_key1 = (Button) remoteView.findViewById(R.id.tv_key1);
		tv_key1.setOnClickListener(this);
//		tv_key1.setWidth(screenWidth);
//		tv_key1.setHeight((screenHeight) / 10);
	//	tv_key1.getBackground().setAlpha(50);
		
		
		Button tv_key2 = (Button) remoteView.findViewById(R.id.tv_key2);
		tv_key2.setOnClickListener(this);
//		tv_key2.setWidth(screenWidth);
//		tv_key2.setHeight((screenHeight) / 10);
	//	tv_key2.getBackground().setAlpha(50);
		
		
		Button tv_key3 = (Button) remoteView.findViewById(R.id.tv_key3);
		tv_key3.setOnClickListener(this);
//		tv_key3.setWidth(screenWidth);
//		tv_key3.setHeight((screenHeight) / 10);
	//	tv_key3.getBackground().setAlpha(50);
		
		Button tv_key4 = (Button) remoteView.findViewById(R.id.tv_key4);
		tv_key4.setOnClickListener(this);
//		tv_key4.setWidth(screenWidth);
//		tv_key4.setHeight((screenHeight) / 10);
		

		Button tv_key5 = (Button) remoteView.findViewById(R.id.tv_key5);
		tv_key5.setOnClickListener(this);
//		tv_key5.setWidth(screenWidth);
//		tv_key5.setHeight((screenHeight) / 10);
//	

		Button tv_key6 = (Button) remoteView.findViewById(R.id.tv_key6);
		tv_key6.setOnClickListener(this);
//		tv_key6.setWidth(screenWidth);
//		tv_key6.setHeight((screenHeight) / 10);
//		

		Button tv_key7 = (Button) remoteView.findViewById(R.id.tv_key7);
		tv_key7.setOnClickListener(this);
//		tv_key7.setWidth(screenWidth);
//		tv_key7.setHeight((screenHeight) / 10);
//	

		Button tv_key8 = (Button) remoteView.findViewById(R.id.tv_key8);
		tv_key8.setOnClickListener(this);
//		tv_key8.setWidth(screenWidth);
//		tv_key8.setHeight((screenHeight) / 10);
//		

		Button tv_key9 = (Button) remoteView.findViewById(R.id.tv_key9);
		tv_key9.setOnClickListener(this);
//		tv_key9.setWidth(screenWidth);
//		tv_key9.setHeight((screenHeight) / 10);
//		
		
		Button tv_10 = (Button) remoteView.findViewById(R.id.tv_key10);
		tv_10.setOnClickListener(this);
//		tv_10.setWidth(screenWidth);
//		tv_10.setHeight((screenHeight) / 10);
//	

		Button tv_power = (Button) remoteView.findViewById(R.id.tv_power);
		tv_power.setOnClickListener(this);
//		tv_power.setWidth(screenWidth);
//		tv_power.setHeight((screenHeight) / 10);
		

		Button tv_av = (Button) remoteView.findViewById(R.id.tv_av);
		tv_av.setOnClickListener(this);
//		tv_av.setWidth(screenWidth);
//		tv_av.setHeight((screenHeight) / 10);
	

		Button tv_mute = (Button) remoteView.findViewById(R.id.tv_mute);
		tv_mute.setOnClickListener(this);
//		tv_mute.setWidth(screenWidth);
//		tv_mute.setHeight((screenHeight) / 10);
//		

		Button tv_back = (Button) remoteView.findViewById(R.id.tv_back);
		tv_back.setOnClickListener(this);
//		tv_back.setWidth(screenWidth);
//		tv_back.setHeight((screenHeight) / 10);
//		
		ImageViewOpaque tv_chadd = (ImageViewOpaque) remoteView.findViewById(R.id.chplus);
		tv_chadd.setOnClickListener(this);
//		tv_chadd.setWidth(screenWidth);
//		tv_chadd.setHeight((screenHeight) / 10);
//	

		ImageViewOpaque tv_chsub = (ImageViewOpaque) remoteView.findViewById(R.id.chminus);
		tv_chsub.setOnClickListener(this);
		
		

		ImageViewOpaque tv_voladd = (ImageViewOpaque) remoteView.findViewById(R.id.volplus);
		tv_voladd.setOnClickListener(this);
//		tv_voladd.setOnLongClickListener(this);
//		tv_voladd.setWidth(screenWidth);
//		tv_voladd.setHeight((screenHeight) / 10);
		

		ImageViewOpaque tv_volsub = (ImageViewOpaque) remoteView.findViewById(R.id.volminus);
		tv_volsub.setOnClickListener(this);
//		tv_volsub.setWidth(screenWidth);
//		tv_volsub.setHeight((screenHeight) / 10);
		

		ImageViewOpaque tv_ok = (ImageViewOpaque) remoteView.findViewById(R.id.ok);
		tv_ok.setOnClickListener(this);
		ImageViewOpaque tv_up = (ImageViewOpaque) remoteView.findViewById(R.id.arrowup);
		tv_up.setOnClickListener(this);
		ImageViewOpaque tv_bottom = (ImageViewOpaque) remoteView.findViewById(R.id.arrowbottom);
		tv_bottom.setOnClickListener(this);
		ImageViewOpaque tv_left = (ImageViewOpaque) remoteView.findViewById(R.id.arrowleft);
		tv_left.setOnClickListener(this);
		ImageViewOpaque tv_right = (ImageViewOpaque) remoteView.findViewById(R.id.arrowright);
		tv_right.setOnClickListener(this);
//		tv_ok.setWidth(screenWidth);
//		tv_ok.setHeight((screenHeight) / 10);
		

		Button tv_menu = (Button) remoteView.findViewById(R.id.tv_menu);
		tv_menu.setOnClickListener(this);
//		tv_menu.setWidth((screenWidth));
//		tv_menu.setHeight((screenHeight) / 10);
//	
		
//		 keyValueIndex = (TextView) remoteView.findViewById(R.id.tv_showkey);
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
	

	public void onClick(View _view) {
		switch (_view.getId()) {
		case R.id.tv_key0:
			currentKey = "tv_key0";
		
			break;
		case R.id.tv_key1:
		
			currentKey = "tv_key1";
		
			break;
		case R.id.tv_key2:
			
			currentKey = "tv_key2";
			
			break;
		case R.id.tv_key3:
			
			currentKey = "tv_key3";
		
			break;
		case R.id.tv_key4:
			
			currentKey = "tv_key4";
			
			break;
		case R.id.tv_key5:
			
			currentKey = "tv_key5";
			
			break;
		case R.id.tv_key6:
			
			currentKey = "tv_key6";
		
			break;
		case R.id.tv_key7:
			
			currentKey = "tv_key7";
		
			break;
		case R.id.tv_key8:
			
			currentKey = "tv_key8";
			
			break;
		case R.id.tv_key9:
		
			currentKey = "tv_key9";
			
			break;
		case R.id.tv_key10:
			
			currentKey = "tv_key10";
			
			break;
		case R.id.tv_power:
		
			currentKey = "tv_power";
		
			break;
		case R.id.tv_av:
		
			currentKey = "tv_av";
		
			break;
		case R.id.tv_mute:
		
			currentKey = "tv_mute";
		
			break;
		case R.id.tv_back:
			
			currentKey = "tv_back";
		
			break;
		case R.id.chplus:
		
			currentKey = "tv_chadd";
		
			break;
		case R.id.chminus:
			
			currentKey = "tv_chsub";
			
			break;
		case R.id.volplus:
			
			currentKey = "tv_voladd";
		
			break;
		case R.id.volminus:
			
			currentKey = "tv_volsub";
		
			break;
		case R.id.ok:
		
			currentKey = "tv_ok";
		
			break;
		case R.id.tv_menu:
		
			currentKey = "tv_menu";
			
			break;
		case R.id.arrowup:
			
			currentKey = "tv_up";
			break;
		case R.id.arrowbottom:
			
			currentKey = "tv_down";
			break;
		case R.id.arrowleft:
			
			currentKey = "tv_left";
			break;
		case R.id.arrowright:
			
			currentKey = "tv_right";
			break;	
			
		default:
			
			currentKey = null;
			
			break;
		}
		if (currentKey != null) {
			KeyTreate.getInstance().keyHandler(Value.currentDevice ,currentKey);	
//			numberButtonWindows = new NumButton(this, itemsOnClick);
//			numberButtonWindows.showAtLocation(this.remoteView.findViewById(R.id.tv_main_view), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//			funButtonWindows = new FuncationButton(this, itemsOnClick);
//			funButtonWindows.showAtLocation(this.remoteView.findViewById(R.id.tv_main_view), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

			
		}
	}

	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		default:
		
		}
		return false;
	}
	

	
	
//	public boolean dispatchKeyEvent(KeyEvent event) { 
//		if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) { 
//		Log.v(TAG, "key menu ");
//		return true; 
//		} 
//		return super.dispatchKeyEvent(event); 
//		} 
}
