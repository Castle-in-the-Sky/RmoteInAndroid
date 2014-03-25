package com.audiomobile.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.audiomobile.remote.R;
import com.audiomobile.data.Globals;
import com.audiomobile.data.LearnButtonData;
import com.audiomobile.data.RemoteDevice;
import com.audiomobile.data.Value;
import com.audiomobile.interfacenew.ConfirmDialogInterface;
import com.audiomobile.interfacenew.ConfirmPopupInterface;
import com.audiomobile.main.BaseActivity;
import com.audiomobile.remote.LearnRemote;
import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.utils.Tools;
import com.database.UserDB;

public class Settings extends BaseActivity implements View.OnClickListener {
	RelativeLayout list_remotes;
	RelativeLayout add_remote;
	RelativeLayout learn_remote;
	RelativeLayout vibrationCheck;
	RelativeLayout powerCheck;
	RelativeLayout existCheck;
	ImageView vibrationImage;
	ImageView powerImage;
	ImageView existImage;
	ImageView animationImage;

	View help;
	View contact_us;

	View share;
	TextView txt1;
	TextView txt2;
	TextView txt3;
	TextView txt4;
	TextView txt5;
	TextView txt6;
	ImageView setBck;

	ImageView widgetEditButtonCheck;
	private static final String TAG = "Settings";

	private RelativeLayout exit;
	private UserDB mUserDB;
	private RelativeLayout animationCheck;
	public static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.txt";
	private static final String AUDIO_RECORDER_DATA_FILE = "record_data.csv";

	//
	private void initCheckboxes() {
		if (retrieveStringFromPreff("vibration", "true").equals("true")) {
			Value.vibrate = true;
			vibrationImage.setImageResource(R.drawable.check_on);
		} else {
			Value.vibrate = false;
			vibrationImage.setImageResource(R.drawable.check_off);
		}
		if (retrieveStringFromPreff("power", "false").equals("true")) {
			Value.powerSupply = true;
			powerImage.setImageResource(R.drawable.check_on);

		} else {
			Value.powerSupply = false;
			powerImage.setImageResource(R.drawable.check_off);
		}
		if (Value.exist) {
			existImage.setImageResource(R.drawable.check_on);
		} else {
			existImage.setImageResource(R.drawable.check_off);
		}
		
		
		if (retrieveStringFromPreff("animation", "false").equals("true")) {
			Value.animation = true;
			animationImage.setImageResource(R.drawable.check_on);
		} else {
			Value.animation = false;
			animationImage.setImageResource(R.drawable.check_off);
		}

	}

	private void initLayout() {
		list_remotes = ((RelativeLayout) findViewById(R.id.list_remotes));
		add_remote = ((RelativeLayout) findViewById(R.id.add_remote));
		learn_remote = ((RelativeLayout) findViewById(R.id.learn_remote));
		contact_us = findViewById(R.id.contact_us);
		exit = ((RelativeLayout) findViewById(R.id.exit));
		help = findViewById(R.id.help);
		txt1 = ((TextView) findViewById(R.id.txt1));
		txt2 = ((TextView) findViewById(R.id.txt2));
		txt4 = ((TextView) findViewById(R.id.txt4));
		txt5 = ((TextView) findViewById(R.id.txt5));
		txt6 = ((TextView) findViewById(R.id.txt6));

		findViewById(R.id.txt51);
		findViewById(R.id.txt9);
		vibrationCheck = ((RelativeLayout) findViewById(R.id.vibrations));
		powerCheck = ((RelativeLayout) findViewById(R.id.terminal_power));
		existCheck = ((RelativeLayout) findViewById(R.id.audio_exist));
		animationCheck = ((RelativeLayout) findViewById(R.id.send_animation));
		
		vibrationImage = (ImageView) findViewById(R.id.vb2);
		powerImage = (ImageView) findViewById(R.id.tp2);
		existImage = (ImageView) findViewById(R.id.ap2);
		animationImage = (ImageView) findViewById(R.id.sa2);
		
		setBck = (ImageView) findViewById(R.id.settingsBck);
		setBck.setOnClickListener(this);
		initCheckboxes();
		
	}

	public void goToAddDevice() {
		putStringToPreff(Globals.ADD_DEVICE, "true");
		Value.remote.setId(Value.totalRemote);
		Value.remote.setType(Value.DeviceType.TYPE_TV);
		createNamePopup("NEW DEVICE");

	}

