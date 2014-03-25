package com.audiomobile.ircore;

import java.util.Arrays;

import android.media.AudioFormat;
import android.media.AudioTrack;
import android.util.Log;

public class Encode {

	private final static String TAG = "EncoderCore";

	private final static int SampleRate = 44100;
	// private final static int MINTIME = 10000/441;

	private static int encoderBufferSize = 0;

	private final static int rmtBuffSize = AudioTrack.getMinBufferSize(
			SampleRate, AudioFormat.CHANNEL_OUT_STEREO,
			AudioFormat.ENCODING_PCM_8BIT);

	// private static byte[] SignalData =
	// {-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67
	// ,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67
	// ,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67
	// ,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67,-128,-128,-74,74,29,-29,-4,4,2,-2,-26,26,67,-67
	// };
	// private static byte[] Signal8000 = {
	// -128,-128,-18,18,-18,18,127,-127,18,-18
	// };
	// private static byte[] Signal1000 = {
	// -128,-128,-111,111,-95,95,-79,79,-64,64,-51,51,-38,38,-27,27,-18,18,-11,11,-5,5,-2,2,-1,1,-2,2,-5,5,-11,11,-18,18,-27,27,-38,38,-51,51,-65,65,-79,79,-95,95,-111,111,-128,-128,111,-111,95,-95,79,-79,64,-64,51,-51,38,-38,27,-27,18,-18,11,-11,5,-5,2,-2,1,-1,2,-2,5,-5,11,-11,18,-18,27,-27,38,-38,51,-51,65,-65,79,-79,95,-95,111,-111
	// };

	private static byte[] SignalZero = {
			// -128,-128,-79,79,-38,38,-11,11,-1,1,-11,11,-38,38,-79,79,-128,-128,79,-79,38,-38,11,-11,1,-1,11,-11,38,-38,79,-79
			-128, -128, -1, 1, -128, -128, 1, -1 };

	private static byte[] SignalOne = {
			// -128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128
			-128, -128, -38, 38, -1, 1, -38, 38, -128, -128, 38, -38, 1, -1,
			38, -38 };
	private static byte[] SignalHead = {
			// -128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,
			// -128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,
			// -128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128
			-128, -128, -111, 111, -95, 95, -79, 79, -64, 64, -51, 51, -38, 38,
			-27, 27, -18, 18, -11, 11, -5, 5, -2, 2, -1, 1, -2, 2, -5, 5, -11,
			11, -18, 18, -27, 27, -38, 38, -51, 51, -65, 65, -79, 79, -95, 95,
			-111, 111, -128, -128, 111, -111, 95, -95, 79, -79, 64, -64, 51,
			-51, 38, -38, 27, -27, 18, -18, 11, -11, 5, -5, 2, -2, 1, -1, 2,
			-2, 5, -5, 11, -11, 18, -18, 27, -27, 38, -38, 51, -51, 65, -65,
			79, -79, 95, -95, 111, -111 };
	private static byte[] SignalBase;
	private static byte[] SignalEnd;

	public static int getRmtBuffSize() {
		return rmtBuffSize;
	}

	// public int getPowerBuffSize(){
	// return pwBuffSize;
	// }

	public static int getMsgSamplerate() {
		return SampleRate;
	}

	public static int getPowerSupplySamplerate() {
		return SampleRate;
	}

	// public native static int getEncoderBufferSize();
	public static int getEncoderBufferSize() {
		return encoderBufferSize;

	}

	private byte[] SignalPow;

