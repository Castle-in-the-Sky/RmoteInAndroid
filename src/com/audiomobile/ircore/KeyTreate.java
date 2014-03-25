package com.audiomobile.ircore;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.audiomobile.data.AirData;
import com.audiomobile.data.Globals;
import com.audiomobile.data.Value;
import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.remote.R;
import com.database.UserDB;
import com.etek.ircomm.RemoteComm;

public class KeyTreate {
	private final static String TAG = "KeyTreate";
	private Handler mHandler;

	private static UserDB userDB;

	private static KeyTreate mKeyTreate = null;

	public void setHandler(Handler _handler) {
		mHandler = _handler;
	//	Log.v(TAG, "mHandler is " + mHandler.toString());
	}

	public static KeyTreate getInstance() {
		if (mKeyTreate == null) {
			mKeyTreate = new KeyTreate();
		}
		return mKeyTreate;
	}

	public void keyHandler(int device, String keyName) {
		String rmtData = "";
//		Log.v(TAG, "test mode is " + Value.test_mode);
		if (0x01 == Value.test_mode) {
			rmtData = Value.KeyTable.get(keyName);
		} else  {
			if (userDB == null) {
				userDB = new UserDB(Value.mAppContext);
			}

			// Log.v(TAG,"device=====>" + device +"      keyName=====>" +
			// keyName);

			try {
				userDB.open();
				rmtData = userDB.getRemoteData(device, keyName);
				userDB.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Log.v(TAG, rmtData);
			// byte[] rmtDataByte = Tools.hexStringToBytes(rmtData);

			// RemoteCommunicate.sendaudiomobileRemote(rmtData);

			// }
		}
		// Log.v(TAG, rmtData);
		remoteSend(rmtData);

	}

	public void airKeyHandler(AirData airData) {

		// RemoteCommunicate.sendaudiomobileAirRemote(airData);
		String rmtData = RemoteComm.getAirData(airData);
//		Log.v(TAG, rmtData);
		remoteSend(rmtData);

	}

	private synchronized void remoteSend(String data) {

		if (mHandler == null)
			return;
		Message msg = mHandler.obtainMessage(R.id.REMOTE_SEND, 1, -1);

		Bundle bundle = new Bundle();
		bundle.putString("remote", data); // 往Bundle中存放数据
		msg.setData(bundle);// mes利用Bundle传递数据
		mHandler.sendMessage(msg);
	}

}
