package com.audiomobile.settings;

import java.util.ArrayList;

import java.util.regex.Pattern;

import com.audiomobile.remote.R;
import com.audiomobile.data.AirData;
import com.audiomobile.data.RemoteData;
import com.audiomobile.data.RemoteDatabase;
import com.audiomobile.data.Value;
import com.audiomobile.main.BaseActivity;
import com.audiomobile.ui.DropDown;
import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.utils.Tools;
import com.database.IRDataBase;
import com.database.RemoteDB;
import com.database.UserDB;


import com.etek.ircomm.RemoteComm;

import com.etek.ircomm.SendOut;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.content.Context;
import android.content.Intent;


import android.util.Log;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.Window;

import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.widget.Button;

import android.widget.TextView;

public class RemoteSelection extends BaseActivity implements OnClickListener {
	final static String TAG = "RemoteSelection";
	// private EffectThread mThread = null;

	private static int allCount = 0;
	private static int mCutCount = 0;
	private AirData ad;
	AutoThread mThread = null;

//	private TextView mBrandName = null;
//
//	private static TextView mCurrentCount = null;

	private int mType = 0;
	private String mTypeName = "";
	String mBrand = "";
	SendOut rmtSend;
	private ArrayList<String> productList = new ArrayList<String>();
	private ArrayList<String> productName = new ArrayList<String>();
	private RemoteDB mRmtDB;
	// private UserDB mUsertDB;
	RemoteData rmtData = new RemoteData();
	// Button autoButton;
	// Button stopButton;

	private Button pravious;

	private Button next;

	private Button tryagain;

	private Button yes;

	private Button auto;

	ImageView back;
	TextView brand;

	TextView type;

	TextView model;

	boolean isAuto = false;
	boolean isRunning = false;

	private String[] typeStr;
	private String[] remoteListSTr;

	private CheckBox switchCheckbox;
	LinearLayout autoLayout;
	LinearLayout menualLayout;
	Context mContext;
	private ImageView codeSending;
	static int mColumn = 0;
	// static DropDown remoteDropDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.remote_select);
		mContext = this;
		ad = new AirData(5003, 1, 24, 1, 1, 1, 0, 0);
//		Intent intent = getIntent();
		ApplicationContext.getInstance().addSetupsActivity(this);
//		rmtSend  = new RemoteSendOut();
//		rmtSend.rmtInitial();
		//	mType = getIntent().getIntExtra("type", 0);
//			mType = Value.remote.getType();
//		// i.getStringExtra("brand");
//		mType = 0;
		// i.getIntExtra("type", 0);
		// database instruction
		mRmtDB = new RemoteDB(getApplicationContext());
		// mUsertDB = new UserDB(getApplicationContext());
		mType = Value.remote.getType();
		mTypeName = Value.RemoteType[mType];
		mBrand = Value.remote.getBrand();
		 Log.v(TAG, mBrand);
		 if ("zh".equals( Value.localLanguage)){
		 mBrand = mBrand.substring(0,mBrand.length()-1);
		 }
//		 Log.v(TAG, Value.localLanguage);
//		 Log.v(TAG, mBrand);
	//	mBrand = "长虹";
		typeStr = getApplicationContext().getResources().getStringArray(
				R.array.type_array);
		//

		// mCurrentCount = (TextView) findViewById(R.id.index_count);

		getProducts(mTypeName, mBrand);
		remoteListSTr = reWriteStr(productName);

//		for (int i = 0; i < remoteListSTr.length; i++) {
//			Log.v(TAG, "remoteListSTr [" + i + "]--->" + remoteListSTr[i]);
//		}
		// remoteListSTr = new String[productName.size()];
		// productName.toArray(remoteListSTr);
		mColumn = getColumn();
		initView();
		initButton();

