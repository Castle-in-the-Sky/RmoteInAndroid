package com.audiomobile.settings;

import java.util.ArrayList;

import java.util.List;

import com.audiomobile.adapt.RemoteAdapter;
import com.audiomobile.remote.R;
import com.audiomobile.data.Globals;
import com.audiomobile.data.RemoteDevice;
import com.audiomobile.data.Value;
import com.audiomobile.interfacenew.ConfirmDialogInterface;
import com.audiomobile.main.BaseActivity;
import com.audiomobile.utils.ApplicationContext;
import com.audiomobile.utils.Logger;
import com.database.UserDB;
//import com.mobeta.android.dslv.DragSortController;
//import com.mobeta.android.dslv.DragSortListView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListRemotes extends BaseActivity {
	DeviceAdapter adapter;
	boolean chooseRemote = false;

	
	ImageView back;

	ListView listRemotes;

	ArrayList<String> remotes = new ArrayList();
	ArrayList<RemoteDevice> remotesList = new ArrayList();
	UserDB mUserDB;
	private void reNamePopup(final int position) {
		if (inflater == null)
			inflater = getLayoutInflater();
		View localView = inflater.inflate(R.layout.popup_info_layout, null);
		((ImageView) localView.findViewById(R.id.icon))
				.setImageDrawable(getResources().getDrawable(
						R.drawable.popup_icon_info));
		TextView localTextView1 = (TextView) localView
				.findViewById(R.id.message);
		localTextView1.setText(getString(R.string.enter_new_remote_name));
		localTextView1.setTypeface(tf);
		TextView localTextView2 = (TextView) localView.findViewById(R.id.title);
		localTextView2.setText(getString(R.string.err_title_info));
		localTextView2.setTypeface(tf_bold);
		final EditText nameEdit = (EditText) localView.findViewById(R.id.edit);
		nameEdit.setVisibility(View.VISIBLE);
		nameEdit.setText(((RemoteDevice) remotesList.get(position)).getName());
		final Dialog dialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.setContentView(localView);
		dialog.show();
		((TextView) localView.findViewById(R.id.ok))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View paramView) {
						if (nameEdit.getText().toString().trim().length() == 0) {
							showPopupMessage(
									getString(R.string.please_enter_remote_name),
									getString(R.string.error), null);
							return;
						}
						((RemoteDevice) remotesList.get(position))
								.setName(nameEdit.getText().toString());
						RemoteDevice remote = remotesList.get(position);
						mUserDB.open();
						// mUserDB.getUserKeyValue();
						mUserDB.updateName(remote);
						mUserDB.close();
						// ListRemotes.writeRemoteOnSdcard((RemoteDevice)ListRemotes.remotesList.get(arg2));
						dialog.dismiss();
						retrieveAllRemotes();
					}
				});
	}

	@SuppressWarnings("unchecked")
	private void retrieveAllRemotes() {
	//	ArrayList<String> test = new ArrayList();
		String name = "";
		mUserDB.open();
		// mUserDB.getUserKeyValue();
		remotesList = mUserDB.getRemoteDevices();
		mUserDB.close();
		adapter.clear();
		remotes.clear();
	//	test.clear();
		for (RemoteDevice rd : remotesList) {
	//		adapter.add(rd.getName());
			if (rd.getName()==null){
				name = "empty";
			}else {
				name = rd.getName();
			}
			remotes.add(name);
		//	Log.v("listremotes", rd.getFullInfo());
		}
	//	remotes.addAll(test);
		
	//	adapter.addAll();   
		adapter.refresh(remotes);
	
	}

	public void confirmDelete(final int paramInt) {
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = ((String) adapter.getItem(paramInt));
		showPopupOkCancel(
				getString(R.string.list_remotes_delete, arrayOfObject),
				getString(R.string.err_title_warning),
				new ConfirmDialogInterface() {
					public boolean cancel() {
						return false;
					}

					public boolean ok() {
						mUserDB.open();
						// mUserDB.getUserKeyValue();
						mUserDB.deleteRemoteDevice(remotesList.get(paramInt));
						mUserDB.close();
						retrieveAllRemotes();
						return false;
					}
				}

				, false);
	}

	public void editRemotePopup(final int position) {
		myHandler.post(new Runnable() {
			public void run() {
				if (inflater == null)
					inflater = getLayoutInflater();
				View localView = inflater.inflate(R.layout.popup_edit_remote,
						null);
				TextView localTextView1 = (TextView) localView
						.findViewById(R.id.title);
				localTextView1.setTypeface(ListRemotes.tf_bold);
				localTextView1.setVisibility(8);
				final Dialog dialog = new Dialog(ListRemotes.this, android.R.style.Theme_Translucent_NoTitleBar);
				dialog.setContentView(localView);
				dialog.show();
				final TextView viewr = (TextView) localView
						.findViewById(R.id.viewr);
				final TextView rename = (TextView) localView
						.findViewById(R.id.rename);
				final TextView edit = (TextView) localView
						.findViewById(R.id.move);
				final TextView delete = (TextView) localView
						.findViewById(R.id.delete);
				delete.setText(R.string.delete);
				edit.setText(R.string.edit);
				rename.setText(R.string.rename);
				viewr.setText(R.string.view_remote);
				rename.setTypeface(ListRemotes.tf_bold);
				edit.setTypeface(ListRemotes.tf_bold);
				delete.setTypeface(ListRemotes.tf_bold);
				viewr.setTypeface(ListRemotes.tf_bold);
				 
				
				OnClickListener viewClickListener = new View.OnClickListener() {
					public void onClick(View paramView) {
						dialog.dismiss();
						if (paramView == viewr) {
							// Intent localIntent1 = new
							// Intent(ListRemotes.this,
							// RemoteActivity.class);

							// startActivity(localIntent1);
							showPopupMessage(remotesList.get(position).getFullInfo(),getResources().getString(R.string.remote_info),null);
						}
						if (paramView == delete) {
							confirmDelete(position);
						}
						if (paramView == edit) {
							// ListRemotes.putStringToPreff(Globals.FAIRY_PREFF_LASTUSED,
							// ((RemoteDevice)ListRemotes.remotesList.get(position)).getId());
							// ListRemotes.putStringToPreff(Globals.FAIRY_PREFF_LASTUSED_PATH,
							// ((RemoteDevice)ListRemotes.remotesList.get(position)).getPathName());
							// Intent localIntent2 = new
							// Intent(ListRemotes.this,
							// RemoteActivity.class);
							//
							// localIntent2.putExtra("edit", true);
							// startActivity(localIntent2);
							Intent i = new Intent(ListRemotes.this, SelectDeviceType.class);
							Value.remote = remotesList.get(position);
							i.putExtra("type", remotesList.get(position).getType());
							startActivity(i);
						}
						if (paramView == rename) {
							reNamePopup(position);
						}
					}
					};
					delete.setOnClickListener(viewClickListener);
					viewr.setOnClickListener(viewClickListener);
					edit.setOnClickListener(viewClickListener);
					rename.setOnClickListener(viewClickListener);
			}
		});
	}

	public int isVisible(int paramInt) {
		if (paramInt >= remotesList.size())
			return 8;
		if (retrieveStringFromPreff(
				"remote_visible_"
						+ ((RemoteDevice) remotesList.get(paramInt)).getId(),
				"true").equals("true"))
			return 0;
		return 4;
	}

	protected void onActivityResult(int paramInt1, int paramInt2,
			Intent paramIntent) {
		super.onActivityResult(paramInt1, paramInt2, paramIntent);
		if (paramInt2 != 0) {
			// if (paramInt2 == Globals.RESULT_SELECT_FCT)
			// {
			// RemoteFunction localRemoteFunction2 =
			// (RemoteFunction)paramIntent.getSerializableExtra("functionResult");
			// if (getIntent().getIntExtra("x", -1) != -1)
			// {
			// localRemoteFunction2.setX(getIntent().getIntExtra("x", 0));
			// localRemoteFunction2.setY(getIntent().getIntExtra("y", 0));
			// }
			// Logger.d("on result received from fct " +
			// localRemoteFunction2.getFunction() + "and sent " + fct);
			// Intent localIntent3 = new Intent();
			// localIntent3.putExtra("function", localRemoteFunction2);
			// localIntent3.putExtra("functionSent", fct);
			// setResult(Globals.RESULT_REMOTE, localIntent3);
			// finish();
			// }
			// if (paramInt2 == Globals.RESULT_SELECT_MACRO)
			// {
			// Macro localMacro =
			// (Macro)paramIntent.getSerializableExtra("macroResult");
			// if (getIntent().getIntExtra("x", -1) != -1)
			// {
			// localMacro.setX(getIntent().getIntExtra("x", 0));
			// localMacro.setY(getIntent().getIntExtra("y", 0));
			// }
			// Intent localIntent2 = new Intent();
			// localIntent2.putExtra("function", localMacro);
			// localIntent2.putExtra("functionSent", fct);
			// setResult(Globals.RESULT_REMOTE, localIntent2);
			// finish();
			// }
			// if (paramInt2 == Globals.RESULT_SELECT_CUSTOM_FCT)
			// {
			// RemoteFunction localRemoteFunction1 =
			// (RemoteFunction)paramIntent.getSerializableExtra("functionResult");
			// if (getIntent().getIntExtra("x", -1) != -1)
			// {
			// localRemoteFunction1.setX(getIntent().getIntExtra("x", 0));
			// localRemoteFunction1.setY(getIntent().getIntExtra("y", 0));
			// }
			// Logger.d("on result received from fct " +
			// localRemoteFunction1.getFunction() + "and sent " + fct);
			// Intent localIntent1 = new Intent();
			// localIntent1.putExtra("function", localRemoteFunction1);
			// localIntent1.putExtra("functionSent", fct);
			// setResult(Globals.RESULT_REMOTE, localIntent1);
			// finish();
			// }
		}
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.list_remotes_screen);
		ApplicationContext.getInstance().addSetupsActivity(this);
		//	mType = getIntent().getIntExtra("type", 0);
			
		putStringToPreff(Globals.ADD_DEVICE, "false");
		listRemotes = ((ListView) findViewById(R.id.listRemotes));
	
		back = ((ImageView) findViewById(R.id.back));

		adapter = new DeviceAdapter(this, remotes, -1);

