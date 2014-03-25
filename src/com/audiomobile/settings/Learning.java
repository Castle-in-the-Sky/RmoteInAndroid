package com.audiomobile.settings;

import java.util.Timer;
import java.util.TimerTask;

import com.audiomobile.data.Value;

import com.audiomobile.ircore.Decode;
import com.audiomobile.ircore.DecodeWatch;

import com.audiomobile.main.BaseActivity;


import com.audiomobile.remote.R;
import com.database.UserDB;
import com.etek.ircomm.SendOut;

import android.content.Intent;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Learning extends BaseActivity implements OnClickListener {
//	LearningHandler learningHandler;
	// Decode dc;
	TextView learnInfo;
//	RemoteSendOut rmtSend;
	private SurfaceView sfv;
	private Paint mPaint;
//	DecodeWatch codeWatch = new DecodeWatch();
	Decode	decode = new Decode();
//	UserDB mUserDB;
	private static final String TAG = "Learning";
	Timer timer;
	int d = 0;
	boolean isShow = false;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
//		mUserDB = new UserDB(this);
		/* set it to be full screen */
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.learning);
	
//		sfv = (SurfaceView) this.findViewById(R.id.decode_show);
//		mPaint = new Paint();
//		mPaint.setColor(Color.GRAY);
//		mPaint.setStrokeWidth(1);
	

		ImageView studyingCancel = (ImageView) findViewById(R.id.quit_learn);
		studyingCancel.setOnClickListener(this);
//		Log.e(TAG, "hightpixels is " + dm.heightPixels);
		
		// studyExit.setWidth((screenWidth) / 3);
		// studyExit.setHeight((screenHeight) / 10);
//		Log.e(TAG, "sfv.getHeight() ---> " + sfv.getHeight());
//		Log.e(TAG, "codewatch baseline ---> " + codeWatch.baseLine);
		// codeWatch.baseLine=sfv.getHeight()/2;
		// Log.v(TAG , "codewatch baseline ---> " + codeWatch.baseLine);
		// codeWatch.Start(sfv,mPaint);
//		learningHandler = new LearningHandler();
//	if (isShow){
		decode.initOscilloscope();
		decode.Start();
		decode.setHandler(learnHandler);
//		decode.initOscilloscope(dm.heightPixels/3);
//		decode.Start(sfv, mPaint);
//		decode.setHandler(learnHandler);
//	}else {
//		code.Start();
//		code.initOscilloscope();
//		code.setHandler(learnHandler);
//	}
		
		
		TimerTask task = new TimerTask(){    
			public void run(){    
//				Log.e(TAG, "time   ---> " + d);
				//d++;
				
				Bundle b = new Bundle();
				b.putString("decode", "timeout");
				setResult(RESULT_CANCELED, getIntent().putExtras(b)); 	
				finish();
//				byte[] cmdData = new byte[10];
//				cmdData[0] = Value.AudioCommand.START_LEARN;
//				cmdData[1] = Value.AudioCommand.START_LEARN;
//				cmdData[2] = Value.AudioCommand.START_LEARN;
//				cmdData[3] = Value.AudioCommand.START_LEARN;
//				cmdData[4] = Value.AudioCommand.START_LEARN;
//				cmdData[5] = Value.AudioCommand.START_LEARN;
//				
//				RemoteSendOut.rmtStrSend(cmdData);
			}    
			};    
			timer = new Timer();  
			timer.schedule(task, 30000);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
//		Log.e(TAG, "onStart ---> ");
		byte[] cmdData = new byte[10];
		cmdData[0] = Value.AudioCommand.START_LEARN;
		cmdData[1] = Value.AudioCommand.START_LEARN;
		cmdData[2] = Value.AudioCommand.START_LEARN;
		cmdData[3] = Value.AudioCommand.START_LEARN;
		cmdData[4] = Value.AudioCommand.START_LEARN;
		cmdData[5] = Value.AudioCommand.START_LEARN;
		if (!Value.powerSupply){
		SendOut.rmtByteSend(cmdData);
		}
		
	}

	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
//		codeWatch.Stop();
		decode.Stop();
//		sfv = null;
//		mPaint = null;
		timer.cancel();//销毁定时器
		Log.e(TAG, "onStop ---> ");
		// dc.stop();
	}
	
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Log.e(TAG, "onResume ---> ");
		// dc.stop();
	}
	
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		Log.e(TAG, "onPause ---> ");
		// dc.stop();
	}
	
	

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.quit_learn:
			// RemoteCommunicate.remoteaudiomobileLearnStop();
			Bundle b = new Bundle();
			b.putString("decode", "cancel");
			setResult(RESULT_CANCELED, getIntent().putExtras(b)); 	
//			Value.isStudying = false;
//			setResult(RESULT_CANCELED, getIntent().putExtras(bundle));
//			byte[] cmdData = new byte[2];
//			cmdData[0] = Value.AudioCommand.STOP_LEARN;
//			RemoteSendOut.cmdSend(cmdData);
		
			finish();

			break;

		}
	}

	

	private class StudyThread extends Thread {
		int status;

		@Override
		public void run() {
			// status= RemoteCommunicate.learnaudiomobileRemoteLoop(30);

			Message msg = new Message();
			Bundle bundle = new Bundle();
			bundle.putInt("status", status);
			msg.setData(bundle);
		

		}
	}

	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//			Value.isStudying = false;
			// setResult(RESULT_CANCELED, getIntent().putExtras(bundle));
			// byte[] cmdData = new byte[10];
			// cmdData[0] = Value.AudioCommand.STOP_LEARN;
			// rmtSend.cmdSend(cmdData);
//			byte[] cmdData = new byte[2];
//			cmdData[0] = Value.AudioCommand.STOP_LEARN;
//			RemoteSendOut.cmdSend(cmdData);
		
			 Bundle b = new Bundle();
			 b.putString("decode", "timeout");
			setResult(RESULT_CANCELED, getIntent().putExtras(b)); 
			finish();
			return true;

		}

		return super.dispatchKeyEvent(event);
	}
	
	

	private final Handler learnHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {

			case R.id.FINISH_CODE:
				Log.d(TAG, "finish the code");
				String data = msg.getData().getString("decode");
				 Bundle b = msg.getData();  
				if ("DecodeError".equals(data)){
		//			Log.v(TAG, "error");
					setResult(RESULT_CANCELED, getIntent().putExtras(b)); 
				}else {
					setResult(RESULT_OK, getIntent().putExtras(b)); 
				}
//				String str1 = msg.getData().getString("decode");
//				// recMsgTV.setText(str1);
//				learnInfo.setText(str1);
				finish();
				break;
			case R.id.LEARN_STOP:
				//Toast.makeText(this, getResources().getString(R.string.out_time), Toast.LENGTH_LONG).show();
				break;
			case R.id.LEARN_TIMEOUT:

				break;
			case R.id.LEARN_READ:

				break;

			default:
				break;
			}
		}
	};
}