		// remoteDropDown = (DropDown) findViewById(R.id.remote_dropDown);
		mCutCount = 0;
		// Set the items of the dropdown
		// remoteDropDown.setItems(remoteListSTr);
		// remoteDropDown.setSelection(mCutCount);
		Log.v(TAG, "mcutcount----- " + mCutCount + "mCount----- " + allCount);
		// updataCountIndex(mCutCount);
		// AudioRemoteComm.init();
		// mThread = new AutoThread();
		// mThread.start();
	}

	void initView() {
		brand = (TextView) findViewById(R.id.brand);
		brand.setText(mBrand);
		type = (TextView) findViewById(R.id.type);
		type.setText(typeStr[mType]);
		model = (TextView) findViewById(R.id.model);
		model.setText(remoteListSTr[0]);

		brand.setTypeface(RemoteSelection.tf_bold);
		type.setTypeface(RemoteSelection.tf_bold);
		model.setTypeface(RemoteSelection.tf_bold);

		autoLayout = (LinearLayout) findViewById(R.id.okcancelLayout);
		menualLayout = (LinearLayout) findViewById(R.id.okcancelLayout2);
		switchCheckbox = (CheckBox) findViewById(R.id.switch_checkbox);
		switchCheckbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							isAuto = true;

							autoLayout.setVisibility(View.VISIBLE);
							menualLayout.setVisibility(View.INVISIBLE);
							isRunning = false;
							mThread = null;

						} else {
							isAuto = false;
							autoLayout.setVisibility(View.INVISIBLE);
							menualLayout.setVisibility(View.VISIBLE);
							isRunning = false;
							mThread = null;
							
					//		mThread.cancel();
						}
					}
				});
		codeSending = (ImageView) findViewById(R.id.code_test_sending);
	}

	void initButton() {

		pravious = (Button) findViewById(R.id.pravious);
		pravious.setOnClickListener(this);

		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(this);
		tryagain = (Button) findViewById(R.id.tryagain);
		tryagain.setOnClickListener(this);
		yes = (Button) findViewById(R.id.yes);
		yes.setOnClickListener(this);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		auto = (Button) findViewById(R.id.auto);
		auto.setOnClickListener(this);
	}

	private void getProducts(String _type, String _brand) {
		String brandName = null;
		mRmtDB.open();
		try {
			brandName = mRmtDB.getBrandOriginal(_brand);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			productName = mRmtDB.getProducts(_type, brandName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			productList = mRmtDB.getProductsIndex(_type, brandName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mRmtDB.close();
		allCount = productList.size();
	}

	String getTestCode(int count) {
		String index = productList.get(count);
		String testCode = "";
		if (mType != Value.DeviceType.TYPE_AIR) {

			RemoteData rmtData = null;
			try {
				rmtData = IRDataBase.getSampleKeyData(mType, index, mColumn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Log.v(TAG, "rmtData  ====>   " + rmtData.getRemoteData());
			// Log.v(TAG, "current index ====>   "+ index);
			 testCode = RemoteComm.encodeRemoteData(rmtData);
			// showCodeSending();
			// RemoteComm.ETEKSendRemote(testCode);
//			AudioRemoteComm.SendRemote(testCode);
		} else {

			int id = Integer.parseInt(index);
			// Log.v(TAG, "air index data ->"+ id);
			ad.setCode(id);
			
			testCode =	RemoteComm.getAirData(ad);
			
//			if (keyColumn == 2) {
//				ad.setmPower(0);
//			} else {
//				ad.setmPower(1);
//			}
//			AudioRemoteComm.sendAirRemote(ad);

		}
		return testCode;
	}



	@Override
	public void onStart() {
		super.onStart();
		// mRmtDB.open();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mThread != null) {
			
			mThread = null;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		return super.onKeyDown(keyCode, event);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	public void onClick(View arg0) {
		Message message = new Message();
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.yes:
			String index = productList.get(mCutCount);
		//	Log.v(TAG, "code index is " + index);
			Value.remote.setCode(index);
//			Log.v(TAG, Value.remote.getCode());
//			Log.v(TAG, Value.remote.getFullInfo());
//			Log.v(TAG, Value.remote.getInfo());
			// save(mCutCount);
			Intent i = new Intent(RemoteSelection.this, RemoteTest.class);
//			i.putExtra("type", 0);
//			i.putExtra("name", "电视机");
//			i.putExtra("brand", "长虹");
//			i.putExtra("index", productList.get(mCutCount));
			
			startActivity(i);
			
			
			break;
		case R.id.tryagain:
			message.what = R.id.AUTO_UPDATE_UI;

			sendHandler.sendMessage(message);
			break;
			
		case R.id.pravious:
			mCutCount--;
			if (mCutCount < 0) {
				mCutCount = allCount - 1;
			}
			isRunning = false;
			message.what = R.id.AUTO_UPDATE_UI;

			sendHandler.sendMessage(message);
			// sendTestCode(mCutCount);
			// sendTestCode(mCutCount,pressKey(0));
			// updataCountIndex(mCutCount);

			break;
		case R.id.next:
			mCutCount++;
			if (mCutCount > allCount - 1) {
				mCutCount = 0;
			}
			isRunning = false;
			message.what = R.id.AUTO_UPDATE_UI;

			sendHandler.sendMessage(message);
			// sendTestCode(mCutCount,pressKey(0));
			// sendTestCode(mCutCount,);
			// updataCountIndex(mCutCount);

			break;
		case R.id.auto:
			if (isRunning) {
				isRunning = false;
				mThread = null;
				auto.setBackgroundResource(R.drawable.greentap_selector);
				auto.setText(R.string.continu);
				
			} else {
				if (mThread ==null){
				mThread = new AutoThread();
				mThread.start();
				}
				isRunning = true;
				auto.setBackgroundResource(R.drawable.greybutton_selector);
				auto.setText(R.string.cancel);
				
			}
			// sendTestCode(mCutCount,pressKey(0));
			// sendTestCode(mCutCount,);
			// updataCountIndex(mCutCount);

			break;

		case R.id.back:
			finish();
			break;
		default:
			break;

		}

	}

	// public static void updataCountIndex(int mCurCount){
	// mCurrentCount.setText("    (" + String.valueOf(mCurCount + 1) + "/" +
	// String.valueOf(mCount)
	// + ")     ");
	// // remoteDropDown.setSelection(mCurCount);
	// mCutCount = mCurCount;
	// return ;
	// }
	public String[] reWriteStr(ArrayList<String> str) {
		String[] newStr = new String[str.size()];
		int count = str.size();
		for (int i = 0; i < count; i++) {
			String tempStr = str.get(i);
			if (isNumeric(tempStr)) {
				if (Integer.valueOf(tempStr) < 250) {

					tempStr = getResources().getString(R.string.general)
							+ tempStr;
				}
			}
			newStr[i] = tempStr;
		}
		return newStr;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public void setModelText(String text) {
		model.setText(text);
		ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 1.5F,
				1.0F, 1.5F, model.getWidth() / 2.0F, model.getHeight() / 2.0F);
		localScaleAnimation.setDuration(400L);
		localScaleAnimation.setRepeatCount(1);
		localScaleAnimation.setRepeatMode(2);
		model.startAnimation(localScaleAnimation);
	}
	Handler sendHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case R.id.AUTO_UPDATE_UI:
				setModelText(remoteListSTr[mCutCount]);
				if (Value.exist ) {
	        		//			Log.v(TAG, "remote data -->" + remoteData);
	        					
//	        					if (retrieveStringFromPreff("vibration", "true")=="true") {
//	        						Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//	        						long[] pattern = { 100, 100 };
//	        						vibrator.vibrate(pattern, 1);
//	        					}
//	        					sendTestCode(getTestCode(mCutCount));
					SendOut.sendRemoteCode(codeSending,getTestCode(mCutCount));
	        				}
				
				break;
			}
			super.handleMessage(msg);
		}
	};
	

	private class AutoThread extends Thread {
		

	

		

		public void run() {
			while (isRunning ) {
				//
			//	Log.e(TAG, "auto runing" + isRunning);
				Message message = new Message();
				message.what = R.id.AUTO_UPDATE_UI;

				sendHandler.sendMessage(message);
				mCutCount++;
				if (mCutCount==allCount)
					mCutCount = 0;
				Log.v(TAG, "mCutCount" + mCutCount);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
//			Log.v(TAG, "not running ");
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
	}

	
//	private void sendTestCode(final String code)
//	
//	  {
//		
//	    if (!code.equals(""))
//	    {
//	      AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
//	      localAlphaAnimation.setDuration(200L);
//	      localAlphaAnimation.setAnimationListener(new AnimationListener()
//	      {
//	      
//			public void onAnimationEnd(Animation paramAnimation)
//	        {
//	        
//	        	rmtSend.rmtStrSend(code);
//	     
//	        	AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
//	            localAlphaAnimation.setDuration(200L);
//	            localAlphaAnimation.setAnimationListener(new AnimationListener()
//	            {
//	              public void onAnimationEnd(Animation paramAnimation)
//	              {
//	                codeSending.setVisibility(View.GONE);
//	              }
//
//	              public void onAnimationRepeat(Animation paramAnimation)
//	              {
//	              }
//
//	              public void onAnimationStart(Animation paramAnimation)
//	              {
//	              }
//	            });
//	            codeSending.startAnimation(localAlphaAnimation);
//	            
//	         
//	          
//	        }
//
//	        public void onAnimationRepeat(Animation paramAnimation)
//	        {
//	        }
//
//	        public void onAnimationStart(Animation paramAnimation)
//	        {
//	          codeSending.setVisibility(View.VISIBLE);
//	        }
//	      });
//	      codeSending.startAnimation(localAlphaAnimation);
//	    }
//	  }
	
	public  int getColumn(){ 
		//	String keyValue = "tv_mute";
			int keyColumn = 10;
			switch (mType) {
			case Value.DeviceType.TYPE_TV:
					keyColumn = 10;
				
				break;
				
			case Value.DeviceType.TYPE_DVD:
				
					keyColumn = 7;
					
				break;
			case Value.DeviceType.TYPE_STB:
				
					keyColumn = 8;
					
				break;
				
			case Value.DeviceType.TYPE_PJT:
				
					keyColumn = 4;
					
				break;
			case Value.DeviceType.TYPE_FAN:
			
					keyColumn = 4;
				
				break;
			case Value.DeviceType.TYPE_AIR:
				
					keyColumn = 0;
					
				break;
			default:
					keyColumn = 10;
				break;
			}
//			Log.v(TAG, keyValue);
//			mRmtDB.open();
//			int keyColumn = mRmtDB.getKeyColumn(keyValue)+4;
//			mRmtDB.close();
			Log.v(TAG, "keyColumn -------->" + keyColumn);
		    return keyColumn;
		 } 
}
