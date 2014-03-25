package com.audiomobile.remote;

import java.util.ArrayList;
import java.util.HashMap;

import com.audiomobile.data.LearnButtonData;

import com.audiomobile.data.Value;

import com.audiomobile.interfacenew.ConfirmDialogInterface;
import com.audiomobile.main.BaseActivity;
import com.audiomobile.settings.Learning;

import com.audiomobile.remote.R;
import com.database.UserDB;

import com.etek.ircomm.SendOut;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;

import android.util.Log;


import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LearnRemote extends BaseActivity implements OnClickListener {
	// TextView keyValueIndex;

	LinearLayout mainRemote;
	HashMap<String, Integer> buttons = new HashMap<String, Integer>();
	ArrayList<LearnButtonData> learnRmts = new ArrayList<LearnButtonData>();
	UserDB mUserDB;
	private String TAG = "LEARNREMOTE";
	private ImageView codeSending;
	private ImageView lr_bck;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_learn);
		mUserDB = new UserDB(this);

		// DisplayMetrics dm = new DisplayMetrics();
		// getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		// int screenWidth = (int) (dm.widthPixels/4);
		//

		initButtons();
		codeSending = (ImageView) findViewById(R.id.learn_sending);
		mainRemote = (LinearLayout) findViewById(R.id.learnRemoteSwipe);
		mainRemote.setOnClickListener(this);
		lr_bck = (ImageView) findViewById(R.id.lr_back);
		lr_bck.setOnClickListener(this);
		if (Value.modeLearning){
			mainRemote.setVisibility(View.INVISIBLE);
		}else {
			lr_bck.setVisibility(View.INVISIBLE);
	
		}
	}

	void initButtons() {
		buttons.put("learn_bt1", R.id.learn_bt1);
		// Button learn_bt2 = (Button)findViewById(R.id.learn_bt2);
		buttons.put("learn_bt2", R.id.learn_bt2);

		buttons.put("learn_bt3", R.id.learn_bt3);

		buttons.put("learn_bt4", R.id.learn_bt4);

		buttons.put("learn_bt5", R.id.learn_bt5);

		buttons.put("learn_bt6", R.id.learn_bt6);

		buttons.put("learn_bt7", R.id.learn_bt7);

		buttons.put("learn_bt8", R.id.learn_bt8);

		buttons.put("learn_bt9", R.id.learn_bt9);

		buttons.put("learn_bt10", R.id.learn_bt10);

		buttons.put("learn_bt11", R.id.learn_bt11);

		buttons.put("learn_bt12", R.id.learn_bt12);

		buttons.put("learn_bt13", R.id.learn_bt13);

		buttons.put("learn_bt14", R.id.learn_bt14);

		buttons.put("learn_bt15", R.id.learn_bt15);

		buttons.put("learn_bt16", R.id.learn_bt16);

		buttons.put("learn_bt17", R.id.learn_bt17);

		buttons.put("learn_bt18", R.id.learn_bt18);

		buttons.put("learn_bt19", R.id.learn_bt19);

		buttons.put("learn_bt20", R.id.learn_bt20);

		buttons.put("learn_bt21", R.id.learn_bt21);

		buttons.put("learn_bt22", R.id.learn_bt22);

		buttons.put("learn_bt23", R.id.learn_bt23);

		buttons.put("learn_bt24", R.id.learn_bt24);

		buttons.put("learn_bt25", R.id.learn_bt25);

		buttons.put("learn_bt26", R.id.learn_bt26);

		buttons.put("learn_bt27", R.id.learn_bt27);

		buttons.put("learn_bt28", R.id.learn_bt28);
//		if (retrieveStringFromPreff("initlearn", "true").equals("true")) {
//			putStringToPreff("initlearn", "false");
//		//	Log.v(TAG, "learn remote init");
//			// Button learn_bt1 = (Button)findViewById(R.id.learn_bt1);
//			
//
//			mUserDB.open();
//			// mUserDB.getUserKeyValue();
//			try {
//				mUserDB.initLearnRemote(buttons);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			mUserDB.close();
//
//		}else {
//			mUserDB.open();
//			// mUserDB.getUserKeyValue();
//			try {
//			//	mUserDB.updataLearnBTID(buttons);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			mUserDB.close();
//			
//		}

	//	retrieveAllLearnRemotes();

	}

	@Override
	public void onStart() {
		super.onStart();
		retrieveAllLearnButtons();
	}

	public void onClick(View v) {
		LearnButtonData learnButton = new LearnButtonData();
		String keyValue = "";
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.learn_bt1:
			keyValue = "learn_bt1";
			break;
		case R.id.learn_bt2:
			keyValue = "learn_bt2";
			break;
		case R.id.learn_bt3:
			keyValue = "learn_bt3";
			break;
		case R.id.learn_bt4:
			keyValue = "learn_bt4";
			break;
		case R.id.learn_bt5:
			keyValue = "learn_bt5";
			break;
		case R.id.learn_bt6:
			keyValue = "learn_bt6";
			break;
		case R.id.learn_bt7:
			keyValue = "learn_bt7";
			break;
		case R.id.learn_bt8:
			keyValue = "learn_bt8";
			break;
		case R.id.learn_bt9:
			keyValue = "learn_bt9";
			break;
		case R.id.learn_bt10:
			keyValue = "learn_bt10";
			break;
		case R.id.learn_bt11:
			keyValue = "learn_bt11";
			break;
		case R.id.learn_bt12:
			keyValue = "learn_bt12";
			break;
		case R.id.learn_bt13:
			keyValue = "learn_bt13";
			break;
		case R.id.learn_bt14:
			keyValue = "learn_bt14";
			break;
		case R.id.learn_bt15:
			keyValue = "learn_bt15";
			break;
		case R.id.learn_bt16:
			keyValue = "learn_bt16";
			break;
		case R.id.learn_bt17:
			keyValue = "learn_bt17";
			break;
		case R.id.learn_bt18:
			keyValue = "learn_bt18";
			break;
		case R.id.learn_bt19:
			keyValue = "learn_bt19";
			break;
		case R.id.learn_bt20:
			keyValue = "learn_bt20";
			break;
		case R.id.learn_bt21:
			keyValue = "learn_bt21";
			break;
		case R.id.learn_bt22:
			keyValue = "learn_bt22";
			break;
		case R.id.learn_bt23:
			keyValue = "learn_bt23";
			break;
		case R.id.learn_bt24:
			keyValue = "learn_bt24";
			break;
		case R.id.learn_bt25:
			keyValue = "learn_bt25";
			break;
		case R.id.learn_bt26:
			keyValue = "learn_bt26";
			break;
		case R.id.learn_bt27:
			keyValue = "learn_bt27";
			break;
		case R.id.learn_bt28:
			keyValue = "learn_bt28";
			break;
		case R.id.learnRemoteSwipe:
			keyValue = null;
			// Log.v("learnremote","learnremote finish");
			finish();
			break;
		case R.id.lr_back:
			keyValue = null;
			// Log.v("learnremote","learnremote finish");
			finish();
			break;

		default:
			break;
		}
		if (keyValue != null){
			mUserDB.open();

			try {
				Log.v(TAG, "button id is " + keyValue);
				learnButton = mUserDB.getLearnButtonFromKey(keyValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mUserDB.close();
			Log.v(TAG , learnButton.getInfo());
			if (Value.modeLearning) {
				if (learnButton.getValid() != 0) {
					editButtonPopup(learnButton);
				} else {
					Value.learnButtonData = learnButton;

					startActivityForResult(new Intent(LearnRemote.this,
							Learning.class),1);
				
				}
			}else {
				if (learnButton.getValid() != 0) {
					SendOut.sendRemoteCode(codeSending, learnButton.getData());
				} 
			}
		}

	}

	public void editButtonPopup(final LearnButtonData learnBD) {

//		myHandler.post(new Runnable() {
//			public void run() {
				if (inflater == null)
					inflater = getLayoutInflater();
				View localView = inflater.inflate(R.layout.popup_edit_remote,
						null);
				TextView localTextView1 = (TextView) localView
						.findViewById(R.id.title);
				localTextView1.setTypeface(LearnRemote.tf_bold);
				localTextView1.setVisibility(View.VISIBLE);
				
				final Dialog dialog = new Dialog(LearnRemote.this,
						android.R.style.Theme_Translucent_NoTitleBar);
				dialog.setContentView(localView);
				dialog.show();
				final TextView viewr = (TextView) localView
						.findViewById(R.id.viewr);
				final TextView rename = (TextView) localView
						.findViewById(R.id.rename);
				final TextView reLearn = (TextView) localView
						.findViewById(R.id.move);
				final TextView delete = (TextView) localView
						.findViewById(R.id.delete);
				delete.setText(R.string.delete);
				reLearn.setText(R.string.reLearn);
				rename.setText(R.string.rename);
				viewr.setText(R.string.test_button);
				rename.setTypeface(LearnRemote.tf_bold);
				reLearn.setTypeface(LearnRemote.tf_bold);
				delete.setTypeface(LearnRemote.tf_bold);
				viewr.setTypeface(LearnRemote.tf_bold);

				OnClickListener viewClickListener = new View.OnClickListener() {
					public void onClick(View paramView) {
						dialog.dismiss();
						if (paramView == viewr) {
							// Intent localIntent1 = new
							// Intent(ListRemotes.this,
							// RemoteActivity.class);

							// startActivity(localIntent1);
							Log.v(TAG, "test");
							SendOut.rmtStrSend(learnBD.getData());
	//						RemoteSend.sendRemoteCode(codeSending, learnBD.getData());
							showPopupMessage(
									learnBD.getInfo(),
									getResources().getString(
											R.string.button_info), null);
							
							
						}
						if (paramView == delete) {
						
							confirmDelete(learnBD);
						}
						if (paramView == reLearn) {
							
							Value.learnButtonData = learnBD;
							Intent i = new Intent(LearnRemote.this,
									Learning.class);
							startActivityForResult(i,1);

						}
						if (paramView == rename) {
							
							renamePopup(learnBD);
						}
					}
				};
				delete.setOnClickListener(viewClickListener);
				viewr.setOnClickListener(viewClickListener);
				reLearn.setOnClickListener(viewClickListener);
				rename.setOnClickListener(viewClickListener);
			}

//		});
	

	public void confirmDelete(final LearnButtonData learnBd) {
		//Object[] arrayOfObject = new Object[1];
		// arrayOfObject[0] = ((String) adapter.getItem(paramInt));
		showPopupOkCancel(
				getString(R.string.learn_button_delete, learnBd.getName()),
				getString(R.string.err_title_warning),
				new ConfirmDialogInterface() {
					public boolean cancel() {
						return false;
					}

					public boolean ok() {
						 mUserDB.open();
						 learnBd.setData("");
						 learnBd.setName("");
						 learnBd.setValid(0); 
						
						 mUserDB.updateLearnButton(learnBd);
						 mUserDB.close();
						 
						 retrieveAllLearnButtons();
						return false;
					}
				}

				, false);
	}

	private void renamePopup(final  LearnButtonData learnBd) {
		if (inflater == null)
			inflater = getLayoutInflater();
		View localView = inflater.inflate(R.layout.popup_info_layout, null);
		((ImageView) localView.findViewById(R.id.icon))
				.setImageDrawable(getResources().getDrawable(
						R.drawable.popup_icon_info));
		TextView localTextView1 = (TextView) localView
				.findViewById(R.id.message);
		localTextView1.setHint(getString(R.string.enter_new_button_name));
		localTextView1.setTypeface(tf);
		TextView localTextView2 = (TextView) localView.findViewById(R.id.title);
		localTextView2.setText(getString(R.string.err_title_info));
		localTextView2.setTypeface(tf_bold);
		final EditText nameEdit = (EditText) localView.findViewById(R.id.edit);
		nameEdit.setVisibility(View.VISIBLE);
		nameEdit.setText( learnBd.getName());
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
						}
						learnBd.setName(nameEdit.getText().toString());
						
						mUserDB.open();
						mUserDB.updateLearnButton(learnBd);
						mUserDB.close();
						// ListRemotes.writeRemoteOnSdcard((RemoteDevice)ListRemotes.remotesList.get(arg2));
						dialog.dismiss();
						retrieveAllLearnButtons();
					}
				});
	}
	private void addButtonPopup() {
		if (inflater == null)
			inflater = getLayoutInflater();
		
		View popView = inflater.inflate(R.layout.popup_info_layout, null);
		((ImageView) popView.findViewById(R.id.icon))
				.setImageDrawable(getResources().getDrawable(
						R.drawable.popup_icon_info));
		TextView message = (TextView) popView
				.findViewById(R.id.message);
		message.setText("");
		message.setTypeface(tf);
		TextView title = (TextView) popView.findViewById(R.id.title);
		title.setText(getString(R.string.enter_new_button_name));
		title.setTypeface(tf_bold);
		final EditText nameEdit = (EditText) popView.findViewById(R.id.editBrand);
		nameEdit.setVisibility(View.VISIBLE);
		nameEdit.setHint(R.string.new_button);
		final Dialog dialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.setContentView(popView);
		dialog.show();
		((TextView) popView.findViewById(R.id.ok))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View paramView) {
						if (nameEdit.getText().toString().trim().length() == 0) {
							showPopupMessage(
									getString(R.string.please_enter_button_name),
									getString(R.string.error), null);
							return;
						} else {
							
							Value.learnButtonData.setName(nameEdit.getText().toString());
							mUserDB.open();
							Value.learnButtonData.setValid(1);
							mUserDB.updateLearnButton(Value.learnButtonData);
							mUserDB.close();
							Log.e(TAG, Value.learnButtonData.getInfo());
							retrieveAllLearnButtons();
						
							dialog.dismiss();
							
						}

					}
				});
	}

	private void retrieveAllLearnButtons() {
		Drawable d = getResources().getDrawable(R.drawable.plain_button);
		d.setAlpha(30);
		Drawable n = getResources().getDrawable(
				R.drawable.normal_button_selector);
		n.setAlpha(100);
		Drawable p = getResources().getDrawable(R.drawable.plain_contour_plus);
		Drawable c = getResources().getDrawable(R.drawable.plain_contour);
		
		mUserDB.open();
		for (int i = 0; i < 28; i++) {
			LearnButtonData learnButton = new LearnButtonData();

			// mUserDB.getUserKeyValue();
			try {
				learnButton = mUserDB.getLearnButton(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			learnRmts.add(learnButton);
	//		Log.v("LEARNREMOTE", learnButton.getInfo());
			if (Value.modeLearning) {
				int id = buttons.get(learnButton.getKeyName());
		//		 findViewById(learnRemote.getBtId());
				if (learnButton.getValid() == 0) {
					findViewById(id).setBackgroundDrawable(p);
					findViewById(id).setOnClickListener(this);
					((Button) findViewById(id)).setText("");
				} else {
					findViewById(id).setBackgroundDrawable(c);
					findViewById(id).setOnClickListener(this);
					((Button) findViewById(id)).setText(learnButton.getName());
				}
			} else {
				//Button bt = (Button) findViewById(learnRemote.getBtId());
				Log.v(TAG, learnButton.getInfo());
				if (learnButton.getValid() == 0) {
//					findViewById(learnButton.getBtId()).setVisibility(View.INVISIBLE);
				} else {
//					findViewById(learnButton.getBtId()).setBackgroundResource(R.drawable.normal_button_selector);
//					findViewById(learnButton.getBtId()).setOnClickListener(this);
//					((Button) findViewById(learnButton.getBtId())).setText(learnButton.getName());
				}
			}
		}
		mUserDB.close();

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case RESULT_OK:
			Log.e(TAG, "learn finish");
			
			
			
			if (Value.learnButtonData.getValid()==0){
				String learnData = "55" +data.getExtras().getString("decode");
				Value.learnButtonData.setData(learnData);
			addButtonPopup();
			}else {
				String learnData = "55" +data.getExtras().getString("decode");
				Value.learnButtonData.setData(learnData);
				mUserDB.open();
				mUserDB.updateLearnButton(Value.learnButtonData);
				mUserDB.close();
				Log.e(TAG, Value.learnButtonData.getInfo());
				retrieveAllLearnButtons();
			}
			
//    		msg.setData(b);
//    		codeHandler.sendMessage(msg);
			break;
		case RESULT_CANCELED:
			Log.e(TAG, "learn failed");
			Bundle msg = data.getExtras();
			String learnStatus = msg.getString("decode");
			Toast.makeText(LearnRemote.this, learnStatus, Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}

	}
	
}