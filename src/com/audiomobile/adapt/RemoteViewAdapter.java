package com.audiomobile.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


	public class RemoteViewAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{//FragmentPagerAdapter  
		  
	//  private FragmentManager fm;  
	    private ArrayList<Fragment> fragments = null;  
	
	    private Context context;  
	    
	    
	    private int currentPageIndex = 0; // 当前page索引（切换之前）

		private String TAG  = "RemoteViewAdapter";
	    public RemoteViewAdapter(Context context, FragmentManager fm, ArrayList<Fragment> fragments) {  
	        super(fm);  
	        this.context = context;  
	        this.fragments = fragments;  
	        notifyDataSetChanged();  
	    }  
	      
	    public RemoteViewAdapter(Context context, FragmentManager fm) {  
	        super(fm);  
	        this.context = context;  
	       
	        notifyDataSetChanged();  
//	      this.fragments = fragments;  
	    }  
	  
	    @Override  
	    public Fragment getItem(int arg0) {  
//	      Fragment fragment = new ColourFragment();  
//	      Bundle args = new Bundle();  
//	      args.putInt("title", arg0);  
//	      args.putSerializable("content",hotIssuesList.get(arg0));  
//	      fragment.setArguments(args);  
//	      return fragment;  
	        return fragments.get(arg0);  
	    }  
	  
	    @Override  
	    public int getItemPosition(Object object) {  
	        // TODO Auto-generated method stub  
	        return PagerAdapter.POSITION_NONE;  
	    }  
	      
	    @Override  
	    public int getCount() {  
	        return fragments.size();//hotIssuesList.size();  
	    }  
	    
	     
	   
	    public void onPageSelected(int i) {
	    	 Log.d(TAG  , "onPageSelected is " + i);
	        fragments.get(currentPageIndex).onPause(); // 调用切换前Fargment的onPause()
//	        fragments.get(currentPageIndex).onStop(); // 调用切换前Fargment的onStop()
	        if(fragments.get(i).isAdded()){
//	            fragments.get(i).onStart(); // 调用切换后Fargment的onStart()
	            fragments.get(i).onResume(); // 调用切换后Fargment的onResume()
	        }
	        currentPageIndex = i;
	       
	       
	 
	    } 
	    
	    
	    public int getCurrentPageIndex() {
	        return currentPageIndex;
	    }

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			Log.d(TAG  , "onPageScrolled is " + arg0);
		}
	}  