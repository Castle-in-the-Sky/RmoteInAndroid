package com.audiomobile.ircore;

public class SinWave {

	public static final int HEIGHT = 127;
	private static final int RATE = 44100;


	public static byte[] sin(int waveLen, int length) {
		byte[] wave = new byte[length];
		for (int i = 0; i < length; i++) {
			wave[i] = (byte) (HEIGHT * (1 - Math.sin(Math.PI*2
					* ((i % waveLen) * 1.00 / waveLen))));
		}
		return wave;
	}
	
	public static byte[] singleSignal(int freq){
		int waveLen = RATE / freq;
	//	int length = waveLen * freq;
		byte[] wave = new byte[waveLen*2];
		wave = sin(waveLen,waveLen);
		wave = monoToSingle(wave);
		return wave ;
	}
	public static byte[] singleMonoSignal(int freq){
		int waveLen = RATE / freq;
	//	int length = waveLen * freq;
		byte[] wave = new byte[waveLen];
		wave = sin(waveLen,waveLen);
		return wave ;
	}
	
	public static byte[] singleSteroSignal(int freq){
		int waveLen = RATE / freq;
	//	int length = waveLen * freq;
		byte[] wave = new byte[waveLen];
		wave = sin(waveLen,waveLen);
		wave = monoToStereo(wave);
		return wave ;
	}
	
	public static int waveLength(int freq){
		int waveLen = RATE / freq;
	
		return waveLen ;
	}

	public static byte[] monoToStereo(byte[] dataIn){
		 byte[] dataOut = new byte[dataIn.length*2];
		int j=0;
		 for (int i=0;i<dataIn.length;i++){
		dataOut[j++] = dataIn[i];	 
		dataOut[j++] = (byte) (~dataIn[i]+1);	 
		 }
		return dataOut;
		
	}
	public static byte[] monoToStereoSame(byte[] dataIn){
		 byte[] dataOut = new byte[dataIn.length*2];
		int j=0;
		 for (int i=0;i<dataIn.length;i++){
		dataOut[j++] = dataIn[i];	 
		dataOut[j++] = dataIn[i];	 
		 }
		return dataOut;
		
	}
	
	public static byte[] monoToSingle(byte[] dataIn){
		 byte[] dataOut = new byte[dataIn.length*2];
		int j=0;
		 for (int i=0;i<dataIn.length;i++){
		dataOut[j++] = dataIn[i];	 
		dataOut[j++] = (byte) 0;	 
		 }
		return dataOut;
		
	}
	
	public static int dataCopyToWave(byte[] dataIn,byte[] waveform,int point){
		
		int j=point;
		 for (int i=0;i<dataIn.length;i++){
		j++	; 
		waveform[j++] = dataIn[i];	 
		 }
		return j;
		
	}

	
	
	public static byte[] setPowerFreq(int Hz) {
		byte[] audioWave = new byte[RATE];
		int waveLen = RATE / Hz;
		int length = waveLen * Hz;
		audioWave = SinWave.sin(waveLen, length);
		audioWave = SinWave.monoToStereo(audioWave);
		return audioWave;
		
	}
	
}

