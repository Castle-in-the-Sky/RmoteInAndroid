package com.audiomobile.ircore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.audiomobile.remote.R;
import com.audiomobile.data.Value;
import com.audiomobile.utils.Tools;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
public class Decode {
        private ArrayList<short[]> inBuf = new ArrayList<short[]>();
        private boolean isRecording = false;
        private RecordThread rec;
 //       private DrawThread draw;
        final int Frequency = 44100;
       int index = 0;
 //       public int rateX = 4;
       
  //      public int rateY = 4;
        
        public int baseLine = 0;
        
        private String TAG = "Decode";
        public static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.txt";
        private static final String AUDIO_RECORDER_DATA_FILE = "record_data.txt";
        AudioRecord audioRecord;
        boolean valid = false;
        boolean sample = false;
        int recBufSize;
		private Handler codeHandler;
        public void initOscilloscope() {
              //  this.rateX = rateX;
              //  this.rateY = rateY;
              //  this.baseLine = baseLine;
                recBufSize = AudioRecord.getMinBufferSize(Frequency,
                		AudioFormat.CHANNEL_CONFIGURATION_MONO,  AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(AudioSource.MIC, Frequency,
        		AudioFormat.CHANNEL_CONFIGURATION_MONO,  AudioFormat.ENCODING_PCM_16BIT, recBufSize);
        }
        
        public void setHandler(Handler _handler) {
    		codeHandler = _handler;
    	//	Log.v(TAG, "codeHandler is " + codeHandler.toString());
    	}
        
        /**
         * ��ʼ
         * 
         * @param recBufSize
         *            AudioRecord��MinBufferSize
         */
        public void Start( ) {
                isRecording = true;
                rec = new RecordThread(audioRecord, recBufSize);
                rec.start();
//                draw = new DrawThread(sfv, mPaint);
//                draw.start();
//                index = 0;
//                Log.e(TAG, "sfv is " + sfv.toString() + "my parint is " + mPaint.toString());
        }
        /**
         * ֹͣ
         */
        public void Stop() {
        	    if(isRecording==true)
                {
        	    	isRecording = false;
        	    	inBuf.clear();// ���
        	    	rec = null;
 //       	    	draw = null;
                }else {
                	rec = null;
//        	    	draw = null;
                }
        }
        class RecordThread extends Thread {
                private int recBufSize;
                private AudioRecord audioRecord;
				
                public RecordThread(AudioRecord audioRecord, int recBufSize) {
                        this.audioRecord = audioRecord;
                        this.recBufSize = recBufSize;
                }
                public void run() {
                        try {
                        	 File file = new File(Environment.getExternalStorageDirectory(), AUDIO_RECORDER_TEMP_FILE);
                        	 
							if(file.exists()){
								file.delete();
								}
                        	  FileOutputStream os = new FileOutputStream(file);//输出文件流
                        	                
                                short[] buffer = new short[recBufSize];
                              
                                audioRecord.startRecording();
                   //             Log.e(TAG , "audioRecord status ---> " + audioRecord.getState());
                                while (isRecording) {
                                       
                                        int bufferReadResult = audioRecord.read(buffer, 0,
                                                        recBufSize);
                                      
                                        short[] tmpBuf = new short[bufferReadResult];
                              //          Log.e(TAG , "bufferReadResult---> " + bufferReadResult);
                                        System.arraycopy(buffer, 0, tmpBuf, 0,
                								bufferReadResult);
//                                        for (int i = 0, ii = 0; i < tmpBuf.length; i++, ii = i
//                                                        * rateX) {
//                                                tmpBuf[i] = buffer[ii];
//                                        }
//                                        synchronized (inBuf) {//
//                                            inBuf.add(tmpBuf);// ������
//                                    }
                                        valid = recodeDataIsValid(tmpBuf);
                                        if (!sample&&valid){
                                        //	Log.e(TAG, "first correct sample ---> " + sample + "first correct valid ---> " +valid );
                                        	sample = true;
                                        	index = 0;
                                        }
                                        if (sample&&!valid){
                                        	Log.e(TAG, "valid out  sample ---> " + sample + "valid out valid ---> " +valid );
                                        	if (index>2){
                                        	//	recordData2SDcard();
                                        		Log.e(TAG, "right data collection");
                                        		isRecording = false;	
                                        	}else {
                                        		Log.e(TAG, "wrong data collection" + index);
                                        		sample = false;
                                        		index = 0;
                                        		os.flush();
                                        	}
                                        	
                                        
                                        }
                                        if (sample&&valid){
//                                        	Log.e(TAG, "valid in  sample ---> " + sample + "valid in valid ---> " +valid );
//                                        	Log.e(TAG, "valid value is in" + index);
                                        	index ++;
                                        byte[] tmpBuffer = Tools.shortArrayToByteArray(buffer);
                                  //      Log.e(TAG, "tmpBuffer length  is " + tmpBuffer.length);
                                        os.write(tmpBuffer);
                                        }
                                        
                                }
                                os.flush();
                    			os.close();
                    			Log.e(TAG, "finish  ---> " );
                           //     audioRecord.stop();
                                
                          //      Log.e(TAG, "codehandle ->" +codeHandler.toString());
                    			byte[] audioInputData = new byte[130];
                    			int bck = audioRecordDecode(audioInputData);
                    			Bundle b = new Bundle();
                    			if (bck<0){
                    				
                        			b.putString("decode","DecodeError"); 	
                    			}else {
          
                        			b.putString("decode",Tools.bytesToHexString(audioInputData,bck)); 	
                    			}
                    			
                        		Message msg = codeHandler.obtainMessage(R.id.FINISH_CODE, 1, -1);
                        		msg.setData(b);
                        		codeHandler.sendMessage(msg);
                        } catch (Throwable t) {
                        }
                }
        };
       
//        class DrawThread extends Thread {
//                private int oldX = 0;
//                private int oldY = 0;
//                private SurfaceView sfv;
//                private int X_index = 0;
//                private Paint mPaint;
//                public DrawThread(SurfaceView sfv, Paint mPaint) {
//                        this.sfv = sfv;
//                        this.mPaint = mPaint;
//                }
//                @SuppressWarnings("unchecked")
//				public void run() {
//                	try{
//                		
//                		Log.e(TAG , "is recording -->" + isRecording);
//                        while (isRecording) {
//                                ArrayList<short[]> buf = new ArrayList<short[]>();
//                                synchronized (inBuf) {
//                                        if (inBuf.size() == 0)
//                                                continue;
//                                        buf = (ArrayList<short[]>) inBuf.clone();
//                                        inBuf.clear();// ���
//                                }
//                                for (int i = 0; i < buf.size(); i++) {
//                                        short[] tmpBuf = buf.get(i);
//                                        SimpleDraw(X_index, tmpBuf, baseLine);
//                                        X_index = X_index + tmpBuf.length;
//                                        if (X_index > sfv.getWidth()) {
//                                                X_index = 0;
//                                        }
//                                }
//                        }
//                     
//                	}catch(Exception e){}
//                }
//               
//                void SimpleDraw(int start, short[] buffer, int baseLine) {
//                        if (start == 0)
//                                oldX = 0;
//                        Canvas canvas = sfv.getHolder().lockCanvas(
//                                        new Rect(start, 0, start + buffer.length, sfv.getHeight()));
//                        canvas.drawColor(Color.WHITE);
//                        int y;
//                        for (int i = 0; i < buffer.length; i++) {
//                                int x = i + start;
//                                y = buffer[i]/100+ baseLine;
//                                canvas.drawLine(oldX, oldY, x, y, mPaint);
//                                oldX = x;
//                                oldY = y;
//                        }
//                        sfv.getHolder().unlockCanvasAndPost(canvas);
//                }
//        
//        
//        
//	};
	
	private boolean recodeDataIsValid(short[] buffer) {
		int j = 0;

		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] > 15000) {
				j++;
				if (j > 8) {
			//		Log.v(TAG, "get usrful information");
					
					return true;
				}
			}
//			} else {
//				j = 0;
//			}
			

		}

		return false;
	}
	public void recordData2SDcard() {
			byte[] tmpByte;
			
			try {
				tmpByte = Tools.readFileFromSDcard(AUDIO_RECORDER_TEMP_FILE);

				short[] tmpBuf = Tools.byteArrayToShortArray(tmpByte);
				Log.v(TAG,"tmpBuf length is " + tmpBuf.length);
				 String tmpStr = "";
				 for (int i = 0; i < tmpBuf.length; i++) {
				 // Log.v(TAG,"short[" + i + "]=====>" +
				 // Tools.shortToHex(tmpBuf[i]));
					 tmpStr +=String.valueOf(tmpBuf[i]) + ", \n";
				 }
				Tools.saveFileToSDcard(tmpStr, AUDIO_RECORDER_DATA_FILE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	private int audioRecordDecode(byte[] audioInputData) {
		// TODO Auto-generated method stub
		int j = 0;
		int i;
		byte d = 0;
		boolean upFlag = true;
		boolean reveral = false;
		int status = 0;
		long start = System.currentTimeMillis();
		// Log.e(TAG, "audioRecordDecode start--->" + start);
		try {
			byte[] tmpByte = Tools.readFileFromSDcard(AUDIO_RECORDER_TEMP_FILE);
			short[] tmpBuf = Tools.byteArrayToShortArray(tmpByte);
			byte[] dataBitHigh = new byte[2048];
			byte[] dataBitLow = new byte[2048];
			for (i = 0; i < tmpBuf.length; i++) {
				switch (status) {
				case 0: // detect value
					if (tmpBuf[i] > 20000) {
						upFlag = true;
						status = 1;
						reveral = false;
					} else if (tmpBuf[i] < -20000) {
						upFlag = false;
						status = 1;
						reveral = true;
					}
					break;
				case 1:
					if (upFlag) { // if data is up level
						if (tmpBuf[i] > 1000) {
							d++;
							if (d > 250)
								return -1;

						} else if (tmpBuf[i] < -1000) {
							if (d > 2) {
								dataBitHigh[j] = d;
								d = 0;
								upFlag = false;
//							Log.e(TAG, "tmpBuf---->" + i + " up[" + j
//										+ "] is " + dataBitHigh[j]);
								if (reveral)j++;
							}
						}

					} else { // if data is down level
						if (tmpBuf[i] < -1000) {
							d++;
							if (d > 250)
								return -1;
						} else if (tmpBuf[i] > 1000) {
							if (d > 2) {
								dataBitLow[j] = d;
								d = 0;
								upFlag = true;
								// Log.e(TAG, "tmpBuf---->" + i + " down[" + j +
								// "] is " + dataBitLow[j]);
								if (!reveral)j++;
								
							}
						}
					}
					break;
				default:
					break;
				}

			}
			int dataLen = j+1;

			i = 0;
			j = 0;
			int bitCount = 0;
			byte bTemp = 0;
			if (dataBitHigh[0] < 55) {
				Log.e(TAG, "first data is wrong");
				return -2;
			}
			for (i = 1; i < dataLen; i++) {
//				Log.e(TAG, "dataBitHigh---->" + i 
//				+ "] is " + dataBitHigh[i]);
				bTemp >>= 1;
				if (dataBitHigh[i] > 15) {
					bTemp |= 0x80;
				} else {
					bTemp &= 0x7f;
				}

				bitCount++;
				if (bitCount > 7) {
					bitCount = 0;
					audioInputData[j++] = bTemp;
					// Log.v(TAG, "audiomobiledeData  [" + i
					// +"]--->"+SoundCommunicate.bytesToHex(byteTemp));
					bTemp = 0;

				}
				if (dataBitHigh[i] > 40) {
				//	Log.e(TAG, "end data is right,all data length is " + j);
					Log.e(TAG, "data is  " + Tools.bytesToHexString(audioInputData,j));
					long end = System.currentTimeMillis();
					Log.e(TAG, "log time equal " + (end - start));
					return j;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.e(TAG, "not get end status  ");

		return -3;

	}


}
