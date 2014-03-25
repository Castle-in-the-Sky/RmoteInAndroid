package com.audiomobile.main;

import java.io.IOException;
import java.util.ArrayList;


import com.audiomobile.data.Globals;
import com.audiomobile.data.RemoteDevice;
import com.audiomobile.data.Value;

import com.audiomobile.interfacenew.ConfirmDialogInterface;
import com.audiomobile.ircore.KeyTreate;
import com.audiomobile.model.MyOnPageChangeListener;

import com.audiomobile.receiver.HeadsetPlugReceiver;
import com.audiomobile.remote.AirRemote;
import com.audiomobile.remote.DVDRemote;
import com.audiomobile.remote.FanRemote;
import com.audiomobile.remote.LearnRemote;
import com.audiomobile.remote.PJTRemote;
import com.audiomobile.remote.STBRemote;
import com.audiomobile.remote.TVRemote;

import com.audiomobile.settings.Settings;

import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.utils.MyAppInfo;
import com.audiomobile.adapt.RemoteSpinnerAdapter;
import com.audiomobile.adapt.RemoteViewAdapter;

import com.audiomobile.remote.R;
import com.database.RemoteDB;
import com.database.UserDB;

import com.etek.ircomm.SendOut;

//import com.audiomobile.utils.RemoteDB;
//import com.audiomobile.utils.UserDB;


import android.os.Bundle;
import android.os.Message;


//import android.os.Environment;
import android.os.Handler;

import android.annotation.SuppressLint;

import android.app.AlertDialog;

import android.support.v4.app.Fragment;


import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;


import android.support.v4.view.ViewPager;

import android.util.Log;


import android.view.KeyEvent;

import android.view.LayoutInflater;


import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


//import android.widget.HorizontalScrollView;


import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import android.widget.AdapterView.OnItemClickListener;

import android.widget.TextView;



