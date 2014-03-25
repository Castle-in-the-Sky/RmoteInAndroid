package com.audiomobile.utils;

import android.util.Log;

public class Logger
{
  private static String TAG;
  static boolean isLogOn = false;

  static
  {
    TAG = "# FAIRY #";
  }

  public static void d(String paramString)
  {
    if (isLogOn)
      Log.d(TAG, paramString);
  }

  public static void d(String paramString1, String paramString2)
  {
    if (isLogOn)
      Log.d(paramString1, paramString2);
  }

  public static void e(String paramString)
  {
    if (isLogOn)
      Log.e(TAG, paramString);
  }

  public static void e(String paramString1, String paramString2)
  {
    if (isLogOn)
      Log.e(paramString1, paramString2);
  }
}

