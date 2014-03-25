package com.audiomobile.utils;


import android.app.Activity;
import android.app.Application;


import android.util.DisplayMetrics;
import android.util.Log;

import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;



public class ApplicationContext extends Application
{
//  public static int BUTTON_WIDTH;
//  public static float DPI;
//  private static int downloadedLastVersion = 0;
  static ApplicationContext instance;
//  private static Runnable notifyWhenVersionDownloaded = null;
  private List<Activity> activities = new ArrayList<Activity>();  
  private List<Activity> setupActivities = new ArrayList<Activity>();  
  static
  {
//    DPI = 1.0F;
//    BUTTON_WIDTH = 70;
  }

  public ApplicationContext()
  {
    instance = this;
  //  downloadLastVersion();
  }

  private void calculateUiLayout()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
 //   DPI = localDisplayMetrics.density;
  }



 
  public static ApplicationContext getInstance()
  {
  if(null == instance)
   {
   instance = new ApplicationContext();
  }
  return instance;
  
  }


  

  public static int getVersionCode()
  {
    try
    {
      int i = getInstance().getPackageManager().getPackageInfo(getInstance().getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

//  public static boolean isCyanogenMod()
//  {
//    return Settings.System.getProperty("os.version").toLowerCase().contains("cyanogenmod");
//  }

  
  
  public void addActivity(Activity activity) {  
      activities.add(activity);  
  }  
  
  public void addSetupsActivity(Activity activity) {  
      setupActivities.add(activity);  
  }  
  
  public void delSetupsActivity(Activity activity) {  
      setupActivities.remove(activity); 
  } 
  
  public void setupTerminate(){
	  for (Activity activity : setupActivities) {  
          activity.finish();  
      }  
  }
  
  @Override  
  public void onTerminate() {  
      super.onTerminate();  
        
      for (Activity activity : activities) {  
          activity.finish();  
      }  
      System.exit(0);
      
        
  //    System.exit(0);  
  }  
  
  
  


//  public static Object stringToObject(String paramString, Class paramClass)
//  {
//    try
//    {
//      Object localObject = ApiConnect.mapper.readValue(paramString, paramClass);
//      return localObject;
//    }
//    catch (Exception localException)
//    {
//      localException.printStackTrace();
//    }
//    return null;
//  }

//  public static int toPx(float paramFloat)
//  {
//    return (int)(paramFloat * DPI);
//  }

  
  public void onCreate()
  {
    super.onCreate();
    Log.v("APP", "application start");
    calculateUiLayout();
  }
}