@SuppressLint("HandlerLeak")
public class Main extends BaseActivity implements OnTouchListener
// OnGestureListener {
{
	private static final String TAG = "MainActivity";
	// private static final int FLING_MIN_DISTANCE = 20;
	// private static final int FLING_MIN_VELOCITY = 0;

	public static Main instance;
	private static Context mContext;
	// private TabHost tabHost;
	// private HorizontalScrollView mHs;
	// private GestureDetector mGestureDetector;
	// private Button mBt_menu;
	private static KeyTreate mKeyTreate = null;
	// SelectPicPopupWindow menuWindow;
	
	// private SlidingDrawer sd;
	// private MenuAdapter myListAdapter;
	// private ListView listView;
	// private ArrayList<MenuList> menulists;
	private ViewPager remotePager;
	TextView remoteSelect;
	private ImageView mBt_menu;
	private ImageView mBT_next;
	private ImageView mBT_previous;
	// RemoteSendOut rmtSend;
	public static int[] rmtTypes = new int[20];
	private HeadsetPlugReceiver headsetPlugReceiver;

	private ArrayList<RemoteDevice> listRemotes;
	private ArrayList<String> remotesName;

	private LinearLayout layout;
	private ListView remoteListview;
	private int remotesCount;
	private RemoteSpinnerAdapter listAdapt;
	private PopupWindow popupWindow;
	private TextView selectRemote;
	private LinearLayout moreControl;
	RemoteViewAdapter remoteAdapter;
	// RemoteViewPagerAdapter remoteAdapter;

	ArrayList<Fragment> remotes = new ArrayList<Fragment>();
	int current;
	private ImageView codeSending;

	private MyOnPageChangeListener mListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		// SendOut.rmtInitial();
		 registerHeadsetPlugReceiver();
		
		// mGestureDetector = new GestureDetector(this);
		// RelativeLayout line = (RelativeLayout) findViewById(R.id.parent);
		// line.setOnTouchListener(this);
		// line.setLongClickable(true);
		mContext = this;
		ApplicationContext.getInstance().addActivity(this);

		// if (RemoteCommunicate.initaudiomobileRemote()){
		// Toast.makeText(mContext, "device de6008 ready",
		// Toast.LENGTH_SHORT).show();
		// }else {
		// Toast.makeText(mContext, " de6008 is not ready",
		// Toast.LENGTH_SHORT).show();
		// }

		// Value.exist = true;
		// AudioManager am = (AudioManager)
		// Value.mAppContext.getSystemService(Context.AUDIO_SERVICE);
		// am.setStreamVolume(AudioManager.STREAM_MUSIC,
		// am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
		// AudioManager.FLAG_SHOW_UI);
		// rmtSend = new RemoteSendOut();
		// rmtSend.rmtInitial();
		

		createDatabase();

		initRemotes();

		initButton();
		
		if (retrieveStringFromPreff("vibration", "true") == "true") {
			Value.vibrate = true;	
		}else {
			Value.vibrate = false;	
		}

	}

	void initRemotes() {
		remotePager = (ViewPager) findViewById(R.id.remote_pager);
		selectRemote = (TextView) findViewById(R.id.selectRemote);

		remotesName = new ArrayList<String>();
		remotesName.clear();
		int i = 0;
		for (RemoteDevice rd : listRemotes) {
			remotesName.add(rd.getName());
			rmtTypes[i] = rd.getType();
			if (rd.getType() == Value.DeviceType.TYPE_AIR) {
				MyAppInfo.saveAirCode(mContext, rd);
			}
			i++;
		}
		remotes.clear();
		for (i = 0; i < listRemotes.size(); i++) {
			// Log.v(TAG, "remotesName [" + i + "]--->" + remotesName.get(i));
			// Log.v(TAG, "rmtTypes [" + i + "]--->" + rmtTypes[i]);
			remotes.add(getFragment(rmtTypes[i]));
		}
		Value.totalRemote = listRemotes.size();
		selectRemote.setText(remotesName.get(0));

		// remoteAdapter = new RemoteViewPagerAdapter(
		// this.getSupportFragmentManager(), remotePager, remotes);
		// remoteAdapter
		// .setOnExtraPageChangeListener(new
		// RemoteViewPagerAdapter.OnExtraPageChangeListener() {
		// @Override
		// public void onExtraPageSelected(int i) {
		// selectRemote.setText(remotesName.get(i));
		// current = i;
		// // Log.v(TAG, "page -->" + i);
		// // System.out.println("Extra...i: " + i);
		// }
		// });
		remoteAdapter = new RemoteViewAdapter(mContext,
				getSupportFragmentManager(), remotes);
		remotePager.setAdapter(remoteAdapter);
		remotePager.setCurrentItem(0);

		listAdapt = new RemoteSpinnerAdapter(this, remotesName);
		mListener = new MyOnPageChangeListener(this, remotePager, remotes);
		mListener
				.setOnExtraPageChangeListener(new MyOnPageChangeListener.OnExtraPageChangeListener() {
					@Override
					public void onExtraPageSelected(int i) {
						selectRemote.setText(remotesName.get(i));
						current = i;
						// Log.v(TAG, "page -->" + i);
						// System.out.println("Extra...i: " + i);
					}
				});
		// remoteAdapter = new
		// AllRemotePagerAdapter(getSupportFragmentManager());
		// remotePager.setAdapter(remoteAdapter);
		remotePager.setOffscreenPageLimit(1);
		remotePager.setOnPageChangeListener(mListener);

	}

	void retriveRemotes() {

		remotesName.clear();
		int i = 0;
		for (RemoteDevice rd : listRemotes) {
			remotesName.add(rd.getName());
			rmtTypes[i] = rd.getType();
			if (rd.getType() == Value.DeviceType.TYPE_AIR) {
				MyAppInfo.saveAirCode(mContext, rd);
				Log.v(TAG, "rd is " + rd.getId());
			}
			i++;

		}
		remotes.clear();
		for (i = 0; i < listRemotes.size(); i++) {
			// Log.v(TAG, "remotesName [" + i + "]--->" + remotesName.get(i));
			// Log.v(TAG, "rmtTypes [" + i + "]--->" + rmtTypes[i]);
			remotes.add(getFragment(rmtTypes[i]));
		}
		Value.totalRemote = listRemotes.size();

		remoteAdapter.notifyDataSetChanged();
		listAdapt.notifyDataSetChanged();

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
		remoteSelect = (TextView) findViewById(R.id.selectRemote);
		remoteSelect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Log.v(TAG, "you are right");
				showRemoteSelect(v);

			}
		});
		// mHs = (HorizontalScrollView) findViewById(R.id.hs);

		// menuLoading();

		// initHostTab();

		mBt_menu = (ImageView) findViewById(R.id.settings);
		mBt_menu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// menuWindow = new SelectPicPopupWindow(MainActivity.this,
				// itemsOnClick);
				// menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.parent),
				// Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				Intent i = new Intent(Main.this, Settings.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		mBT_next = (ImageView) findViewById(R.id.selectRight);
		mBT_next.setOnClickListener(new OnClickListener() {
	//		private RemoteDevice mDev;

			public void onClick(View v) {
				int total = Value.totalRemote;

				if (current + 1 >= total) {
					current = total - 1;
				} else {
					current = current + 1;
				}
				// Log.v(TAG, "total ====>" + total);
				// Log.v(TAG, "current ====>" + current);
				Value.currentDevice = current;
				remotePager.setCurrentItem(current);

				remoteSelect.setText(remotesName.get(current));

			}
		});
		mBT_previous = (ImageView) findViewById(R.id.selectLeft);
		mBT_previous.setOnClickListener(new OnClickListener() {
	//		private RemoteDevice mDev;

			public void onClick(View v) {
				int total = Value.totalRemote;

				if (current - 1 < 0) {
					current = 0;
				} else {
					current = current - 1;
				}
				// Log.v(TAG, "total ====>" + total);
				// Log.v(TAG, "current ====>" + current);
				Value.currentDevice = current;
				remotePager.setCurrentItem(current);

				remoteSelect.setText(remotesName.get(current));

			}
		});
		moreControl = (LinearLayout) findViewById(R.id.swipe);
		moreControl.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// menuWindow = new SelectPicPopupWindow(MainActivity.this,
				// itemsOnClick);
				// menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.parent),
				// Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				Value.modeLearning = false;
				Intent i = new Intent(Main.this, LearnRemote.class);
				startActivity(i);
			}
		});

		codeSending = (ImageView) findViewById(R.id.code_sending);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (mKeyTreate == null) {
			mKeyTreate = KeyTreate.getInstance();
			// mKeyTreate.setContext(this);
			mKeyTreate.setHandler(mainHandler);
		}

		putIntToPreff(Globals.TEST_MODE, 0x00);
		Value.test_mode = 0x00;
		// tabHost = getTabHost();

		// tabHost.clearAllTabs();
		// initHostTab();
		// tabHost.setCurrentTab(0);
		// tabHost.setOnTabChangedListener(new OnTabChangeListener() {
		// public void onTabChanged(String tabId) {
		// Value.currentDevice = tabHost.getCurrentTab();
		// // Toast.makeText(getApplicationContext(),
		// // "鐜板湪鏄�+tabHost.getCurrentTab() , Toast.LENGTH_SHORT).show();
		// }
		// });
		// RemoteComm.initRemote();

	}

	@Override
	public void onDestroy() {
		unregisterReceiver(headsetPlugReceiver);
		super.onDestroy();

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		if (mKeyTreate == null) {
			mKeyTreate = KeyTreate.getInstance();
			// mKeyTreate.setContext(this);

		}
		mKeyTreate.setHandler(mainHandler);

		putIntToPreff(Globals.TEST_MODE, 0x00);
		Value.test_mode = 0x00;
		Log.v(TAG, "main restart");
		getRemoteLists();

		retriveRemotes();

	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		switch (requestCode) {
//		case R.id.REQUEST_STUDY:
//
//			break;
//		default:
//			break;
//		}
//
//	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	// public boolean onTouch(View arg0, MotionEvent arg1) {
	// // TODO Auto-generated method stub
	// return mGestureDetector.onTouchEvent(arg1);
	// }

	

	public static Context getContext() {
		return mContext;
	}

	public void showCodeSending() {
		mainHandler.post(new Runnable() {
			public void run() {
				final View localView = findViewById(R.id.code_sending);
				// if (localView.getVisibility() == 0)
				// return;
				AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F,
						1.0F);
				localAlphaAnimation
						.setInterpolator(new AccelerateInterpolator());
				localAlphaAnimation.setDuration(100);
				localAlphaAnimation
						.setAnimationListener(new Animation.AnimationListener() {
							public void onAnimationEnd(Animation paramAnimation) {
								localView.setVisibility(8);
							}

							public void onAnimationRepeat(
									Animation paramAnimation) {
							}

							public void onAnimationStart(
									Animation paramAnimation) {
								localView.setVisibility(0);
							}
						});
				localView.startAnimation(localAlphaAnimation);
			}
		});
	}

	public void audiomobiledeSending() {
		mainHandler.post(new Runnable() {
			public void run() {
				final View localView = findViewById(R.id.code_sending);
				if (localView.getVisibility() != 0)
					return;
				AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F,
						0.0F);
				localAlphaAnimation
						.setInterpolator(new AccelerateInterpolator());
				localAlphaAnimation.setDuration(100);
				localAlphaAnimation
						.setAnimationListener(new Animation.AnimationListener() {
							public void onAnimationEnd(Animation paramAnimation) {
								localView.setVisibility(0);
							}

							public void onAnimationRepeat(
									Animation paramAnimation) {
							}

							public void onAnimationStart(
									Animation paramAnimation) {
								localView.setVisibility(8);
							}
						});
				localView.startAnimation(localAlphaAnimation);
			}
		});
	}

	// private void sendRemoteCode(final String code) {
	// if (!code.equals("")) {
	// AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
	// localAlphaAnimation.setDuration(200L);
	// localAlphaAnimation.setAnimationListener(new AnimationListener() {
	//
	// public void onAnimationEnd(Animation paramAnimation) {
	// // rmtSend.rmtStart(code);
	// RemoteSendOut.rmtStrSend(code);
	// AlphaAnimation localAlphaAnimation = new AlphaAnimation(
	// 1.0F, 0.0F);
	// localAlphaAnimation.setDuration(200L);
	// localAlphaAnimation
	// .setAnimationListener(new AnimationListener() {
	// public void onAnimationEnd(
	// Animation paramAnimation) {
	// codeSending.setVisibility(View.GONE);
	// }
	//
	// public void onAnimationRepeat(
	// Animation paramAnimation) {
	// }
	//
	// public void onAnimationStart(
	// Animation paramAnimation) {
	// }
	// });
	// codeSending.startAnimation(localAlphaAnimation);
	//
	// }
	//
	// public void onAnimationRepeat(Animation paramAnimation) {
	// }
	//
	// public void onAnimationStart(Animation paramAnimation) {
	// codeSending.setVisibility(View.VISIBLE);
	// }
	// });
	// codeSending.startAnimation(localAlphaAnimation);
	// }
	// }
	private final Handler mainHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case R.id.MESSAGE_WRITE:
				break;
			case R.id.REMOTE_SEND:
				String remoteData = msg.getData().getString("remote");

				if (Value.exist && remoteData != null) {
					// Log.v(TAG, "remote data -->" + remoteData);

//					if (retrieveStringFromPreff("vibration", "true") == "true") {
//						vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//						long[] pattern = { 100, 100 };
//						vibrator.vibrate(pattern, 1);
//					}
					SendOut.sendRemoteCode(codeSending, remoteData);
				}

				break;
		
				// showCodeSending();

				// hiaudiomobiledeSending();

			
			case R.id.MESSAGE_READ:

				break;
			case R.id.MESSAGE_TOAST:

				break;
			case R.id.MSG_LEARN_END:
				// RemoteCommunicate.remoteaudiomobileLearnStop();
				// Value.isStudying = false;
				// Toast toast = Toast.makeText(getApplicationContext(),
				// R.string.study_save,
				// Toast.LENGTH_SHORT);
				// toast.setGravity( Gravity.CENTER_HORIZONTAL, 0, 0);
				// toast.show();
				break;
			case R.id.MSG_OPTION_STUDY:

