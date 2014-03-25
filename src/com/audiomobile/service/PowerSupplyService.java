package com.audiomobile.service;




import com.audiomobile.ircore.Encode;
import com.audiomobile.ircore.SinWave;
import com.audiomobile.utils.Tools;



import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;

import android.media.AudioTrack;
import android.os.Binder;
import android.os.RemoteException;


import android.os.IBinder;

import android.util.Log;

public class PowerSupplyService extends Service{
	private static final String TAG = "PowerSupplyService";

	private static final int AudioSupplyFreq = 3000;

	private final static int SampleFrequency = 44100;
	static final int ChannelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_STEREO;
	static final int AudioEncoding = AudioFormat.ENCODING_PCM_8BIT;
//	public static int audioSampleRate = 48000;

	public static int adMinBufferSize = AudioTrack.getMinBufferSize(
			SampleFrequency, ChannelConfiguration,
			AudioEncoding);
	AudioTrack adAT;

	

	private int waveLen;

	private int length;

	private byte[] powWave;
	private byte[] msgWave;
	private boolean powerFlag;

	public boolean msgSend;
	Encode ec;
	AudioThread adThread;
	
	 private final IBinder binder = new MyBinder();  
	  
	    public class MyBinder extends Binder {  
	    	public PowerSupplyService getService() {  
	            return PowerSupplyService.this;  
	        }

			@Override
			protected boolean onTransact(int code, android.os.Parcel data,
					android.os.Parcel reply, int flags) throws RemoteException {
				// TODO Auto-generated method stub
				return super.onTransact(code, data, reply, flags);
			}  
	    	
	    	
	    }  

	public boolean audioIsSupplying() {
		return powerFlag;
	}

	public void onCreate() {
		super.onCreate();  
		if (powerFlag)
			adStop();
		// Hz=3000;
//		waveLen = SampleFrequency / AudioSupplyFreq;
//		length = waveLen * AudioSupplyFreq;
		ec = new Encode();
		setPowerFreq(AudioSupplyFreq);
		adAT = new AudioTrack(AudioManager.STREAM_MUSIC, SampleFrequency,
				ChannelConfiguration, // CHANNEL_CONFIGURATION_MONO,
				AudioEncoding, SampleFrequency, AudioTrack.MODE_STREAM);
		Log.d(TAG, "length   =   " + length);
		Log.d(TAG, "waveLen   =   " + waveLen);
		Log.d(TAG, "audio service start");
		// 生成正弦波
//		audioWave = SinWave.sin(waveLen, length);
//		audioWave = SinWave.monoToStereo(audioWave);
		
	}

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		adStop();
		Log.v(TAG, "audio service end");
	}
	
	
	
	public void adStart(){
		if (adAT != null) {
			 adAT.play();
			}
			adThread = new AudioThread();
			adThread.start();
			
			powerFlag = true;
			
			Log.v(TAG, "audio power start");
	}
	public void adStop() {
		adThread = null;
		if (adAT != null) {
			adAT.stop();
			adAT.release();
			adAT = null;
		}
		powerFlag = false;
		
	}

	public boolean isMsgSend() {
		return msgSend;
	}

	public void setMsgSend(boolean msgSend) {
		this.msgSend = msgSend;
	}

	public void powPlay() {

		if (adAT != null) {
			try {
				adAT.write(powWave, 0, length);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void msgPlay() {
		if (adAT != null) {
			adAT.write(msgWave, 0, length);
		}
	}

	public void setPowerFreq(int Hz) {

		waveLen = SampleFrequency / Hz;
		length = waveLen * Hz;
		powWave = SinWave.sin(waveLen, length);
		powWave = SinWave.monoToStereo(powWave);
		
	}

	public void setMsgWave(String data) {
	
		msgWave = ec.getSteroData(Tools.hexStringToBytes(data));

	}
	 

	public class AudioThread extends Thread {
	
		public AudioThread() {
			

		}

		public void run() {
		
//				Log.v(TAG, "power");
				while (powerFlag == true) {
				
					if (!msgSend) {
//						Log.v(TAG, "power");
						powPlay();
					} else {
						Log.v(TAG, "message");
						msgPlay();
						msgSend = false;
					}
					// Log.v(TAG, "audio on off play");
				}
			
		}// run end
	}// RecordThread end

	 

	
//	private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            Log.i(TAG, "handleMessage");
//            switch (msg.what) {
////            case R.id.MSG_SEND_DATA:
////            	String str1 = msg.getData().getString("message"); 
////            	setMsgWave (str1);
////            	msgSend = true;
////               Log.v(TAG, "get message" +str1);
////             break;
//            default:
//                super.handleMessage(msg);
//            }
//
//        }
//    };
  

    @Override  
    public IBinder onBind(Intent intent) {  
    	 Log.v(TAG, "onbind");
    	 return binder;
  //  	 return mMessenger.getBinder();  
    }

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}  
	
}// end class

