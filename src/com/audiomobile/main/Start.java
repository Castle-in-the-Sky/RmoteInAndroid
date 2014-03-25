package com.audiomobile.main;


import com.audiomobile.receiver.HeadsetPlugReceiver;

import com.audiomobile.remote.R;
import com.audiomobile.data.Value;
import com.etek.ircomm.SendOut;



import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

public class Start extends BaseActivity {
	private final int SPLASH_DISPLAY_LENGHT = 2000;//Integer.valueOf(this.getString(R.string.splash_delay_value));
	private HeadsetPlugReceiver headsetPlugReceiver;
	 
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Value.screenHeight= dm.widthPixels;
		Value.screenWidth  = dm.heightPixels;
		Value.mAppContext = getApplicationContext();
		
		 if (retrieveStringFromPreff("power", "false").equals("true")) {
				Value.powerSupply = true;
			} else {
				Value.powerSupply = false;
			}
		 SendOut.rmtInitial();
  //      startService(new Intent(this, RemoteUpdateService.class));
        new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent mainIntent = new Intent(Start.this,
						Main.class);
				startActivity(mainIntent);
				finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	
	
	
}
