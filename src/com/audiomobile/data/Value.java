package com.audiomobile.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.os.Handler;

public final class Value {
	public static int HEADER = 0x99;
	public final static String[] RemoteType ={ 
		"TV","DVD","STB","FAN","PJT","AIR","LIGHT","CAMERA","AMPLIFY"
	};
	public static boolean animation = false;
	

	
	
//	public  static String[] REMOTE_TYPE;
	public  static int selectStatus = 0;

	public static Boolean initial=true;
	public static Boolean audio= true;
//	public static Boolean powerSupply= false;
	public static Boolean modeLearning = false;
	public static String currentKey = null;
	public static int currentDevice = 0;
	public static Handler mHandler;
	public static Handler listHandler;
	public static Boolean exist = false;
	public static LearnButtonData learnButtonData = new LearnButtonData();
//	public static int cKey = 0;
	
	public static int screenWidth ;
	public static int screenHeight;
	public static Context mAppContext;
//	public static HashMap<String, String> keyRemoteTab ;
//	public static ArrayList<KeyValue> keyValueTab  ;
	public static int test_mode = 0;
	public static int totalRemote = 0;
	
	public static AirData airData = new AirData();
	
	public static RemoteDevice remote = new RemoteDevice();
	public static HashMap<String,String > KeyTable = new HashMap<String, String>();
	public static int currentType;
	public static String localLanguage =Locale.getDefault().getLanguage();
	public static boolean powerSupply = false;
	public static boolean vibrate = false;
	public static class DeviceType {
		public final static int TYPE_TV = 0x00;
		public final static int TYPE_STB = 0x02;
		public final static int TYPE_DVD = 0x01;
		public final static int TYPE_FAN = 0x03;
		public final static int TYPE_AIR = 0x05;
		public final static int TYPE_PJT = 0x04;
		public final static int TYPE_LIGHT	= 0X06;
		public final static int TYPE_CAMERA	= 0X07;
		public final static int TYPE_AMP	= 0X08;
	}


	public static class AudioCommand {
		public final static int START_LEARN = 0x57;
		public final static int STOP_LEARN = 0x5E;
		public final static int READ_LEARN = 0x5D;
	}
	
}
