package com.audiomobile.model;

import java.util.ArrayList;
import java.util.List;

import com.audiomobile.adapt.RemoteViewPagerAdapter.OnExtraPageChangeListener;
import com.audiomobile.data.Value;
import com.audiomobile.main.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener  {

	private String TAG = "MyOnPageChangeListener";
	  private List<Fragment> remotes; // 每个Fragment对应一个Page
	    private FragmentManager fragmentManager;
	    private ViewPager remotePager; // viewPager对象
	    private int currentPageIndex = 0; // 当前page索引（切换之前）
	    private OnExtraPageChangeListener onExtraPageChangeListener; // ViewPager切换页面时的额外功能添加接口
	
	    public MyOnPageChangeListener(Main mainActivity,
			ViewPager remotePager, ArrayList<Fragment> remotes) {
		this.remotePager = remotePager;
		this.remotes = remotes;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPageScrollStateChanged---->" + arg0);
		 if(null != onExtraPageChangeListener){ // 如果设置了额外功能接口
	            onExtraPageChangeListener.onExtraPageScrollStateChanged(arg0);
	        }
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	//	Log.d(TAG, "onPageScrolled---->" + arg0 +"onPageScrolled---->" + arg1+"onPageScrolled---->" + arg2);
		  if(null != onExtraPageChangeListener){ // 如果设置了额外功能接口
	            onExtraPageChangeListener.onExtraPageScrolled(arg0,arg1,arg2);
	        }
	
	}

	@Override
	public void onPageSelected(int i) {
		// TODO Auto-generated method stub
		Value.currentDevice = i;
		
		Log.d(TAG, "onPageSelected---->" + currentPageIndex );
		remotes.get(currentPageIndex).onPause(); // 调用切换前Fargment的onPause()
//	        fragments.get(currentPageIndex).onStop(); // 调用切换前Fargment的onStop()
	        if(remotes.get(i).isAdded()){
//	            fragments.get(i).onStart(); // 调用切换后Fargment的onStart()
	        	remotes.get(i).onResume(); // 调用切换后Fargment的onResume()
	        }
	        currentPageIndex = i;
	        if(null != onExtraPageChangeListener){ // 如果设置了额外功能接口
	            onExtraPageChangeListener.onExtraPageSelected(i);
	        }
	}
	 /**
     * 设置页面切换额外功能监听器
     * @param onExtraPageChangeListener
     */
    public void setOnExtraPageChangeListener(OnExtraPageChangeListener onExtraPageChangeListener) {
        this.onExtraPageChangeListener = onExtraPageChangeListener;
    }
    public OnExtraPageChangeListener getOnExtraPageChangeListener() {
        return onExtraPageChangeListener;
    }
	
	/**
     * page切换额外功能接口
     */
    public static class OnExtraPageChangeListener{
        public void onExtraPageScrolled(int i, float v, int i2){}
        public void onExtraPageSelected(int i){}
        public void onExtraPageScrollStateChanged(int i){}
    }

}
