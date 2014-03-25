package com.audiomobile.settings;

import java.util.ArrayList;

import java.util.List;

import com.audiomobile.data.Globals;
import com.audiomobile.data.RemoteDatabase;
import com.audiomobile.data.RemoteDevice;
import com.audiomobile.data.Value;

import com.audiomobile.interfacenew.ConfirmDialogInterface;
import com.audiomobile.ircore.KeyTreate;
import com.audiomobile.main.BaseActivity;

import com.audiomobile.remote.AirRemote;
import com.audiomobile.remote.DVDRemote;
import com.audiomobile.remote.FanRemote;
import com.audiomobile.remote.PJTRemote;
import com.audiomobile.remote.STBRemote;
import com.audiomobile.remote.TVRemote;
import com.audiomobile.settings.Settings;
import com.audiomobile.ui.SelectPicPopupWindow;
import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.utils.MyAppInfo;

import com.audiomobile.adapt.RemoteSpinnerAdapter;
import com.audiomobile.adapt.RemoteViewPagerAdapter;
import com.audiomobile.remote.R;
import com.database.RemoteDB;
import com.database.UserDB;

import com.etek.ircomm.SendOut;

//import com.audiomobile.utils.RemoteDB;
//import com.audiomobile.utils.UseValue.remoteB;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Message;
import android.os.Vibrator;

//import android.os.Environment;
import android.os.Handler;

import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;

import android.view.LayoutInflater;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

//import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;



import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class RemoteTest extends BaseActivity implements OnTouchListener
// OnGestureListener {
{
	private static final String TAG = "TESTActivity";
	// private static final int FLING_MIN_DISTANCE = 20;
	// private static final int FLING_MIN_VELOCITY = 0;

	public static RemoteTest instance;
	private static Context mContext;
	// private TabHost tabHost;
	// private HorizontalScrollView mHs;
	// private GestureDetector mGestureDetector;
	// private Button mBt_menu;
	private static KeyTreate mKeyTreate = null;

	private Vibrator vibrator;
	// private SlidingDrawer sd;
	// private MenuAdapter myListAdapter;
	// private ListView listView;
	// private ArrayList<MenuList> menulists;
	private ViewPager testRemotePager;

	private TextView mTestInfo;
	private TextView mTestEdit;
	private TextView mTestRename;
	private ImageView mTestBack;
	private Button testNextDevice;
	private Button testKeepDevice;
	public static int[] testRmtTypes = new int[20];

	private ArrayList<RemoteDevice> listTestRemotes;

	private LinearLayout layout;
	private ImageView codeSending;

	//private RemoteSendOut rmtSend;
	RemoteViewPagerAdapter testRemoteAdapter;
	List<Fragment> testRemotes = new ArrayList<Fragment>();
	int current;
	private UserDB mUserdB;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_test);
		mUserdB = new UserDB(this);
		mContext = this;
		ApplicationContext.getInstance().addSetupsActivity(this);
		
		putIntToPreff(Globals.TEST_MODE, 0x01);
		Value.test_mode = 0x01;

		getRemoteInfo();

		initRemotes();

		initButton();