//				Log.v(TAG, "msg study");
//				// Value.isStudying = true;
//				byte[] cmdData = new byte[10];
//				cmdData[0] = Value.AudioCommand.START_LEARN;
//				SendOut.rmtByteSend(cmdData);
//				Intent studyIntent = new Intent(mContext, Learning.class);
//
//				startActivityForResult(studyIntent, R.id.REQUEST_STUDY);

				break;
			case R.id.MSG_OPTION_LIST:
				Log.v("test", "btn_options btn_options");
				// Intent optionsIntent = new Intent(mContext,
				// ListActivity.class);
				// startActivityForResult(optionsIntent, R.id.REQUEST_OPTIONS);

				// Intent optionsIntent = new Intent(mContext,
				// RemoteDevicesList.class);
				// startActivity(optionsIntent);
				break;

			case R.id.MSG_OPTION_ABOUT:
				// Dialog dialog = new Dialog(mContext);
				// dialog.setContentView(R.layout.dialog_about);
				// dialog.setTitle(R.string.title_about);
				// dialog.show();
//				LayoutInflater mInflater = LayoutInflater.from(mContext);
//				View settingView = mInflater.inflate(R.layout.dialog_about,
//						null);
//				AlertDialog aboutDialog = new AlertDialog.Builder(mContext)
//						.setIcon(R.drawable.ic_launcher)
//						.setTitle(R.string.title_about)
//						.setView(settingView)
//						.setPositiveButton(R.string.dialog_ok,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,
//											int whichButton) {
//									}
//								}).create();
//				aboutDialog.show();
				break;

			case R.id.MSG_OPTION_SETUP:
				Log.v("test", "MSG_OPTION_SETUP MSG_OPTION_SETUP");
				Intent setupIntent = new Intent(mContext, Settings.class);
				startActivity(setupIntent);
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
				showPopupOkCancel(getResources().getString(R.string.exit), "",
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
		if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				Intent setupIntent = new Intent(mContext, Settings.class);
				startActivity(setupIntent);
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	private void createDatabase() {
		RemoteDB mRmtDB = new RemoteDB(getApplicationContext());
		try {
			mRmtDB.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserDB mUserDB = new UserDB(getApplicationContext());
		try {
			mUserDB.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		mUserDB.open();
//		// mUserDB.getUserKeyValue();
//		listRemotes = mUserDB.getRemoteDevices();
//		mUserDB.close();
		getRemoteLists();

	}

	private void getRemoteLists() {

		UserDB mUserDB = new UserDB(getApplicationContext());

		mUserDB.open();
		// mUserDB.getUserKeyValue();
		listRemotes = mUserDB.getRemoteDevices();
		mUserDB.close();

	}

	public void showRemoteSelect(View v) {

	
		layout = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.remote_spinner_dropdown, null);
		
		remoteListview = (ListView) layout.findViewById(R.id.listView);
		
		remoteListview.setAdapter(listAdapt);

		popupWindow = new PopupWindow(v);
	
		popupWindow.setWidth(remoteSelect.getWidth());
		
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
	
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		popupWindow.setOutsideTouchable(true);
	
		popupWindow.setFocusable(true);
	
		popupWindow.setContentView(layout);
	
		popupWindow.showAsDropDown(v, 0, 0);
	
		remoteListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Value.currentDevice = arg2;
				Log.v(TAG, "current device is " + Value.currentDevice);
				remoteSelect.setText(remotesName.get(arg2));
				remotePager.setCurrentItem(arg2);
				// tabHost.setCurrentTab(arg2);
			
				popupWindow.dismiss();
				popupWindow = null;

			}
		});

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
private void registerHeadsetPlugReceiver() {
		
		headsetPlugReceiver = new HeadsetPlugReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.HEADSET_PLUG");
		registerReceiver(headsetPlugReceiver, intentFilter);
	}
}
