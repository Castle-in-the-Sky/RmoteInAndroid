package com.audiomobile.settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.audiomobile.ui.CharacterParser;
import com.audiomobile.ui.ClearEditText;
import com.audiomobile.ui.PinyinComparator;
import com.audiomobile.ui.SideBar;
import com.audiomobile.ui.SortModel;
import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.adapt.SortAdapter;
import com.audiomobile.remote.R;
import com.audiomobile.data.Value;
import com.database.RemoteDB;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BrandListActivity extends Activity {
	private static final String TAG = "ProductsListActivity";
	private ListView brandListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
//	private ClearEditText mClearEditText;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> nameList = new ArrayList<String>();
	private int mType;
	private String mTypeName = null;
	private RemoteDB mRmtDB;
	private TextView deviceType;

	
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brand_list);
		ApplicationContext.getInstance().addSetupsActivity(this);
	//	mType = getIntent().getIntExtra("type", 0);
		mType = Value.remote.getType();
		Log.v(TAG, "mtype is " + mType);
		initData();
		initViews();
	}

	private void initData() {
		mRmtDB = new RemoteDB(getApplicationContext());

	//	mType = Value.currentType;
		mTypeName = Value.RemoteType[mType];
		
	}

	private void initViews() {
	
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
//		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		deviceType = (TextView) findViewById(R.id.brand_type);
		String[] types = getResources().getStringArray(R.array.type_array);
		deviceType.setText(types[mType]);
		
		sideBar.setOnTouchingLetterChangedListener(new com.audiomobile.ui.SideBar.OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					brandListView.setSelection(position);
				}

			}
		});

		brandListView = (ListView) findViewById(R.id.brand_list);
		brandListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SortModel name = (SortModel) adapter.getItem(position);
				
				Value.remote.setBrand(name.getName());
				Intent i = new Intent(BrandListActivity.this,
						RemoteSelection.class);
				i.putExtra("brand",
						((SortModel) adapter.getItem(position)).getName());
				
	//			Log.v(TAG, ((SortModel) adapter.getItem(position)).getName());
				startActivity(i);
				
				
//				Toast.makeText(getApplication(),
//						((SortModel) adapter.getItem(position)).getName(),
//						Toast.LENGTH_SHORT).show();
			}
		});

		getBrand(mTypeName);
		String[] nameListSTr = new String[nameList.size()];
		nameList.toArray(nameListSTr);

		SourceDateList = filledData(nameListSTr);

		
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		brandListView.setAdapter(adapter);
		
		ImageView blBack = (ImageView) findViewById(R.id.bl_back);
		blBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				finish();
			}
		});

//		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
//		getWindow().setSoftInputMode(
//				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//	
//		mClearEditText.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//			
//				filterData(s.toString());
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//			}
//		});
	}

	private ArrayList<String> getBrand(String _type) {

		mRmtDB.open();
		list = mRmtDB.getBrand(_type);
		nameList = mRmtDB.translateBrands(list);
		mRmtDB.close();
		return nameList;
	}

	/**
	 * ΪListView
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String[] date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.length; i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
		
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

	
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