//		rmtSend  = new RemoteSendOut();
//		rmtSend.rmtInitial();

	}

	void initRemotes() {
		testRemotePager = (ViewPager) findViewById(R.id.test_view_pager);
		codeSending = (ImageView) findViewById(R.id.test_code_sending);

		int i = 0;
		for (RemoteDevice rd : listTestRemotes) {

			testRmtTypes[i] = rd.getType();
			
			if (rd.getType()==Value.DeviceType.TYPE_AIR){
				MyAppInfo.saveAirCode(mContext, rd);
			}
			i++;

		}
		testRemotes.clear();
		for (i = 0; i < listTestRemotes.size(); i++) {

			Log.v(TAG, "rmtTypes [" + i + "]--->" + testRmtTypes[i]);
			testRemotes.add(getFragment(testRmtTypes[i]));
		}

		testRemoteAdapter = new RemoteViewPagerAdapter(
				getSupportFragmentManager(), testRemotePager, testRemotes);
		Log.v(TAG, Value.remote.getFullInfo());
		if (Value.remote.getType()!=Value.DeviceType.TYPE_AIR){
		RemoteDatabase.Tab2Map(Value.remote);
		}
		// Iterator iter = Value.KeyTable.entrySet().iterator();
		// while (iter.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// Log.v(TAG, "key---->" + entry.getKey() + "value ---->" +
		// entry.getValue());
		// }

		if (mKeyTreate == null) {
			mKeyTreate = KeyTreate.getInstance();
			mKeyTreate.setHandler(mHandler);
		}

	}

	public Fragment getFragment(int type) {
		Fragment ft = null;
		switch (type) {
		case Value.DeviceType.TYPE_TV:
			ft = new TVRemote();
			break;
		case Value.DeviceType.TYPE_DVD:
			ft = new DVDRemote();
			break;
		case Value.DeviceType.TYPE_STB:
			ft = new STBRemote();
			break;
		case Value.DeviceType.TYPE_FAN:
			ft = new FanRemote();
			break;
		case Value.DeviceType.TYPE_PJT:
			ft = new PJTRemote();
			break;
		case Value.DeviceType.TYPE_AIR:
			ft = new AirRemote();
			break;

		default:
			ft = new TVRemote();

			// Bundle args = new Bundle();
			// args.putString(ARGUMENTS_NAME, tabTitle[arg0]);
			// ft.setArguments(args);

			break;
		}
		// Log.v(TAG, "ft is " + ft.toString());
		return ft;
	}

	void initButton() {

		// mHs = (HorizontalScrollView) findViewById(R.id.hs);

		// menuLoading();

		// initHostTab();

		mTestInfo = (TextView) findViewById(R.id.testInfo);
		mTestInfo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// menuWindow = new SelectPicPopupWindow(MainActivity.this,
				// itemsOnClick);
				// menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.parent),
				// Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				showPopupMessage(listTestRemotes.get(0).getFullInfo(),
						"Remote Information", null);
			}
		});
		// mTestEdit = (TextView) findViewById(R.id.testEdit);
		// mTestEdit.setOnClickListener(new OnClickListener() {
		//
		//
		// public void onClick(View v) {
		// Intent i = new Intent(RemoteTest.this, SelectDeviceType.class);
		// i.putExtra("type", Value.remote.getType());
		// i.putExtra("name", Value.remote.getName());
		// i.putExtra("brand", Value.remote.getBrand());
		// i.putExtra("index", Value.remote.getCode());
		// i.putExtra("id", Value.remote.getId());
		// startActivity(i);
		//
		// }
		// });
		mTestRename = (TextView) findViewById(R.id.testRename);
		mTestRename.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				createNamePopup();

			}
		});
		mTestBack = (ImageView) findViewById(R.id.testBack);
		mTestBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				finish();
			}
		});
		testNextDevice =  (Button) findViewById(R.id.testNextDevice);
		testNextDevice.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				finish();
			}
		});
		testKeepDevice =  (Button) findViewById(R.id.testKeepDevice);
		testKeepDevice.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
