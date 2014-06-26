package com.sark.shifter;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
@SuppressLint({ "WorldReadableFiles", "WorldWriteableFiles" })
@SuppressWarnings("deprecation")
public class MainActivity extends ListActivity {
	private PendingIntent mAlarmSender;
	private final int GROUP_1 = 1;
	private final int GROUP_2 = 2;
	private final int start_service = 1;
	private final int stop_service = 2;
	private int itemno = 0;
	public static int flag = 0;
	static public int flag2;
	public DiaryAdapter myAdapter;
	int no_of_rows;
	int first_start=0;
	String stmin[] = new String[2];
	String etmin[] = new String[2];
	SharedPreferences pre,prefs,prefs3;
	String data = "mydatastorage";
	Editor medit;
	MyDB dba;
	public class ViewHolder {
		MyDiary mdiary;
		TextView mname_of_profile;
		TextView mstartinghour;
		TextView mstartingmin;
		TextView mendinghour;
		TextView mendingmin;
		TextView mtype_of_profile;
		TextView mmon;
		TextView AM_PM_Startinghour;
		TextView AM_PM_Endinghour;
	}
		private class MyDiary {
		public String name_of_profile;
		public int startinghour;
		public int startingmin;
		public int endinghour;
		public int endingmin;
		public int type_of_profile;
		public int mon;
		public int tues;
		public int wed;
		public int thurs;
		public int fri;
		public int sat;
		public int sun;
		public MyDiary(String n, int sh, int sm, int eh, int em, int top,
				int mon, int tues, int wed, int thur, int fri, int sat, int sun) {
			name_of_profile = n;
			startinghour = sh;
			startingmin = sm;
			endinghour = eh;
			endingmin = em;
			type_of_profile = top;
			this.mon = mon;
			this.tues = tues;
			this.wed = wed;
			this.thurs = thur;
			this.fri = fri;
			this.sat = sat;
			this.sun = sun;
		}
	}
		private class DiaryAdapter extends BaseAdapter {
			private LayoutInflater mInflater;
			private ArrayList<MyDiary> diaries;
			int arg0;
			View arg1;
			ViewGroup arg2;
			public DiaryAdapter(Context context) {
				mInflater = LayoutInflater.from(context);
				diaries = new ArrayList<MyDiary>();
				launchwithservice.flag2 = 0;
				getdata();
				getView(arg0, arg1, arg2);
			}
			public void getdata() {
				Cursor c = dba.getdiaries();
				startManagingCursor(c);
				if (c.moveToFirst()) {
					do {String nameofprofile = c.getString(c
								.getColumnIndex(Constants.KEY_name_of_profile));
						int startinghour = c.getInt(c
								.getColumnIndex(Constants.KEY_Startinghour));
						int startingmin = c.getInt(c
								.getColumnIndex(Constants.KEY_Startingminute));
						int endinghour = c.getInt(c
								.getColumnIndex(Constants.KEY_Endinghour));
						int endingmin = c.getInt(c
								.getColumnIndex(Constants.KEY_Endingminute));
						int typeofprofile = c.getInt(c
								.getColumnIndex(Constants.KEY_spinboxno));
						int mon = c.getInt(c.getColumnIndex(Constants.KEY_Monday));
						int tues = c
								.getInt(c.getColumnIndex(Constants.KEY_Tuesday));
						int wed = c.getInt(c
								.getColumnIndex(Constants.KEY_Wednesday));
						int thurs = c.getInt(c
								.getColumnIndex(Constants.KEY_Thursday));
						int fri = c.getInt(c.getColumnIndex(Constants.KEY_Friday));
						int sat = c
								.getInt(c.getColumnIndex(Constants.KEY_Saturday));
						int sun = c.getInt(c.getColumnIndex(Constants.KEY_Sunday));
						MyDiary temp = new MyDiary(nameofprofile, startinghour,
								startingmin, endinghour, endingmin, typeofprofile,
								mon, tues, wed, thurs, fri, sat, sun);
						diaries.add(temp);
					} while (c.moveToNext());
				}
			}
			@Override
			public int getCount() {
				return diaries.size();
			}
			@Override
			public MyDiary getItem(int i) {
				return diaries.get(i);
			}
			@Override
			public long getItemId(int i) {
				return i;
			}
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				{
					final ViewHolder holder;
					View v = arg1;
					View v2 = arg2;
					if ((v == null) || (v.getTag() == null)) {
						v = mInflater.inflate(R.layout.activity_list, null);
						holder = new ViewHolder();

						holder.mname_of_profile = (TextView) v
								.findViewById(R.id.nameofprofile);
						holder.mstartinghour = (TextView) v
								.findViewById(R.id.startinghour);
						holder.mstartingmin = (TextView) v
								.findViewById(R.id.startingmin);
						holder.mendinghour = (TextView) v
								.findViewById(R.id.endinghour);
						holder.mendingmin = (TextView) v
								.findViewById(R.id.endingmin);
						holder.mtype_of_profile = (TextView) v
								.findViewById(R.id.typeofprofile);
						holder.mmon = (TextView) v.findViewById(R.id.mon);
						holder.AM_PM_Startinghour = (TextView) v
								.findViewById(R.id.am_pm_startinghour);
						holder.AM_PM_Endinghour = (TextView) v
								.findViewById(R.id.am_pm_endinghour);

						v.setTag(holder);
					} else {
						holder = (ViewHolder) v.getTag();
					}
					try {
						holder.mdiary = getItem(arg0);
					} catch (Exception e) {
						flag = 1;
					}
					if (flag == 0) {
						if (holder.mdiary.name_of_profile == " ") {
						}
						holder.mname_of_profile.setText(String
								.valueOf(holder.mdiary.name_of_profile));
							holder.mstartinghour.setText(String
									.valueOf(holder.mdiary.startinghour));
							holder.mendinghour.setText(String
									.valueOf(holder.mdiary.endinghour));
						if (holder.mdiary.startingmin < 10) {
							stmin[0] = "0";
							stmin[1] = String.valueOf(holder.mdiary.startingmin);
							holder.mstartingmin.setText(stmin[0] + stmin[1]);
						} else {
							holder.mstartingmin.setText(String
									.valueOf(holder.mdiary.startingmin));
						}
						if (holder.mdiary.endingmin < 10) {
							etmin[0] = "0";
							etmin[1] = String.valueOf(holder.mdiary.endingmin);
							holder.mendingmin.setText(etmin[0] + etmin[1]);
						} else {
							holder.mendingmin.setText(String
									.valueOf(holder.mdiary.endingmin));
						}
						if (holder.mdiary.type_of_profile == 0) {
							holder.mtype_of_profile.setText(R.string.Ringtone);
						} else if (holder.mdiary.type_of_profile == 1) {
							holder.mtype_of_profile.setText(R.string.Vibrate);
						} else if (holder.mdiary.type_of_profile == 2) {
							holder.mtype_of_profile.setText(R.string.Silent);
						}else if(holder.mdiary.type_of_profile==3){
							holder.mtype_of_profile.setText(R.string.Airplane);
						}
						if (holder.mdiary.mon == 1) {
							holder.mmon.append("Mon ");
						}
						if (holder.mdiary.tues == 1) {
							holder.mmon.append("Tues  ");
						}
						if (holder.mdiary.wed == 1) {
							holder.mmon.append("Wed  ");
						}
						if (holder.mdiary.thurs == 1) {
							holder.mmon.append("Thurs  ");
						}
						if (holder.mdiary.fri == 1) {
							holder.mmon.append("Fri  ");
						}
						if (holder.mdiary.sat == 1) {
							holder.mmon.append("Sat  ");
						}
						if (holder.mdiary.sun == 1) {
							holder.mmon.append("Sun  ");
						}
						v.setTag(holder);
						return v;
					}
					return v2;
				}
			}			
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("EULA", "EULA");
		Eula.show(this);
		dba = new MyDB(this);
		dba.open();
		super.onCreate(savedInstanceState);
		pre=getSharedPreferences("mystartsettings", MODE_WORLD_READABLE);
		prefs = getSharedPreferences(data, MODE_WORLD_READABLE);
		prefs3 = getSharedPreferences("sets", MODE_WORLD_WRITEABLE);
		medit = prefs.edit();
		setContentView(R.layout.activity_main);
		mAlarmSender = PendingIntent.getService(
				MainActivity.this, 0, new Intent(
						MainActivity.this,
						BackgroundService.class), 0);
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(MainActivity.this,
						launchwithservice.class);
				startActivity(in);
			}
		});
		myAdapter = new DiaryAdapter(this);
		if (flag == 0) {
			this.setListAdapter(myAdapter);
		}
			getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				default:
					dialog_of_main(arg2);
					break;
				}
			}
		});
		Log.i("shifter ","the error");
		first_start();
	}
	public void first_start(){
		SharedPreferences ret=getSharedPreferences("mystartsettings", MODE_WORLD_READABLE);
		first_start=ret.getInt("start", 0);
        Log.i("value of first_start is ",String.valueOf(first_start));
		if(first_start==0){
		Log.i("first start","first start is running");
		Editor ps=pre.edit();
  		ps.putInt("start", 1);
		ps.commit();
		Log.i("inside if","would run only first time");
		start_service();
		}
	}
	public void dialog_of_main(int arg2) {
		final int arg3 = arg2;
		final String[] items = { "Modify", "Delete" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if (item == 0) {
					modify_profile(arg3);
				} else if (item == 1) {
					Delete_profile(arg3);
				}
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	public void Delete_profile(int ar) {
		final int ar2=ar;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.Delete)
				.setCancelable(false)
				.setPositiveButton(R.string.Yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Cursor c = dba.getdiaries();
								no_of_rows = c.getCount();
								dba.deleterow(ar2+1);
								dba.update_row(ar2+1, no_of_rows);
								restart_the_synch();
							}
						})
				.setNegativeButton(R.string.No,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
	public void modify_profile(int ar1) {
		Intent in = new Intent(MainActivity.this,
				ModifyProfile.class);
		in.putExtra("position_is", ar1);
		startActivity(in);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(GROUP_1, start_service, 1, R.string.action_start);
		menu.add(GROUP_2, stop_service, 2, R.string.action_stop);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case start_service:
			start_service();
			break;
		case stop_service:
			stop_service();
			break;
		case R.id.action_about:
			Intent action= new Intent("com.sark.shifter.ABOUT");
			startActivity(action);
			break;
		case R.id.action_help:
			Intent help= new Intent("com.sark.shifter.HELP");
			startActivity(help);
			break;
		}
		return true;
	}
	public boolean onPrepareOptionsMenu(Menu menu) {
		itemno = prefs.getInt("item_value", 1);
		if (itemno == 1) {
			menu.setGroupVisible(GROUP_2, true);
			menu.setGroupVisible(GROUP_1, false);
		} else if (itemno == 0) {
			menu.setGroupVisible(GROUP_1, true);
			menu.setGroupVisible(GROUP_2, false);
		}
		return super.onPrepareOptionsMenu(menu);
	}
	void  start_service() {
		itemno = 1;
		medit.putInt("item_value", itemno);
		medit.commit();
		long firstTime = SystemClock.elapsedRealtime();
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
				60000, mAlarmSender);
	}
	void stop_service() {
		itemno = 0;
		medit.putInt("item_value", itemno);
		medit.commit();
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(mAlarmSender);
		refresh_set();
	}
	void refresh_set() {
		Editor med = prefs3.edit();
		med.putInt("set1", 0);
		med.putInt("set2", 0);
		med.putInt("set3", 0);
		med.putInt("set4", 0);
		med.putInt("set5", 0);
		med.putInt("set6", 0);
		med.commit();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	protected void onResume() {
		if (launchwithservice.returnvalue() == 1) {
			myAdapter = new DiaryAdapter(this);
		}
		if (flag == 0 && launchwithservice.returnvalue() == 1) {
			this.setListAdapter(myAdapter);
		}
		if (ModifyProfile.returnvalue() == 1) {
			myAdapter = new DiaryAdapter(this);
		}
		if (flag == 0 && ModifyProfile.returnvalue() == 1) {
			this.setListAdapter(myAdapter);
		}
		if (itemno == 1) {
			start_service();
		}
		refresh_set();
		super.onResume();
	}
	public void restart_the_synch() {
		myAdapter = new DiaryAdapter(this);
		this.setListAdapter(myAdapter);
	}
}