	public void goToBackupRestore() {

		try {
			mUserDB.initialDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		putStringToPreff("initlearn", "true");
		 Toast.makeText(Settings.this, R.string.clean_database, Toast.LENGTH_LONG).show();
		
	}

	public void goToListRemotes() {
		startActivity(new Intent(this, ListRemotes.class));
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.backup_restore:
			showPopupOkCancel( getResources().getString(R.string.warning),getResources().getString(R.string.restore_text),
					new ConfirmDialogInterface() {

						public boolean cancel() {

							return false;
						}

						@SuppressWarnings("deprecation")
						public boolean ok() {
							goToBackupRestore();

							return false;
						}
					}, false);
			
			break;
		case R.id.learn_remote:
			if(Value.exist){
			Value.modeLearning = true;
			startActivity(new Intent(this, LearnRemote.class));
			}else {
				showPopupMessage((getResources().getString(R.string.no_device_quit)), (getResources().getString(R.string.warning)),
						new ConfirmPopupInterface() {

							

							@SuppressWarnings("deprecation")
							public void ok() {

							
							}
						});
			}
			break;
		case R.id.add_remote:
			goToAddDevice();
			break;
		case R.id.list_remotes:
			goToListRemotes();
			break;
		case R.id.help:
			// startActivity(new Intent(this, HelpActivity.class));
			temp2Data();
			break;
		case R.id.contact_us:
			Uri localUri = Uri.parse("http://www.etek.com.cn/");
			Intent localIntent3 = new Intent("android.intent.action.VIEW");
			localIntent3.setData(localUri);
			startActivity(localIntent3);
			break;
		case R.id.vibrations:
			if (retrieveStringFromPreff("vibration", "true").equals("true")) {
				putStringToPreff("vibration", "false");
				Value.vibrate = false;	
			} else {
				putStringToPreff("vibration", "true");
				Value.vibrate = true;	
			}
			initCheckboxes();
			break;
		case R.id.audio_exist:
			if (Value.exist) {
				Value.exist = false;
				putStringToPreff("exist", "false");
			} else {
				Value.exist = true;
				putStringToPreff("exist", "true");
			}
			initCheckboxes();
			break;
		case R.id.send_animation:
			if (Value.animation) {
				Value.animation = false;
				putStringToPreff("animation", "false");
			} else {
				Value.animation = true;
				putStringToPreff("animation", "true");
			}
			initCheckboxes();
			break;
		case R.id.terminal_power:
			
			showPopupOkCancel( getResources().getString(R.string.warning),getResources().getString(R.string.power_change),
					new ConfirmDialogInterface() {

						public boolean cancel() {

							return false;
						}

						@SuppressWarnings("deprecation")
						public boolean ok() {
							if (retrieveStringFromPreff("power", "false").equals("true")) {
								putStringToPreff("power", "false");
							} else {
								putStringToPreff("power", "true");
							}
							ApplicationContext.getInstance().onTerminate();

							return false;
						}
					}, false);
		//	initCheckboxes();
			break;
		case R.id.exit:
			showPopupOkCancel(getResources().getString(R.string.exit), "",
					new ConfirmDialogInterface() {

						public boolean cancel() {

							return false;
						}

						@SuppressWarnings("deprecation")
						public boolean ok() {

							ApplicationContext.getInstance().onTerminate();

							return false;
						}
					}, false);

			break;
			
		case R.id.settingsBck:
			finish();
			break;
		default:
			break;
		}

	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.settings);
		initLayout();
		ApplicationContext.getInstance().addActivity(this);
		ApplicationContext.getInstance().addSetupsActivity(this);
		((ScrollView) findViewById(R.id.scroll1))
				.setOnTouchListener(new View.OnTouchListener() {
					public boolean onTouch(View view,
							MotionEvent paramMotionEvent) {
						if (view.getScrollY() > 2) {
							findViewById(R.id.scroll_shade).setVisibility(
									View.GONE);
							view.setOnTouchListener(null);
							return true;
						}
						return false;
					}
				});
		mUserDB = new UserDB(this);
	}

	private void createNamePopup(String name) {
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
		nameEdit.setHint(name);
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
						} else {
							Value.remote.setName(nameEdit.getText().toString());

							// ListRemotes.writeRemoteOnSdcard((RemoteDevice)ListRemotes.remotesList.get(arg2));
							dialog.dismiss();
							startActivity(new Intent(Settings.this,
									SelectDeviceType.class));
						}

					}
				});
	}

	
	public void temp2Data() {
		File outFile = new File(Environment.getExternalStorageDirectory()
		// .getPath()
		// + File.separatorChar + AUDIO_RECORDER_FOLDER
		// + File.separatorChar +filename);
				, AUDIO_RECORDER_DATA_FILE);
		if (outFile.exists()) {
			outFile.delete();
		}

		try {
			byte[] tmpByte = Tools.readFileFromSDcard(AUDIO_RECORDER_TEMP_FILE);

			short[] tmpBuf = Tools.byteArrayToShortArray(tmpByte);
			FileOutputStream os = new FileOutputStream(outFile);// 输出文件流
			Log.v(TAG, "tmpBuf length is " + tmpBuf.length);
			String tmpStr = "";
			for (int i = 0; i < tmpBuf.length; i++) {
				// Log.v(TAG,"short[" + i + "]=====>" +
				// Tools.shortToHex(tmpBuf[i]));
				tmpStr = String.valueOf(tmpBuf[i]) + ",\n";
				os.write(tmpStr.getBytes());
			}

			os.flush();
			os.close();
			Log.d("CodeWatch", "save successed");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
