package com.etek.ircomm;

import com.audiomobile.data.Value;
import com.audiomobile.ircore.Encode;
import com.audiomobile.service.PowerSupplyService;

import com.audiomobile.utils.Tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import android.os.IBinder;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SendOut {
	private static boolean rmtIsSending = false;
	private final String TAG = "SendOut";
	/*
	 * 此buffer的大小影响到发送数据缓存区的大小， 发送数据缓存区大小计算:
	 */
	public static int rmtMinBufferSize = AudioTrack.getMinBufferSize(44100,
			AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_8BIT) * 5;

	static AudioTrack rmtAT;
	static Encode ec = new Encode();;
	public static PowerSupplyService psService;

	public boolean rmtIsSending() {
		return rmtIsSending;
	}

	public static void rmtInitial() {
		if (Value.powerSupply) {
			Intent serviceIntent = new Intent(Value.mAppContext,
					PowerSupplyService.class);
			Value.mAppContext.bindService(serviceIntent, sc,
					Context.BIND_AUTO_CREATE);
			
		}
	}
	
	
	private static ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub

			psService = ((PowerSupplyService.MyBinder) service).getService();
			if (psService != null) {
				psService.adStart();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	};
	


	public static void rmtStrSend(String rmtData) {
		if (rmtIsSending)
			rmtStop();
		byte[] bts = ec.getSteroDataNoPow(Tools.hexStringToBytes(rmtData));
		rmtAT = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
				AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_8BIT,
				rmtMinBufferSize, AudioTrack.MODE_STATIC);

		rmtIsSending = true;

		rmtAT.write(bts, 0, rmtMinBufferSize);
		rmtAT.flush();
		rmtAT.setStereoVolume(1, 1);

		rmtAT.play();

	}

	public static void rmtByteSend(byte[] cmdData) {
		if (rmtIsSending)
			rmtStop();
		Log.e("test", "rmtStrSend is send");
		byte[] bts = ec.getSteroDataNoPow(cmdData);
		rmtAT = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
				AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_8BIT,
				rmtMinBufferSize, AudioTrack.MODE_STATIC);

		rmtIsSending = true;

		rmtAT.write(bts, 0, rmtMinBufferSize);
		rmtAT.flush();
		rmtAT.setStereoVolume(1, 1);

		rmtAT.play();

	}

	public static void rmtStop() {
		if (rmtAT != null) {
			rmtAT.release();
			rmtAT = null;
		}
		rmtIsSending = false;
	}

	private static Vibrator vibrator;

	public static void sendRemoteCode(final ImageView codeSending,
			final String code) {

		if (!code.equals("")) {
			if (Value.vibrate) {
				vibrator = (Vibrator) Value.mAppContext
						.getSystemService(Context.VIBRATOR_SERVICE);
				long[] pattern = { 100, 100 };
				vibrator.vibrate(pattern, 1);
			}
			if (Value.animation) {
				AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F,
						1.0F);
				localAlphaAnimation.setDuration(200L);
				localAlphaAnimation
						.setAnimationListener(new AnimationListener() {

							public void onAnimationEnd(Animation paramAnimation) {
								// rmtSend.rmtStart(code);
								if (!Value.powerSupply) {
									SendOut.rmtStrSend(code);
								} else {
									psService.setMsgWave(code);
									psService.setMsgSend(true);
								}
								AlphaAnimation localAlphaAnimation = new AlphaAnimation(
										1.0F, 0.0F);
								localAlphaAnimation.setDuration(200L);
								localAlphaAnimation
										.setAnimationListener(new AnimationListener() {
											public void onAnimationEnd(
													Animation paramAnimation) {
												codeSending
														.setVisibility(View.GONE);
											}

											public void onAnimationRepeat(
													Animation paramAnimation) {
											}

											public void onAnimationStart(
													Animation paramAnimation) {
											}
										});
								codeSending.startAnimation(localAlphaAnimation);

							}

							public void onAnimationRepeat(
									Animation paramAnimation) {
							}

							public void onAnimationStart(
									Animation paramAnimation) {
								codeSending.setVisibility(View.VISIBLE);
							}
						});
				codeSending.startAnimation(localAlphaAnimation);
			} else {
				if (!Value.powerSupply) {
					SendOut.rmtStrSend(code);
				} else {
					psService.setMsgWave(code);
					psService.setMsgSend(true);
				}
			}
		}
	}
}