//		if (getIntent().getStringExtra("chooseRemote") != null) {
//		
//			chooseRemote = true;
//		}
		mUserDB = new UserDB(getApplicationContext());
	//	Logger.d("function chosen " + fct);
		retrieveAllRemotes();
	//	Logger.d("id NOT in list remotes " + idNOT);

		listRemotes.setAdapter(adapter);
		listRemotes
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> paramAdapterView,
							View paramView, int position, long paramLong) {
						// if (chooseRemote) {
						// // if ((fct.equals("all_numbers"))
						// // || (fct.equals("colors"))) {
						// // Logger.d("pressed ID "
						// // + ((RemoteDevice) remotesList
						// // .get(paramInt)).getId());
						// // Intent localIntent1 = new Intent();
						// // localIntent1.putExtra("remoteID",
						// // ((RemoteDevice) remotesList
						// // .get(paramInt)).getId());
						// // localIntent1.putExtra("functionSent", fct);
						// // setResult(Globals.RESULT_REMOTE, localIntent1);
						// // finish();
						// return;
						// }
						// Intent localIntent2 = new
						// Intent(ListRemotes.this, ListFunctions.class);
						// localIntent2.putExtra("remoteID",
						// ((RemoteDevice).remotesList.get(paramInt)).getId());
						// localIntent2.putExtra("function",
						// ListRemotes.fct);
						// if (ListRemotes.macro)
						// localIntent2.putExtra("macro", "macro");
						// ListRemotes.startActivityForResult(localIntent2,
						// Globals.RESULT_SELECT_FCT);
						// return;
						// }
						RemoteDevice localRemoteDevice = (RemoteDevice) remotesList
								.get(position);
						// ListRemotes.putStringToPreff(Globals.FAIRY_PREFF_LASTUSED,
						// localRemoteDevice.getId());
						// ListRemotes.putStringToPreff(Globals.FAIRY_PREFF_LASTUSED_PATH,
						// localRemoteDevice.getPathName());
						// if (localRemoteDevice.getStatus() == 9)
						// {
						// showPopupOkCancel(getString(2131296475),
						// getString(2131296378), new ConfirmDialogInterface()
						// {
						// public boolean cancel()
						// {
						// ListRemotes.editRemotePopup(arg2);
						// return false;
						// }
						//
						// public boolean ok()
						// {
						// Intent localIntent = new Intent(ListRemotes.this,
						// RecordActivity.class);
						// localIntent.putExtra("record", true);
						// ListRemotes.startActivity(localIntent);
						// return false;
						// }
						// }
						// , false, ListRemotes.getString(2131296476),
						// ListRemotes.getString(2131296477));
						// return;
						// }
						editRemotePopup(position);
					}
				});
		
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {

				finish();
			}
		});

		listRemotes
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					public boolean onItemLongClick(
							AdapterView<?> paramAdapterView, View paramView,
							int paramInt, long paramLong) {
						if (!chooseRemote)
							editRemotePopup(paramInt);
						return false;
					}
				});
