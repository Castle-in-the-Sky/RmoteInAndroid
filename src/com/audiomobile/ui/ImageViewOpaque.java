package com.audiomobile.ui;

import com.audiomobile.model.OnStartPressingListener;


import android.R;
import android.content.Context;
import android.graphics.Bitmap;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;


public class ImageViewOpaque extends ImageView
{
  private Bitmap bmpCache = null;
  View.OnClickListener clickListener;
  private boolean isPressed = false;
  private boolean isPressing = false;
  View.OnLongClickListener longClickListener;
  final static String TAG = "ImageViewOpaque";
  Runnable longClickRunnable = new Runnable()
  {
    public void run()
    {
      if (longClickListener != null)
      {
        longClickListener.onLongClick(ImageViewOpaque.this);
        MotionEvent event = MotionEvent.obtain(0L, System.currentTimeMillis(), 3, 0.0F, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
        dispatchTouchEvent(event);
      }
    }
  };
  OnStartPressingListener pressListener;

  public ImageViewOpaque(Context paramContext)
  {
    super(paramContext);
  }

  public ImageViewOpaque(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ImageViewOpaque(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }


//private Bitmap getBitmap()
//  {
//    if (this.bmpCache != null){
//    	Logger.d("bmpCache is valid");
//      return this.bmpCache;
//    }else {
//    	Logger.d("bmpCache is null");
//    }
//    StateListDrawable localStateListDrawable = (StateListDrawable)getDrawable();
//   
//    if (localStateListDrawable == null){
//    	Logger.d("localStateListDrawable is null");
//      return null;
//    }else {
//    	Logger.d("localStateListDrawable is " + localStateListDrawable.getLevel());
//    }
//    Bitmap localBitmap1 = ((BitmapDrawable)localStateListDrawable.getCurrent()).getBitmap();
//    Bitmap localBitmap2 = null;
//    if ((getWidth() != localBitmap1.getWidth()) || (getHeight() != localBitmap1.getHeight()))
//    {
//    	Logger.d("getWidth()" + getWidth() +"getHeight()" + getHeight());
//      this.bmpCache = Bitmap.createScaledBitmap(localBitmap1, getWidth(), getHeight(), false);
//      localBitmap2 = this.bmpCache.copy(Bitmap.Config.ARGB_4444, false);
//      this.bmpCache.recycle();
//    }
//    for (this.bmpCache = localBitmap2; ; this.bmpCache = localBitmap1)
//      return this.bmpCache;
//  }
  
  
  private Bitmap getBitmap()
  {
    StateListDrawable localStateListDrawable = (StateListDrawable)getDrawable();
    if (localStateListDrawable == null)
      return null;
    Drawable d=localStateListDrawable.getCurrent();
    BitmapDrawable bd = (BitmapDrawable) d;
    Bitmap bm = bd.getBitmap();
       return bm;
  }
  
  
  
//  private Bitmap getBitmap()
//  {
//    if (bmpCache != null)
//      return bmpCache;
//    StateListDrawable localStateListDrawable = (StateListDrawable)getDrawable();
//    if (localStateListDrawable == null)
//      return null;
//    Bitmap localBitmap1 = ((BitmapDrawable)localStateListDrawable.getCurrent()).getBitmap();
//    Bitmap localBitmap2 = null;
//    if ((getWidth() != localBitmap1.getWidth()) || (getHeight() != localBitmap1.getHeight()))
//    {
//      bmpCache = Bitmap.createScaledBitmap(localBitmap1, getWidth(), getHeight(), false);
//      localBitmap2 = bmpCache.copy(Bitmap.Config.ARGB_4444, false);
//      bmpCache.recycle();
//    }
//    for (bmpCache = localBitmap2; ; this.bmpCache = localBitmap1)
//      return this.bmpCache;
//  }

//  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
//  {
//	  
//	  Logger.d("paramMotionEvent is " + paramMotionEvent.getAction());
//    if (paramMotionEvent.getAction() == 0)
//    {
//    	
//      Bitmap localBitmap2 = getBitmap();
//      if (((int)paramMotionEvent.getX() > 0) && ((int)paramMotionEvent.getX() < localBitmap2.getWidth()) && ((int)paramMotionEvent.getY() > 0) && ((int)paramMotionEvent.getY() < localBitmap2.getWidth()) && (Color.alpha(localBitmap2.getPixel((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) > 50))
//      {
//        StateListDrawable localStateListDrawable3 = (StateListDrawable)getDrawable();
//        isPressed = true;
//        if (this.pressListener != null)
//          this.pressListener.startPressing(this);
//        localStateListDrawable3.setState(new int[] { android.R.attr.state_pressed, android.R.attr.state_focused });
//        postDelayed(this.longClickRunnable, 1300L);
//        isPressing = true;
//      }
//    }
//    while (true)
//    {
//    
//      StateListDrawable localStateListDrawable2 = (StateListDrawable)getDrawable();
//      this.isPressed = false;
//      localStateListDrawable2.setState(new int[] { android.R.attr.state_enabled, android.R.attr.state_enabled });
//      removeCallbacks(this.longClickRunnable);
//      if (this.clickListener == null)
//        return super.onTouchEvent(paramMotionEvent);
//      Bitmap localBitmap1 = getBitmap();
//      if (((int)paramMotionEvent.getX() <= 0) || ((int)paramMotionEvent.getX() >= localBitmap1.getWidth()) || ((int)paramMotionEvent.getY() <= 0) || ((int)paramMotionEvent.getY() >= localBitmap1.getWidth()) || (Color.alpha(localBitmap1.getPixel((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) <= 50))
//        break;
//      if (!this.isPressing)
//        continue;
//      this.clickListener.onClick(this);
//      performHapticFeedback(1);
//      return true;
//    }
//    isPressing = false;
//    if (paramMotionEvent.getAction() != 1){
//      
//    if ((paramMotionEvent.getAction() == 4) || (paramMotionEvent.getAction() == 3))
//    {
//      StateListDrawable localStateListDrawable1 = (StateListDrawable)getDrawable();
//      isPressed = false;
//      localStateListDrawable1.setState(new int[] { android.R.attr.state_enabled, android.R.attr.state_enabled });
//      removeCallbacks(this.longClickRunnable);
//      isPressing = false;
//    }
//    }
//    return super.dispatchTouchEvent(paramMotionEvent);
//  }

  public boolean dispatchTouchEvent(MotionEvent event)
  {
	  
	//  Log.d(TAG, event.toString());
    if (event.getAction() == MotionEvent.ACTION_DOWN)
    {
      Bitmap localBitmap2 = getBitmap();
      if (localBitmap2==null){
    //	  Log.v("test", "localBitmap2 is null");
      }
     
      if (((int)event.getX() > 0) && ((int)event.getX() < localBitmap2.getWidth()) && ((int)event.getY() > 0) && ((int)event.getY() < localBitmap2.getWidth()) && (Color.alpha(localBitmap2.getPixel((int)event.getX(), (int)event.getY())) > 50))
      {
    //	  Log.v(TAG,"paramMotionEvent is " + paramMotionEvent.toString());
        StateListDrawable localStateListDrawable3 = (StateListDrawable)getDrawable();
        isPressed = true;
        if (pressListener != null){
          pressListener.startPressing(this);
        }
        localStateListDrawable3.setState(new int[] { R.attr.state_pressed, R.attr.state_focused });
        postDelayed(longClickRunnable, 1300L);
        isPressing = true;
        
        return true;
      }
    }
    while (true)
    {
    
      if (event.getAction() != MotionEvent.ACTION_UP){
    	  
    //	  Log.d(TAG, "not up");
    	  if ((event.getAction() == MotionEvent.ACTION_OUTSIDE) || (event.getAction() == MotionEvent.ACTION_CANCEL))
    	    {
    //		  Log.d(TAG, "cancel outside");
    	      StateListDrawable localStateListDrawable1 = (StateListDrawable)getDrawable();
    	      this.isPressed = false;
    	      localStateListDrawable1.setState(new int[] { R.attr.state_enabled, R.attr.state_enabled });
    	      removeCallbacks(this.longClickRunnable);
    	      this.isPressing = false;
    	    }
    	  Bitmap localBitmap1 = getBitmap();
          if (((int)event.getX() <= 0) || ((int)event.getX() >= localBitmap1.getWidth()) || ((int)event.getY() <= 0) || ((int)event.getY() >= localBitmap1.getWidth()) || (Color.alpha(localBitmap1.getPixel((int)event.getX(), (int)event.getY())) <= 50))
            {
            StateListDrawable localStateListDrawable2 = (StateListDrawable)getDrawable();
            localStateListDrawable2.setState(new int[] { R.attr.state_enabled, R.attr.state_enabled });
              this.isPressed = false;   
         //     Log.d(TAG, "out of the image");
        	  break;
            }
      }else {
    //	  Log.d(TAG, "up");
      StateListDrawable localStateListDrawable2 = (StateListDrawable)getDrawable();
      this.isPressed = false;
      removeCallbacks(this.longClickRunnable);
      localStateListDrawable2.setState(new int[] { R.attr.state_enabled, R.attr.state_enabled });
      }
      
      if (clickListener == null){
    	  removeCallbacks(this.longClickRunnable);
    //	  Log.v(TAG, "this clicklistener is null");
        return super.onTouchEvent(event);
      }
     
      if (!isPressing)
        continue;
     // Log.d(TAG, "continue");
      clickListener.onClick(this);
      performHapticFeedback(HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING);
      return true;
    }
    isPressing = false;
 //   Log.d(TAG, "quit ");
    return super.dispatchTouchEvent(event);
  }

//  public boolean dispatchTouchEvent(MotionEvent event)
// {
//		Log.d(TAG, event.toString());
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			Bitmap localBitmap2 = getBitmap();
//			if (((int) event.getX() > 0)
//					&& ((int) event.getX() < localBitmap2.getWidth())
//					&& ((int) event.getY() > 0)
//					&& ((int) event.getY() < localBitmap2.getWidth())
//					&& (Color.alpha(localBitmap2.getPixel((int) event.getX(),
//							(int) event.getY())) > 50)) {
//				StateListDrawable localStateListDrawable3 = (StateListDrawable) getDrawable();
//				isPressed = true;
//				if (pressListener != null)
//					pressListener.startPressing(this);
//				localStateListDrawable3.setState(new int[] {
//						R.attr.state_pressed, R.attr.state_focused });
//				postDelayed(longClickRunnable, 1300L);
//				isPressing = true;
//			}
//			return true;
//		}
//		while (true) {
//
//			if (event.getAction() != MotionEvent.ACTION_UP) {
//				isPressing = false;
//				if ((event.getAction() == MotionEvent.ACTION_OUTSIDE)
//						|| (event.getAction() == MotionEvent.ACTION_CANCEL)) {
//					StateListDrawable localStateListDrawable1 = (StateListDrawable) getDrawable();
//					isPressed = false;
//					localStateListDrawable1.setState(new int[] {
//							R.attr.state_enabled, R.attr.state_enabled });
//					removeCallbacks(longClickRunnable);
//					isPressing = false;
//				} else {
//					StateListDrawable localStateListDrawable2 = (StateListDrawable) getDrawable();
//					isPressed = false;
//					localStateListDrawable2.setState(new int[] {
//							R.attr.state_enabled, R.attr.state_enabled });
//					removeCallbacks(this.longClickRunnable);
//					if (clickListener == null)
//						return super.onTouchEvent(event);
//					Bitmap localBitmap1 = getBitmap();
//					if (((int) event.getX() <= 0)
//							|| ((int) event.getX() >= localBitmap1.getWidth())
//							|| ((int) event.getY() <= 0)
//							|| ((int) event.getY() >= localBitmap1.getWidth())
//							|| (Color.alpha(localBitmap1.getPixel(
//									(int) event.getX(), (int) event.getY())) <= 50))
//						break;
//					if (!this.isPressing)
//						continue;
//					clickListener.onClick(this);
//					performHapticFeedback(1);
//					return true;
//
//				}
//
//			}
//		}
//		return super.dispatchTouchEvent(event);
//	}

  
  
  public boolean isPressed()
  {
    return isPressed;
  }

//  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
//  {
//    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
//  }

//  @Override
//public boolean dispatchTouchEvent(MotionEvent event) {
//	// TODO Auto-generated method stub
//	  Log.v("imageviewopaque","paramMotionEvent is " + event.toString());
//	  if (event.getAction() == 0)
//	    {
//	    	
//	      Bitmap localBitmap2 = getBitmap();
//	      if (((int)event.getX() > 0) && ((int)event.getX() < localBitmap2.getWidth()) && ((int)event.getY() > 0) && ((int)event.getY() < localBitmap2.getWidth()) && (Color.alpha(localBitmap2.getPixel((int)event.getX(), (int)event.getY())) > 50))
//	      {
//	        StateListDrawable localStateListDrawable3 = (StateListDrawable)getDrawable();
//	        isPressed = true;
//	        if (this.pressListener != null)
//	          this.pressListener.startPressing(this);
//	        localStateListDrawable3.setState(new int[] { android.R.attr.state_pressed, android.R.attr.state_focused });
//	       return true;
//
//	      
//	      }else {
//	    	  StateListDrawable localStateListDrawable1 = (StateListDrawable)getDrawable();
//  	      isPressed = false;
//  	      localStateListDrawable1.setState(new int[] { android.R.attr.state_enabled, android.R.attr.state_enabled });
//  	//      removeCallbacks(this.longClickRunnable);
//  	      isPressing = false; 
//	      }
//	    }
//	return super.dispatchTouchEvent(event);
//}

@Override
protected void onLayout(boolean changed, int left, int top, int right,
		int bottom) {
	// TODO Auto-generated method stub
	super.onLayout(changed, left, top, right, bottom);
}

public void setOnClickListener(OnClickListener paramOnClickListener)
  {
    this.clickListener = paramOnClickListener;
  }

  public void setOnLongClickListener(OnLongClickListener paramOnLongClickListener)
  {
    this.longClickListener = paramOnLongClickListener;
  }

  public void setOnStartPressingListener(OnStartPressingListener paramOnStartPressingListener)
  {
    this.pressListener = paramOnStartPressingListener;
  }


  
  
}

