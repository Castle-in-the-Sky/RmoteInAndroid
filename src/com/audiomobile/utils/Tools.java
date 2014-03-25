package com.audiomobile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.audiomobile.data.AirData;
import com.audiomobile.data.Value;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;



public final class Tools {
	   private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
	    public static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.txt";
	    private static final String AUDIO_RECORDER_DATA_FILE = "record_data.txt";
	    private static Context mContext;
		

		
		
		public static String shortToHex(short src) {
			StringBuilder stringBuilder = new StringBuilder("");

			int v = src & 0xffFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 4) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
			return stringBuilder.toString();
		}
		public static String shortToHexString(short[] src) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xffFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 4) {
					stringBuilder.append(0);
				}
				stringBuilder.append(hv);
			}
			return stringBuilder.toString();
		}
		
		

	
		 private String getFilename(){
		        String filepath = Environment.getExternalStorageDirectory().getPath();
		        File file = new File(filepath,AUDIO_RECORDER_FOLDER);
		        if(!file.exists()){
		            file.mkdirs();
		    }
		        File tempFile = new File(filepath,AUDIO_RECORDER_DATA_FILE);
//		        if(tempFile.exists()){
//		        	tempFile.delete();
//		        }
		                        
		        return (file.getAbsolutePath() +"/" + AUDIO_RECORDER_DATA_FILE );
		}

		public static String getTempFilename(){
		        String filepath = Environment.getExternalStorageDirectory().getPath();
		        File file = new File(filepath,AUDIO_RECORDER_FOLDER);
		        
		        if(!file.exists()){
		                file.mkdirs();
		        }
		        
		        File tempFile = new File(filepath,AUDIO_RECORDER_TEMP_FILE);
		        
//		        if(tempFile.exists())
//		                tempFile.delete();
		        
		        return (file.getAbsolutePath() + "/" + AUDIO_RECORDER_TEMP_FILE);
		}

		  //  copyWaveFile(getTempFilename(),getFilename());
		  //  deleteTempFile();


		static void deleteTempFile() {
		    File file = new File(getTempFilename());
		    
		    file.delete();
		}
		public static byte[] shortArrayToByteArray(short[] s) {
			   byte[] byteBuf = new byte[s.length];
			   
			   int j =0;
			   for(int i=0;i<s.length/2;i++) {
			  
			   byteBuf[j++] = (byte)((s[i]>>8)&0xff);
			   byteBuf[j++] = (byte)((s[i])&0xff);
			   }
			   return byteBuf;
			  }
		public static short[] byteArrayToShortArray(byte[] b) {
			   short[] shortBuf = new short[b.length/2];
			   
			   int j = 0;
			   int i = 0;
			   while( i<b.length){
				 
		           shortBuf[j++] = (short) (((b[i++] & 0xFF) << 8)+ (b[i++] & 0xFF));
			   }
			   return shortBuf;
			  }


		public static byte[] readFileFromSDcard(String filename) throws Exception
		{
		  
		    File file = new File(Environment.getExternalStorageDirectory()
//		            .getPath() 
//		            +  File.separatorChar + AUDIO_RECORDER_FOLDER 
//		            +  File.separatorChar +filename);
		    		,filename);
		    FileInputStream in=new FileInputStream(file);
		   
		    byte[] buffer = new byte[in.available()]; 
		   
		    in.read(buffer);            
		    
		    in.close();  
		    return buffer;
		} 
		
		public static void saveFileToSDcard( String datas,String fileName) {  
			
			  File file = new File(Environment.getExternalStorageDirectory()
//			            .getPath() 
//			            +  File.separatorChar + AUDIO_RECORDER_FOLDER 
//			            +  File.separatorChar +filename);
			    		,fileName);
	        try {  
	           
	        	 FileOutputStream os = new FileOutputStream(file);//输出文件流
	            os.write(datas.getBytes());  
	            os.flush();  
	            os.close();  
	            Log.d("CodeWatch", "save successed");
	      //      Toast.makeText(mContext, fileName+"save successed", Toast.LENGTH_LONG).show();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	  
	    }
		public static void saveDocumnet(Context mContext, String datas,String fileName) {  
			
		      
	        try {  
	           
	            FileOutputStream outputStream =mContext.openFileOutput(fileName,  
	                    Activity.MODE_PRIVATE);  
	            outputStream.write(datas.getBytes());  
	            outputStream.flush();  
	            outputStream.close();  
	            Toast.makeText(mContext, fileName+"save successed", Toast.LENGTH_LONG).show();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	  
	    }
		
		public static void saveDocumnet(short[] datas, String fileName) {
			
			
			try {
				 FileOutputStream outputStream =Value.mAppContext.openFileOutput(fileName,  
		                    Activity.MODE_PRIVATE);  
				  
				for (int i=0;i<datas.length;i++){
					if (datas[i]>10000||datas[i]<-10000){
					String str = String.valueOf(datas[i]) +"\n";
					
				outputStream.write(str.getBytes());
					}
				}
				outputStream.flush();
				outputStream.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
public static void saveDocumnet(byte[] datas, String fileName) {
			
			
			try {
				 FileOutputStream outputStream =Value.mAppContext.openFileOutput(fileName,  
		                    Activity.MODE_PRIVATE);  
				  
				for (int i=0;i<datas.length;i++){
				//	if (datas[i]>10000||datas[i]<-10000){
					String str = String.valueOf(datas[i]) +"\n";
					
				outputStream.write(str.getBytes());
			//		}
				}
				outputStream.flush();
				outputStream.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		public static byte[]  readFile(String fileName) throws IOException{   
			byte [] buffer = null;
			  try{   
			         FileInputStream fin =Value.mAppContext.openFileInput(fileName);   
			         int length = fin.available();   
			         buffer = new byte[length];   
			         fin.read(buffer);       
			        
			         fin.close();       
			     }   
			     catch(Exception e){   
			         e.printStackTrace();   
			     }   
			     return buffer;   
			  
			}  	
		
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
	
	public static String bytesToHexString(byte[] src,int length) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || length<= 0) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		//	Log.v("remoteSend", "data ----> " + d[i]);
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	public static int[] airDataToByte(AirData data) {
		int[] temp = new int[10];
		temp[0] = (int) data.getCode();
		temp[1] = (int) data.getmPower();
		temp[2] = (int) data.getmMode();
		temp[3] = (int) data.getmWindCount();
		temp[4] = (int) data.getmTmp();
		temp[5] = (int) data.getmWindDir();
		temp[6] = (int) data.getmWindAuto();
		
		return temp;  
    }
	
	
	public static int[] airDataToArray(AirData data) {
		int[] temp = new int[10];
		temp[0] = (int) data.getCode();
		temp[1] = (int) data.getmPower();
		temp[2] = (int) data.getmMode();
		temp[3] = (int) data.getmWindCount();
		temp[4] = (int) data.getmTmp();
		temp[5] = (int) data.getmWindDir();
		temp[6] = (int) data.getmWindAuto();
		temp[7] = (int) data.getMkey();
		return temp;  
    }
	public static int getValidLearnData(byte datas[]) {
		int i,length = 0,index;
		 index = datas[2]+2;
		 for (i=index;i<64;i++ ){
			 byte temp = datas[i];
		 if (((temp&0x0f)==0x00)||((temp&0xf0)==0x00)){
			 length = i + 2;
			 return length;
		 	}
		// Log.v(TAG, "learnData[" + i +"]---------->"+ datas[i]);
		 }
		//  Log.v(TAG, "learn data length --->" + length);
		  return 64;
	}
	
	public static String bytesToHex(byte src) {
		StringBuilder stringBuilder = new StringBuilder("");
	
			int v = src & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		return stringBuilder.toString();
	}
}