//		DragSortController localDragSortController = new DragSortController(
//				listRemotes);
//		localDragSortController.setDragHandleId(R.id.handler);
//		localDragSortController.setRemoveEnabled(false);
//		localDragSortController.setSortEnabled(true);
//		localDragSortController.setDragInitMode(0);
//		listRemotes.setFloatViewManager(localDragSortController);
//		listRemotes.setOnTouchListener(localDragSortController);
//		listRemotes.setDragEnabled(false);
//		listRemotes.setDropListener(new DragSortListView.DropListener() {
//			public void drop(int paramInt1, int paramInt2) {
//				if (paramInt1 != paramInt2) {
//					String str = (String) adapter.getItem(paramInt1);
//					adapter.remove(str);
//					adapter.insert(str, paramInt2);
//					RemoteDevice localRemoteDevice = (RemoteDevice) remotesList
//							.get(paramInt1);
//					remotesList.remove(paramInt1);
//					remotesList.add(paramInt2, localRemoteDevice);
//				}
//				for (int i = 0;i<remotesList.size(); i++) {
//				
//					// ((RemoteDevice)remotesList.get(i)).setSortPosition(i);
//					// ListRemotes.writeRemoteOnSdcard((RemoteDevice)remotesList.get(i));
//				}
//			}
//		});
//		adapter.setMyRemotes(true);
		
		return;

	}

	protected void onResume() {
		super.onResume();
	}

	class DeviceAdapter extends ArrayAdapter {

		private final BaseActivity activity;
		int deviceORType;
		private List<String> devices;
		private boolean myRemotes = false;

		public DeviceAdapter(Context mContext, List<String> mDevices,
				int localList) {
			super(mContext, localList);
			activity = (BaseActivity) mContext;
			devices = mDevices;
			deviceORType = localList;
		}

//		public void addAll(Collection paramCollection) {
//			Iterator localIterator = paramCollection.iterator();
//			while (true) {
//				if (!localIterator.hasNext())
//					add(localIterator.next());
//				return;
//
//			}
//		}
		public void refresh(ArrayList<String> list) {  
			devices = list;  
	       
//	        for (String t :list){
//	    	Log.v("listremotes", t);
//	    		}
	        notifyDataSetChanged();  
	    }  
		public int getCount() {
			return devices.size();
		}

		public Object getItem(int paramInt) {
			return devices.get(paramInt);
		}

		public View getView(final int position, View paramView,
				ViewGroup paramViewGroup) {
			
			View localView = paramView;
			DeviceView localDeviceView = null;
			ListRemotes localListRemotes;
	//		Log.v("listremotes", "paramView  ");
			int i = 0;
			if (localView == null) {
				//Log.v("listremotes", "localview is ok");
				localView = activity.getLayoutInflater().inflate(
						R.layout.remote_row, null);
			}
				localDeviceView = new DeviceView();
				
				localDeviceView.title = ((TextView) localView
						.findViewById(R.id.title));
//				localDeviceView.eye = ((ImageView) localView
//						.findViewById(R.id.eye));
				localView.setTag(localDeviceView);
				
				if ((position != 0)) {

					localView.setBackgroundColor(Color.rgb(10, 10, 10));
					localDeviceView.title.setTypeface(BaseActivity.tf_bold);
					localDeviceView.title.setTextSize(2, 18.0F);
					if ((devices.size() <= 0)
							|| (position != -1 + devices.size())
							|| (!((String) devices.get(position)).toLowerCase()
									.trim().contains("other"))) {
						localDeviceView.title.setTextColor(-1);
					} else {

						localDeviceView.title.setTextColor(Color
								.parseColor("#848789"));
					}
					if ((isMyRemotes()) && ((activity instanceof ListRemotes))) {
						localListRemotes = (ListRemotes) activity;
						}
//						i = localListRemotes.isVisible(position);
//						if (i != 8) {
//
//							localDeviceView.eye.setVisibility(i);
//						} else {
//							localDeviceView.eye.setVisibility(0);
//						}
//						localDeviceView.eye.setVisibility(View.GONE);
//						if (i == 0)
//							localDeviceView.eye
//									.setImageResource(R.drawable.eye_visible);
//						if (i == 4)
//							localDeviceView.eye
//									.setImageResource(R.drawable.eye_invisible);
					
				}

//				localDeviceView.eye
//						.setOnClickListener(new View.OnClickListener() {
//							public void onClick(View paramView) {
//								toggleVisibility(position);
//							}
//						});

				
//			if ((position == 0) || (position == 1)) {
//				localView.setBackgroundColor(Color.rgb(10, 10, 10));
//				localDeviceView.title.setTypeface(BaseActivity.tf_bold);
//				localDeviceView.title.setTextSize(2, 18.0F);
//
//			} else {
			String str = (String) devices.get(position);
			localDeviceView.title.setText(str.toUpperCase());
			localDeviceView = (DeviceView) localView.getTag();
			localView.setBackgroundColor(0);
			localDeviceView.title.setTypeface(BaseActivity.tf);
			localDeviceView.title.setTextSize(2, 16.0F);	
//		}

			return localView;
		}

		public void toggleVisibility(int paramInt) {
			if (retrieveStringFromPreff("remote_visible_"
							+ ((RemoteDevice) remotesList.get(paramInt))
									.getId(),
					"true").equals("true")){
				putStringToPreff("remote_visible_"
						+ ((RemoteDevice) remotesList.get(paramInt)).getId(),
						"false");

			}else {
			putStringToPreff(
					"remote_visible_"
							+ ((RemoteDevice) remotesList.get(paramInt))
									.getId(),
					"true");
			}
			
		}
		
		

		public boolean isMyRemotes() {
			return myRemotes;
		}

		public void setMyRemotes(boolean paramBoolean) {
			myRemotes = paramBoolean;
		}

		protected class DeviceView {
			protected ImageView eye;
			protected TextView title;

			protected DeviceView() {
			}
		}
	}
}