//				mUserdB.open();
//				// mUseValue.remoteB.getUserKeyValue();
//				if ("true".equals(retrieveStringFromPreff(Globals.ADD_DEVICE))){
//			
//					mUserdB.saveRemoteDevice(Value.remote);	
//				}else {
//					mUserdB.updateRemoteDevice(Value.remote);	
//				}
//				mUserdB.close();
				showLoading();
				RemoteDatabase.saveOrUpdateRemoteDevice(Value.remote);
				dismissLoading();
				ApplicationContext.getInstance().setupTerminate();
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
		putIntToPreff(Globals.TEST_MODE,0x00);
	//	mKeyTreate.setHandler(null);
		mKeyTreate = null;
		
	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		default:
			break;
		}

	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Context getContext() {
		return mContext;
	}

	private final Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case R.id.REMOTE_SEND:
				String remoteData = msg.getData().getString("remote");// 接受msg传递过来的参数
				if (Value.exist ) {
	        		//			Log.v(TAG, "remote data -->" + remoteData);
	        					
//	        					if (retrieveStringFromPreff("vibration", "true")=="true") {
//	        						vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//	        						long[] pattern = { 100, 100 };
//	        						vibrator.vibrate(pattern, 1);
//	        					}
					SendOut.sendRemoteCode(codeSending,remoteData);
	        				}
				
				

				break;

			default:
				break;
			}
		}
	};

	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				showPopupOkCancel("quit", "information",
						new ConfirmDialogInterface() {

							public boolean cancel() {

								return false;
							}

							public boolean ok() {
								// Intent localIntent = new
								// Intent(BaseActivity.this,
								// ChooseDevice.class);
								// if (isTablet())
								// localIntent = new Intent(BaseActivity.this,
								// TabChooseDevice.class);
								// BaseActivity.this.startActivity(localIntent);
								// BaseActivity.this.finish();
								finish();
								// Value.mAppContext.onTerminate();
								// android.os.Process.killProcess(android.os.Process.myPid());
								return false;
							}
						}, false);
				return true;
			}
		}

		return super.dispatchKeyEvent(event);
	}

	private void getRemoteInfo() {

//		Value.remote.setType(getIntent().getIntExtra("type", 0));
//		Value.remote.setName(getIntent().getStringExtra("name"));
//		Value.remote.setCode(getIntent().getStringExtra("index"));
//		Value.remote.setBrand(getIntent().getStringExtra("brand"));
		Log.v(TAG, Value.remote.getFullInfo());
		listTestRemotes = new ArrayList<RemoteDevice>();
		listTestRemotes.clear();
		listTestRemotes.add(Value.remote);
	

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

//	private void sendTestCode(final String code) {
//		if (!code.equals("")) {
//			AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
//			localAlphaAnimation.setDuration(200L);
//			localAlphaAnimation.setAnimationListener(new AnimationListener() {
//
//				public void onAnimationEnd(Animation paramAnimation) {
//				
//		        	rmtSend.rmtStrSend(code);
//		        			
//					AlphaAnimation localAlphaAnimation = new AlphaAnimation(
//							1.0F, 0.0F);
//					localAlphaAnimation.setDuration(200L);
//					localAlphaAnimation
//							.setAnimationListener(new AnimationListener() {
//								public void onAnimationEnd(
//										Animation paramAnimation) {
//									codeSending.setVisibility(View.GONE);
//								}
//
//								public void onAnimationRepeat(
//										Animation paramAnimation) {
//								}
//
//								public void onAnimationStart(
//										Animation paramAnimation) {
//								}
//							});
//					codeSending.startAnimation(localAlphaAnimation);
//
//				}
//
//				public void onAnimationRepeat(Animation paramAnimation) {
//				}
//
//				public void onAnimationStart(Animation paramAnimation) {
//					codeSending.setVisibility(View.VISIBLE);
//				}
//			});
//			codeSending.startAnimation(localAlphaAnimation);
//		}
//	}

	private void createNamePopup() {
		if (inflater == null)
			inflater = getLayoutInflater();
		View localView = inflater.inflate(R.layout.popup_info_layout, null);
		((ImageView) localView.findViewById(R.id.icon))
				.setImageDrawable(getResources().getDrawable(
						R.drawable.popup_icon_info));
		TextView localTextView1 = (TextView) localView
				.findViewById(R.id.message);
		localTextView1.setText(getString(R.string.enter_new_remote_name));
		localTextView1.setTypeface(tf);
		TextView localTextView2 = (TextView) localView.findViewById(R.id.title);
		localTextView2.setText(getString(R.string.err_title_info));
		localTextView2.setTypeface(tf_bold);
		final EditText nameEdit = (EditText) localView.findViewById(R.id.edit);
		nameEdit.setVisibility(View.VISIBLE);
		nameEdit.setText(Value.remote.getName());
		final Dialog dialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.setContentView(localView);
		dialog.show();
		((TextView) localView.findViewById(R.id.ok))
				.setOnClickListener(new View.OnClickListener() {
					

					public void onClick(View paramView) {
						if (nameEdit.getText().toString().trim().length() == 0) {
							showPopupMessage(
									getString(R.string.please_enter_remote_name),
									getString(R.string.error), null);
							return;
						}else {
						Value.remote.setName(nameEdit.getText().toString());

						mUserdB.open();
						// mUseValue.remoteB.getUserKeyValue();
						mUserdB.updateName(Value.remote);
						mUserdB.close();
						// ListRemotes.writeRemoteOnSdcaValue.remote((RemoteDevice)ListRemotes.remotesList.get(arg2));
						dialog.dismiss();
						}

					}
				});
	}
}
