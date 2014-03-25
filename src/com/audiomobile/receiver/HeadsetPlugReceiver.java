package com.audiomobile.receiver;



import com.audiomobile.remote.R;
import com.audiomobile.data.Value;
import com.audiomobile.service.PowerSupplyService;

import com.etek.ircomm.SendOut;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class HeadsetPlugReceiver extends BroadcastReceiver {
//	private final int audioSupplyFreq = 3000;
//	final static int frequency = 44100;
//	static final int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
//	static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	private static final String TAG = "HeadsetPlugReceiver";
//	PowerSupplyService psService;
	

	@Override
	public void onReceive(Context context, Intent intent) {
		  if (intent.hasExtra("state")){
			   if (intent.getIntExtra("state", 0) == 0){
				   Value.exist = false;
				   Toast.makeText(context, R.string.remote_isnot_connected, Toast.LENGTH_SHORT).show();
			//	SendOut.psService.adStop();
			   }
		
			   else {

					
	
					Value.exist = true;
					AudioManager am = (AudioManager) Value.mAppContext.getSystemService(Context.AUDIO_SERVICE);
					am.setStreamVolume(AudioManager.STREAM_MUSIC,
							am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
							AudioManager.FLAG_PLAY_SOUND);
					 Toast.makeText(context, R.string.remote_is_connected, Toast.LENGTH_SHORT).show();
//					 rmtSend = new RemoteSendOut();
//					 rmtSend.rmtInitial();
					  if (Value.powerSupply){
				//			SendOut.psService.adStart();
//				   Intent serviceIntent = new Intent(context, PowerSupplyService.class);          
//			        context.bindService(serviceIntent, sc, Context.BIND_AUTO_CREATE);
					  }
				  
			   }
		  }
	//	  int microphone = intent.getIntExtra("microphone", 0);
	//	  String name = intent.getStringExtra("name");
	//	  Log.v(TAG, "micphone name ->"+ name + "with microphone-->"+ microphone);
		
	}
	/*
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			psService = ((PowerSupplyService.MyBinder) (service)).getService();  
            if (psService != null) {  
            	psService.supply();       
            }  
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}  
		  
       
    };  
      
   */
          
      
    
}
