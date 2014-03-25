package com.audiomobile.main;



import com.audiomobile.remote.R;
import com.audiomobile.data.Globals;
import com.audiomobile.interfacenew.ConfirmDialogInterface;
import com.audiomobile.interfacenew.ConfirmPopupInterface;

import com.audiomobile.utils.Logger;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;

import android.app.Dialog;
import android.content.SharedPreferences;



import android.graphics.Typeface;


import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import android.widget.TextView;

public class BaseActivity extends FragmentActivity implements OnClickListener{
	  Dialog pd;
	  public  LayoutInflater inflater = null;
	  private View gearBig;
	  private View gearSmall;
	  public Handler myHandler = new Handler();
	  public static Typeface tf;
	  public static Typeface tf_bold;
	  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//showLoading();
		 if (tf_bold == null)
		      tf_bold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/font_bold.ttf");
		    if (tf == null)
		      tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/font_regular.ttf");
	//	    showPopupOkCancel("right","information",null,true);
	//	dismissLoading();
	//	    showPopupMessage("new","right",null);
		   
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 public void showLoading()
	  {
	    if (pd == null)
	    {
	      if (inflater == null)
	        inflater = ((LayoutInflater)getSystemService("layout_inflater"));
	      View localView = inflater.inflate(R.layout.popup_loading_layout, null);
	      this.gearSmall = localView.findViewById(R.id.gearSmall);
	      this.gearBig = localView.findViewById(R.id.gearBig);
	      
	      pd = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
	      pd.setContentView(localView);
	    }
	    if (!pd.isShowing())
	    {
	     // Log.d("show loading");
	      pd.show();
	      float f = getResources().getDisplayMetrics().density;
	      RotateAnimation localRotateAnimation1 = new RotateAnimation(0.0F, 360.0F, f * 30.0F, f * 30.0F);
	      localRotateAnimation1.setInterpolator(new LinearInterpolator());
	      localRotateAnimation1.setDuration(1200L);
	      localRotateAnimation1.setRepeatCount(-1);
	      this.gearSmall.startAnimation(localRotateAnimation1);
	      RotateAnimation localRotateAnimation2 = new RotateAnimation(360.0F, 0.0F, f * 50.0F, f * 50.0F);
	      localRotateAnimation2.setInterpolator(new LinearInterpolator());
	      localRotateAnimation2.setDuration(2000L);
	      localRotateAnimation2.setRepeatCount(-1);
	      this.gearBig.startAnimation(localRotateAnimation2);
	    }
	  }
	 
	  public void dismissLoading()
	  {
	    if (pd != null);
	    try
	    {
	      pd.dismiss();
	      return;
	    }
	    catch (Exception localException)
	    {
	    }
	  }
	  
	  public void showPopupMessage(String paramString1, String paramString2, ConfirmPopupInterface paramConfirmPopupInterface)
	  {
	    showPopupMessage(paramString1, paramString2, paramConfirmPopupInterface, getString(R.string.ok));
	  }

	  public void showPopupMessage( final String message, final String title, final ConfirmPopupInterface callback, final String okText)
	  {
		  myHandler.post(new Runnable()
	    {
	      public void run()
	      {
	        if (inflater == null)
	          inflater = ((LayoutInflater)BaseActivity.this.getSystemService("layout_inflater"));
	        View localView = inflater.inflate(R.layout.popup_info_layout, null);
	        ((ImageView)localView.findViewById(R.id.icon)).setImageDrawable(getResources().getDrawable(R.drawable.popup_icon_info));
	        TextView localTextView1 = (TextView)localView.findViewById(R.id.message);
	        localTextView1.setText(message);
	        localTextView1.setTypeface(BaseActivity.tf);
	        TextView localTextView2 = (TextView)localView.findViewById(R.id.title);
	        localTextView2.setText(title);
	        localTextView2.setTypeface(BaseActivity.tf_bold);
	        final Dialog localDialog = new Dialog(BaseActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
	        localDialog.setContentView(localView);
	        localDialog.setCancelable(true);
	        try
	        {
	          localDialog.show();
	          TextView localTextView3 = (TextView)localView.findViewById(R.id.ok);
	          localTextView3.setTypeface(BaseActivity.tf_bold);
	          if (okText != null)
	            localTextView3.setText(okText);
	          localTextView3.setOnClickListener(new View.OnClickListener()
	          {
	            public void onClick(View paramView)
	            {
	              if (callback != null)
	                callback.ok();
	              localDialog.dismiss();
	            }
	          });
//	          if ((this.message.equals(BaseActivity.this.getString(R.string.custom_help))) && (BaseActivity.this.retrieveStringFromPreff(Globals.FAIRY_PREFF_CUSTOM_SHOWAGAIN).equals("true")))
//	          {
//	            LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(R.id.again);
//	            localLinearLayout.setVisibility(0);
//	            ((CheckBox)localLinearLayout.findViewById(R.id.check)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//	            {
//	              public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
//	              {
//	                if (paramCompoundButton.isChecked())
//	                {
//	                  BaseActivity.this.putStringToPreff(Globals.FAIRY_PREFF_CUSTOM_SHOWAGAIN, "false");
//	                  return;
//	                }
//	                BaseActivity.this.putStringToPreff(Globals.FAIRY_PREFF_CUSTOM_SHOWAGAIN, "true");
//	              }
//	            });
//	          }
	          return;
	        }
	        catch (Exception localException)
	        {
	         
	        }
	      }
	    });
	  }
	  
	  /**
	 * @param set or get SharedPreferences
	 * @param index data
	 */
	public void putIntToPreff(String index, int data)
	  {
	    SharedPreferences.Editor localEditor = getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).edit();
	    localEditor.putInt(index, data);
	    localEditor.commit();
	  }

