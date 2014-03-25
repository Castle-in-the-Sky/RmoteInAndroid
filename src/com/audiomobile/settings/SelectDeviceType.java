package com.audiomobile.settings;

import com.audiomobile.adapt.DeviceAdapter;

import com.audiomobile.data.Globals;
import com.audiomobile.data.Value;

import com.audiomobile.main.BaseActivity;
import com.audiomobile.utils.ApplicationContext;

import com.audiomobile.remote.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import android.widget.ListView;


public class SelectDeviceType extends BaseActivity implements OnClickListener, OnItemClickListener{
	
	private ListView listView;
	private ImageView dtlBack;


	private int mType;
	private SelectDeviceType mContext;
	private DeviceAdapter typeAdapter;

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.device_type_list);
	        
	        dtlBack= (ImageView)findViewById(R.id.dtl_back);
	        dtlBack.setOnClickListener(this);
	        ApplicationContext.getInstance().addSetupsActivity(this);
//	        btn_ok = (ImageButton)findViewById(R.id.rdl_ok);
//	        btn_ok.setMinimumWidth((Value.screenWidth) / 6);
//	        btn_ok.setMinimumHeight((Value.screenHeight) / 10);
//	        btn_ok.setOnClickListener(this);
	
//	        mType = getIntent().getIntExtra("type", 0);
	        if ("true".equals(retrieveStringFromPreff(Globals.ADD_DEVICE))){
	        	mType = Value.DeviceType.TYPE_TV;
	        }else{
	        	mType = Value.remote.getType();
	        }
			listView=(ListView)findViewById(R.id.listView_type); 
			listView.setOnItemClickListener(this);
		
			String[]	typeDevices = getResources().getStringArray(R.array.type_array);
//			int[] typeChecks = {R.drawable.check_off,R.drawable.check_off,R.drawable.check_off,
//					R.drawable.check_off,R.drawable.check_off,R.drawable.check_off,R.drawable.check_off,R.drawable.check_off,R.drawable.check_off		
//			};
			     
//	            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,typeDevices);
//	             listView.setAdapter(adapter);
			
			
			mContext = this;
			typeAdapter = new DeviceAdapter(mContext,typeDevices);
			listView.setAdapter(typeAdapter);
			typeAdapter.changeSelected(mType);
			
	    }
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.dtl_back:
				finish();
	    		break;
			
	    	default:
	    		break;
			}
		}
		@Override
		public void onItemClick(AdapterView<?> av, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
				
			typeAdapter.changeSelected(position);
			
			Intent i = new Intent(SelectDeviceType.this, BrandListActivity.class);
			Value.remote.setType(position);
			i.putExtra("type", position);
			startActivity(i);
			
		}
}
