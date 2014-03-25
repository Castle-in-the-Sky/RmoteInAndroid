package com.audiomobile.utils;


import com.audiomobile.data.AirData;
import com.audiomobile.data.Globals;
import com.audiomobile.data.RemoteDevice;
import com.audiomobile.data.Value;

import android.content.Context;
import android.content.SharedPreferences;

public class MyAppInfo {
//	final static String TVINDEX ="tvindex";
//	final static String STBINDEX ="stbindex";
//	final static String DVDINDEX ="dvdindex";
//	final static String FANINDEX ="fanindex";
//	final static String PJTINDEX ="pjtindex";
//	final static String LIGHTINDEX ="lightindex";
//	final static String AIRINDEX ="airindex";
	final static String INITIAL ="initial";
	final static String AUDIO ="audio";
	final static String DB = "REMOTEINDEX";
	
	final static String AIRDATA = "AIRDATA";
	final static String TEMP = "airtemp";
	final static String POWER = "airpower";
	final static String MODE = "airmode";
	final static String WINDC = "airwindcount";
	final static String WINDD = "airwinddirect";
	final static String WINDA = "airauto";
	final static String CODE = "aircode";
	
	public static  void saveInfo(Context _mContext){
		SharedPreferences  sharedPreferences = _mContext.getSharedPreferences(DB,0);  
		SharedPreferences.Editor mEditor = sharedPreferences.edit();  
    
        mEditor.putBoolean(INITIAL,Value.initial); 
        mEditor.putBoolean(AUDIO,Value.audio); 
        mEditor.commit();  
	}
	public static  void saveAirInfo(Context _mContext, AirData ad, int id){
		SharedPreferences  sharedPreferences = _mContext.getSharedPreferences(DB,0);  
		SharedPreferences.Editor mEditor = sharedPreferences.edit();  
	
        mEditor.putInt(id +POWER ,ad.getmPower()); 
        mEditor.putInt(id +TEMP ,ad.getmTmp()); 
        mEditor.putInt(id +MODE,ad.getmMode()); 
        mEditor.putInt(id +WINDC,ad.getmWindCount()); 
        mEditor.putInt(id +WINDD,ad.getmWindDir()); 
        mEditor.putInt(id +WINDA,ad.getmWindAuto()); 
        mEditor.putInt(id +CODE,ad.getCode()); 
        
        mEditor.commit();  
	}
	
	public static  void saveAirCode(Context _mContext, RemoteDevice remote){
		SharedPreferences  sharedPreferences = _mContext.getSharedPreferences(DB,0);  
		SharedPreferences.Editor mEditor = sharedPreferences.edit();  
		int id = remote.getId();
		int code = Integer.parseInt(remote.getCode());

        mEditor.putInt(id +CODE,code); 
        
        mEditor.commit();  
	}
	
	public static void getInfo(Context mContext){
		SharedPreferences  sharedPreferences = mContext.getSharedPreferences(DB,0);  
		
		Value.initial =sharedPreferences.getBoolean(INITIAL, true);
		Value.audio =sharedPreferences.getBoolean(AUDIO, false);

	}
	public static AirData getAirInfo(Context mContext,int id){
		SharedPreferences  sharedPreferences = mContext.getSharedPreferences(DB,0);  
		AirData ad = new AirData();
		ad.setmPower(sharedPreferences.getInt(id +POWER, 1));
		ad.setmTmp(sharedPreferences.getInt(id +TEMP, 25));
		ad.setmMode(sharedPreferences.getInt(id +MODE, 0));
		ad.setmWindCount(sharedPreferences.getInt(id +WINDC, 1));
		ad.setmWindDir(sharedPreferences.getInt(id +WINDD, 1));
		ad.setmWindAuto(sharedPreferences.getInt(id +WINDA, 0));
		ad.setCode(sharedPreferences.getInt(id +CODE, 5000));
		return ad;

	}
	  /**
	 * @param set or get SharedPreferences
	 * @param index data
	 */
	public static void putIntToPreff(String index, int data)
	  {
	    SharedPreferences.Editor localEditor = Value.mAppContext.getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).edit();
	    localEditor.putInt(index, data);
	    localEditor.commit();
	  }

	  public static void putStringToPreff(String index, String data)
	  {
	    SharedPreferences.Editor localEditor =Value.mAppContext.getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).edit();
	    localEditor.putString(index, data);
	    localEditor.commit();
	  }
	  public static int retrieveIntFromPreff(String index)
	  {
	    return Value.mAppContext.getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getInt(index, 0);
	  }

	  public static String retrieveStringFromPreff(String index)
	  {
	    return Value.mAppContext.getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getString(index, "");
	  }

	  public static String retrieveStringFromPreff(String index, String data)
	  {
	    return Value.mAppContext.getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getString(index, data);
	  }
 
}