	public byte[] getSteroData(byte[] data) {

		int m;

		// SignalOne=SinWave.sin(waveLen, length);
		// SignalOne = sin();
		SignalOne = SinWave.singleSteroSignal(2000);
		SignalZero = SinWave.singleSteroSignal(4000);
		SignalPow = SinWave.singleSteroSignal(3000);
		SignalHead = SinWave.singleSteroSignal(1500);
		// SignalBase = SinWave.singleSteroSignal(3000);
		SignalEnd = SinWave.singleSteroSignal(1500);
		// for (byte w : SignalOne){
		// Log.v(TAG, "singalone ===>" + w);
		// }

		byte[] waveform = new byte[44100];
//		waveform = SinWave.setPowerFreq(3000);
//		for (int i = 0; i < 1000; i++) {
//			waveform[i] = (byte) -127;
//		}
		 Arrays.fill(waveform, (byte) -127);
		boolean[] bits = new boolean[data.length * 8];
		// byte[] waveform = new byte[(sendme.length*bytesinframe*sampleRate /
		// baudRate)]; // 8 bit, no parity, 1 stop
		// Arrays.fill(waveform, (byte) 0);
		Arrays.fill(bits, true); // slight opti to decide what to do with stop
									// bits
		m = 0;
		// generate bit array first: makes it easier to understand what's going
		// on
		for (int i = 0; i < data.length; ++i) {

			bits[m++] = ((data[i] & 1) == 1);// ?false:true;
			bits[m++] = ((data[i] & 2) == 2);// ?false:true;
			bits[m++] = ((data[i] & 4) == 4);// ?false:true;
			bits[m++] = ((data[i] & 8) == 8);// ?false:true;
			bits[m++] = ((data[i] & 16) == 16);// ?false:true;
			bits[m++] = ((data[i] & 32) == 32);// ?false:true;
			bits[m++] = ((data[i] & 64) == 64);// ?false:true;
			bits[m++] = ((data[i] & 128) == 128);// ?false:true;

		}

		// now generate the actual waveform using l to wiggle the DAC and
		// prevent it from zeroing out
		int point = 1000;
		// int j = 32;
		// // point += 1000;
		// while ((j--)>0){
		// System.arraycopy(SignalBase, 0, waveform, point, SignalBase.length);
		// point += SignalBase.length;
		// }
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// point += 10000;
		for (int i = 0; i < bits.length; i++) {
			// Log.v(TAG, "bits value  " + i + "is " + bits[i] );
			if (bits[i] == true) {
				System.arraycopy(SignalOne, 0, waveform, point,
						SignalOne.length);
				point += SignalOne.length;
			} else {
				System.arraycopy(SignalZero, 0, waveform, point,
						SignalZero.length);
				point += SignalZero.length;
			}

		}
		// int j = 1;
		 point += 1000;
		// while ((j--)>0){
		// System.arraycopy(SignalEnd, 0, waveform, point, SignalEnd.length);
		// point += SignalEnd.length;
		// }

		// for (int i=0;i<point;i++ ){
		// Log.v(TAG, "waveform value  " + i + "is " + waveform[i] );
		//
		// }
		// Log.v(TAG, "point is "+ point );
//		for (int i = 0; i < 1000; i++) {
//			waveform[point + i] = (byte) -127;
//		}
		
		while (point < 44000){
			System.arraycopy(SignalPow, 0, waveform, point,
					SignalPow.length);
			point += SignalPow.length;
		}

//		byte[] wavetemp = new byte[point];
//		 System.arraycopy(waveform, 0, wavetemp, 0,point);

		 return waveform;
		//return wavetemp;
	}

	public byte[] getSinPowSinData(byte[] data, int Hz) {

		int m;
		int waveLen, length;
		waveLen = SampleRate / Hz;
		length = waveLen * Hz;
		byte[] waveform = new byte[SampleRate];
		waveform = SinWave.sin(waveLen, length);
		waveform = SinWave.monoToSingle(waveform);

		// SignalOne=SinWave.sin(waveLen, length);
		// SignalOne = sin();
		SignalOne = SinWave.singleMonoSignal(4000);
		SignalZero = SinWave.singleMonoSignal(8000);
		SignalHead = SinWave.singleMonoSignal(2000);
		SignalBase = SinWave.singleMonoSignal(6000);
		SignalEnd = SinWave.singleMonoSignal(3000);
		// for (byte w : SignalOne){
		// Log.v(TAG, "singalone ===>" + w);
		// }

		boolean[] bits = new boolean[data.length * 8];
		// byte[] waveform = new byte[(sendme.length*bytesinframe*sampleRate /
		// baudRate)]; // 8 bit, no parity, 1 stop
		// Arrays.fill(waveform, (byte) 0);
		Arrays.fill(bits, true); // slight opti to decide what to do with stop
									// bits
		m = 0;
		// generate bit array first: makes it easier to understand what's going
		// on
		for (int i = 0; i < data.length; ++i) {

			bits[m++] = ((data[i] & 1) == 1);// ?false:true;
			bits[m++] = ((data[i] & 2) == 2);// ?false:true;
			bits[m++] = ((data[i] & 4) == 4);// ?false:true;
			bits[m++] = ((data[i] & 8) == 8);// ?false:true;
			bits[m++] = ((data[i] & 16) == 16);// ?false:true;
			bits[m++] = ((data[i] & 32) == 32);// ?false:true;
			bits[m++] = ((data[i] & 64) == 64);// ?false:true;
			bits[m++] = ((data[i] & 128) == 128);// ?false:true;

		}

		// now generate the actual waveform using l to wiggle the DAC and
		// prevent it from zeroing out
		int point = 0;
		int j = 16;
		// point += 1000;
		while ((j--) > 0) {
			point = SinWave.dataCopyToWave(SignalBase, waveform, point);
			// point += SignalBase.length;
		}
		point = SinWave.dataCopyToWave(SignalHead, waveform, point);
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// point += 10000;
		for (int i = 0; i < bits.length; i++) {
			// Log.v(TAG, "bits value  " + i + "is " + bits[i] );
			if (bits[i] == true) {
				point = SinWave.dataCopyToWave(SignalOne, waveform, point);
			} else {
				point = SinWave.dataCopyToWave(SignalZero, waveform, point);
			}

		}
		j = 4;
		// point += 1000;
		while ((j--) > 0) {
			point = SinWave.dataCopyToWave(SignalEnd, waveform, point);
		}

		// for (int i=0;i<point;i++ ){
		// Log.v(TAG, "waveform value  " + i + "is " + waveform[i] );
		//
		// }
		// byte[] wavetemp = new byte[point];
		// System.arraycopy(waveform, 0, wavetemp, 0,point);

		// Log.v(TAG, "point is "+ point );
		return waveform;

	}