	  public void putStringToPreff(String index, String data)
	  {
	    SharedPreferences.Editor localEditor = getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).edit();
	    localEditor.putString(index, data);
	    localEditor.commit();
	  }
	  public int retrieveIntFromPreff(String index)
	  {
	    return getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getInt(index, 0);
	  }

	  public String retrieveStringFromPreff(String index)
	  {
	    return getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getString(index, "");
	  }

	  public String retrieveStringFromPreff(String index, String data)
	  {
	    return getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getString(index, data);
	  }

	  public void showPopupOkCancel(String paramString1, String paramString2, ConfirmDialogInterface paramConfirmDialogInterface, boolean paramBoolean)
	  {
	    showPopupOkCancel(paramString1, paramString2, paramConfirmDialogInterface, paramBoolean, getString(R.string.ok), getString(R.string.cancel), true);
	  }

	  public void showPopupOkCancel(String paramString1, String paramString2, ConfirmDialogInterface paramConfirmDialogInterface, boolean paramBoolean, String paramString3, String paramString4)
	  {
	    showPopupOkCancel(paramString1, paramString2, paramConfirmDialogInterface, paramBoolean, paramString3, paramString4, true);
	  }

	  public void showPopupOkCancel(final String message, final String title, final ConfirmDialogInterface callback, boolean paramBoolean1, final String okTXT, final String cancelTXT, final boolean cancelable)
	  {
	    Logger.d(message);
	    myHandler.post(new Runnable()
	    {
	      public void run()
	      {
	        if (inflater == null)
	          inflater = ((LayoutInflater)BaseActivity.this.getSystemService("layout_inflater"));
	        View localView = inflater.inflate(R.layout.popup_ok_cancel, null);
	        ((ImageView)localView.findViewById(R.id.icon)).setImageDrawable(getApplicationContext().getResources().getDrawable(  R.drawable.popup_icon_warning));
	        TextView localTextView1 = (TextView)localView.findViewById(R.id.title);
	        localTextView1.setText(message);
	        localTextView1.setTypeface(BaseActivity.tf);
	        TextView localTextView2 = (TextView)localView.findViewById(R.id.message);
	        localTextView2.setText(title);
	        localTextView2.setTypeface(BaseActivity.tf_bold);
	        final Dialog localDialog = new Dialog(BaseActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
	        localDialog.setContentView(localView);
	        try
	        {
	          localDialog.show();
	          TextView localTextView3 = (TextView)localView.findViewById(R.id.ok);
	          localTextView3.setText(okTXT);
	
	          localTextView3.setOnClickListener(new View.OnClickListener( )
	          {
	            public void onClick(View paramView)
	            {
	              if (callback != null)
	                callback.ok();
	              localDialog.dismiss();
	            }
	          });
	          TextView localTextView4 = (TextView)localView.findViewById(R.id.cancel);
	          localTextView4.setTypeface(BaseActivity.tf_bold);
	          localTextView4.setText(cancelTXT);
	          localTextView4.setOnClickListener(new View.OnClickListener()
	          {
	            public void onClick(View paramView)
	            {
	              if (callback != null)
	                callback.cancel();
	              localDialog.dismiss();
	            }
	          });
	          localDialog.setCancelable(cancelable);
	          return;
	        }
	        catch (Exception localException)
	        {
	        
	        }
	      }
	    });
	  }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
//		case R.id.arrowleft:
//			Log.v("right", "arrowleft--->" + R.id.arrowleft);
//			break;
		default:
			break;
		}
	}

}