	public byte[] getSteroDataNoPow(byte[] data) {

		int m;

		SignalOne = SinWave.singleSteroSignal(4000);
		SignalZero = SinWave.singleSteroSignal(8000);
		SignalHead = SinWave.singleSteroSignal(2000);
		SignalBase = SinWave.singleSteroSignal(6000);
		SignalEnd = SinWave.singleSteroSignal(2000);
		// for (byte w : SignalOne){
		// Log.v(TAG, "singalone ===>" + w);
		// }
		byte[] waveform = new byte[44100];
		Arrays.fill(waveform, (byte) -127);
		boolean[] bits = new boolean[data.length * 8];
		// byte[] waveform = new byte[(sendme.length*bytesinframe*sampleRate /
		// baudRate)]; // 8 bit, no parity, 1 stop
		// Arrays.fill(waveform, (byte) 0);
		Arrays.fill(bits, true); // slight opti to decide what to do with stop
									// bits
		m = 0;
		// generate bit array first: makes it easier to understand what's going
		// on
		for (int i = 0; i < data.length; ++i) {

			bits[m++] = ((data[i] & 1) == 1);// ?false:true;
			bits[m++] = ((data[i] & 2) == 2);// ?false:true;
			bits[m++] = ((data[i] & 4) == 4);// ?false:true;
			bits[m++] = ((data[i] & 8) == 8);// ?false:true;
			bits[m++] = ((data[i] & 16) == 16);// ?false:true;
			bits[m++] = ((data[i] & 32) == 32);// ?false:true;
			bits[m++] = ((data[i] & 64) == 64);// ?false:true;
			bits[m++] = ((data[i] & 128) == 128);// ?false:true;

		}

		// now generate the actual waveform using l to wiggle the DAC and
		// prevent it from zeroing out
		int point = 0;
		int j = 32;
		// point += 1000;
		while ((j--) > 0) {
			System.arraycopy(SignalBase, 0, waveform, point, SignalBase.length);
			point += SignalBase.length;
		}
		System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// System.arraycopy(SignalHead, 0, waveform, point, SignalHead.length);
		// point += SignalHead.length;
		// point += 10000;
		for (int i = 0; i < bits.length; i++) {
			// Log.v(TAG, "bits value  " + i + "is " + bits[i] );
			if (bits[i] == true) {
				System.arraycopy(SignalOne, 0, waveform, point,
						SignalOne.length);
				point += SignalOne.length;
			} else {
				System.arraycopy(SignalZero, 0, waveform, point,
						SignalZero.length);
				point += SignalZero.length;
			}

		}
		// j = 8;
		// // point += 1000;
		// while ((j--)>0){
		// System.arraycopy(SignalOne, 0, waveform, point, SignalOne.length);
		// point += SignalOne.length;
		// }
		j = 8;
		// point += 1000;
		while ((j--) > 0) {
			System.arraycopy(SignalEnd, 0, waveform, point, SignalEnd.length);
			point += SignalEnd.length;
		}

		// Log.v(TAG, "point is "+ point );
		return waveform;

	}

}
