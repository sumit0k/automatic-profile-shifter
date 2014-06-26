package com.sark.shifter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
@SuppressLint("WorldWriteableFiles")
public class BackgroundService extends Service {
	private AudioManager maudio;
	public final String tag = "dmtrace";
	MyDB dba;
	int flag5;
	DataAdapter myAdapter;
	int set1 = 0, set2 = 0, set3 = 0, set4 = 0, set5 = 0, set6 = 0;
	private ArrayList<MyDBContent> mydbcont;
	SharedPreferences prefs;
	@SuppressWarnings("unused")
	private class MyDBContent {
		public MyDBContent(int id, String name_of_profile, int startinghour,
				int startingminute, int endinghour, int endingminute,
				int sunday, int monday, int tuesday, int wednesday,
				int thursday, int friday, int saturday, int spinboxno) {
			this.id = id;
			this.name_of_profile = name_of_profile;
			this.startinghour = startinghour;
			this.startingminute = startingminute;
			this.endinghour = endinghour;
			this.endingminute = endingminute;
			this.sunday = sunday;
			this.monday = monday;
			this.tuesday = tuesday;
			this.wednesday = wednesday;
			this.thursday = thursday;
			this.friday = friday;
			this.saturday = saturday;
			this.spinboxno = spinboxno;
		}
		public int id;
		public String name_of_profile;
		public int startinghour;
		public int startingminute;
		public int endinghour;
		public int endingminute;
		public int sunday;
		public int monday;
		public int tuesday;
		public int wednesday;
		public int thursday;
		public int friday;
		public int saturday;
		public int spinboxno;
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	public void onCreate() {
		super.onCreate();
		maudio = (AudioManager) getSystemService(AUDIO_SERVICE);
		prefs = getSharedPreferences("sets", MODE_WORLD_WRITEABLE);
		Thread initthread = new Thread(new Runnable() {
			@Override
			public void run() {
				show();
			}
		});
		initthread.start();
		String currentDateTimeString = java.text.DateFormat.getTimeInstance()
				.format(new Date());
		Calendar cd = Calendar.getInstance();
		int hour = cd.get(Calendar.HOUR);
		if (hour == 0) {
		}
	}
	@Override
	public void onDestroy() {
		dba.close();
		super.onDestroy();
	}
	private class DataAdapter extends BaseAdapter {
		int j = 0;
		int storedstartinghour[] = new int[100];
		int storedstartingminute[] = new int[100];
		int storedendinghour[] = new int[100];
		int storedendingminute[] = new int[100];
		int countersun = 0;
		int countermon = 0;
		int countertues = 0;
		int counterwed = 0;
		int counterthurs = 0;
		int counterfri = 0;
		int countersat = 0;
		int storedspinbox[] = new int[100];
		String storedname_of_profile[] = new String[100];
		public DataAdapter(Context context)
		{
			mydbcont = new ArrayList<BackgroundService.MyDBContent>();
			getdata();
		}
		public void getdata() {
			Cursor c = dba.getdiaries();
			Calendar cda = Calendar.getInstance();
			int day2 = cda.get(Calendar.DAY_OF_WEEK);
			if (c.moveToFirst()) {
				do {
					int id = c.getInt(c.getColumnIndex(Constants.KEY_ID));
					String name_of_profile = c.getString(c
							.getColumnIndex(Constants.KEY_name_of_profile));
					int startinghour = c.getInt(c
							.getColumnIndex(Constants.KEY_Startinghour));
					int startingminute = c.getInt(c
							.getColumnIndex(Constants.KEY_Startingminute));
					int endinghour = c.getInt(c
							.getColumnIndex(Constants.KEY_Endinghour));
					int endingminute = c.getInt(c
							.getColumnIndex(Constants.KEY_Endingminute));
					int spinboxno = c.getInt(c
							.getColumnIndex(Constants.KEY_spinboxno));
					int thursday = c.getInt(c
							.getColumnIndex(Constants.KEY_Thursday));
					int friday = c.getInt(c
							.getColumnIndex(Constants.KEY_Friday));
					int wednesday = c.getInt(c
							.getColumnIndex(Constants.KEY_Wednesday));
					int tuesday = c.getInt(c
							.getColumnIndex(Constants.KEY_Tuesday));
					int monday = c.getInt(c
							.getColumnIndex(Constants.KEY_Monday));
					int sunday = c.getInt(c
							.getColumnIndex(Constants.KEY_Sunday));
					int saturday = c.getInt(c
							.getColumnIndex(Constants.KEY_Saturday));
					if (sunday == 1) {
						int day3 = 1;
						if (day3 == day2) {
							storedstartinghour[countersun] = startinghour;
							storedstartingminute[countersun] = startingminute;
							storedendinghour[countersun] = endinghour;
							storedendingminute[countersun] = endingminute;
							storedspinbox[countersun] = spinboxno;
							storedname_of_profile[countersun] = name_of_profile;
							countersun++;
						}
					}
					if (monday == 1) {
						int day3 = 2;
						if (day3 == day2) {
							storedstartinghour[countermon] = startinghour;
							storedstartingminute[countermon] = startingminute;
							storedendinghour[countermon] = endinghour;
							storedendingminute[countermon] = endingminute;
							storedspinbox[countermon] = spinboxno;
							storedname_of_profile[countermon] = name_of_profile;
							countermon++;
						}
					}
					if (tuesday == 1) {
						int day3 = 3;
						if (day3 == day2) {
							storedstartinghour[countertues] = startinghour;
							storedstartingminute[countertues] = startingminute;
							storedendinghour[countertues] = endinghour;
							storedendingminute[countertues] = endingminute;
							storedspinbox[countertues] = spinboxno;
							storedname_of_profile[countertues] = name_of_profile;
							countertues++;
						}
					}
					if (wednesday == 1) {
						int day3 = 4;
						if (day3 == day2) {
							storedstartinghour[counterwed] = startinghour;
							storedstartingminute[counterwed] = startingminute;
							storedendinghour[counterwed] = endinghour;
							storedendingminute[counterwed] = endingminute;
							storedspinbox[counterwed] = spinboxno;
							counterwed++;
						}
					}
					if (thursday == 1) {
						int day3 = 5;
						if (day3 == day2) {
							storedstartinghour[counterthurs] = startinghour;
							storedstartingminute[counterthurs] = startingminute;
							storedendinghour[counterthurs] = endinghour;
							storedendingminute[counterthurs] = endingminute;
							storedspinbox[counterthurs] = spinboxno;
							counterthurs++;
						}
					}
					if (friday == 1) {
						int day3 = 6;
						if (day3 == day2) {
							storedstartinghour[counterfri] = startinghour;
							storedstartingminute[counterfri] = startingminute;
							storedendinghour[counterfri] = endinghour;
							storedendingminute[counterfri] = endingminute;
							storedspinbox[counterfri] = spinboxno;
							storedname_of_profile[counterfri] = name_of_profile;
							counterfri++;
						}
					}
					if (saturday == 1) {
						int day3 = 7;
						if (day3 == day2) {
							storedstartinghour[j] = startinghour;
							storedstartingminute[j] = startingminute;
							storedendinghour[j] = endinghour;
							storedendingminute[j] = endingminute;
							storedspinbox[j] = spinboxno;
							storedname_of_profile[j] = name_of_profile;
							countersat++;
						}
					}
					MyDBContent db = new MyDBContent(id, name_of_profile,
							startinghour, startingminute, endinghour,
							endingminute, sunday, monday, tuesday, wednesday,
							thursday, friday, saturday, spinboxno
							);
					mydbcont.add(db);
					j = j + 1;
				} while (c.moveToNext());
			}
		}
		@Override
		public int getCount() {
			return mydbcont.size();
		}
		@Override
		public MyDBContent getItem(int i) {
			return mydbcont.get(i);
		}
		@Override
		public long getItemId(int i) {
			return i;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
		}
	}
	public void show() {
		Editor mex = prefs.edit();
		dba = new MyDB(this);
		dba.open();
		myAdapter = new DataAdapter(this);
		if (myAdapter.countersun != 0) {
			getting_schedule(myAdapter.countersun, myAdapter);
		} else if (myAdapter.countermon != 0) {
			getting_schedule(myAdapter.countermon, myAdapter);
		} else if (myAdapter.countertues != 0) {
			getting_schedule(myAdapter.countertues, myAdapter);
		} else if (myAdapter.counterwed != 0) {
			getting_schedule(myAdapter.counterwed, myAdapter);
		} else if (myAdapter.counterthurs != 0) {
			getting_schedule(myAdapter.counterthurs, myAdapter);
		} else if (myAdapter.counterfri != 0) {
			getting_schedule(myAdapter.counterfri, myAdapter);
		} else if (myAdapter.countersat != 0) {
			getting_schedule(myAdapter.countersat, myAdapter);
		} else {
			set4 = prefs.getInt("set4", 0);
			if (set4 == 0) {
				mex.putInt("set4", 1);
				mex.putInt("set1", 0);
				mex.putInt("set2", 0);
				mex.putInt("set3", 0);
				mex.putInt("set5", 0);
				mex.putInt("set6", 0);
				mex.commit();
				Airplane_mode(false);
				ringtone();
				Log.i("auto default", "no profile is found");
			}
		}
		BackgroundService.this.stopSelf();
	}
	public void ringtone() {
		maudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}
	public void vibrate() {
		maudio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	}
	public void silent() {
		maudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	}
	public void default_ringtone() {
		maudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}
	@SuppressWarnings("deprecation")
	public void Airplane_mode(boolean isEnabled) {
		if (isEnabled) {
			Settings.System.putInt(this.getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 1);
			Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			 intent.putExtra("state", isEnabled);
				sendBroadcast(intent);			
		} else 
		if(!isEnabled){
			Settings.System.putInt(this.getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 0);
			Settings.System.putInt(this.getContentResolver(),
					Settings.System.AIRPLANE_MODE_RADIOS, 1);
			Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			 intent.putExtra("state", isEnabled);
				sendBroadcast(intent);
		}		
	}
	public void getting_schedule(int counter2, DataAdapter dataadapterob) {
		float dbstarttime[] = new float[100];
		float dbstarttime2[] = new float[100];
		float dbendtime[] = new float[100];
		float dbendtime2[] = new float[100];
		int startingindex[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 };
		int endingindex[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 };
		Calendar cdm = Calendar.getInstance();
		int hour1 = cdm.get(Calendar.HOUR_OF_DAY);
		int min1 = cdm.get(Calendar.MINUTE);
		float decimalmin = (float) 0.01 * min1;
		float netcurrenttime = decimalmin + (float) hour1;
		for (int i = 0; i < counter2; i++) {
			int startinghourfromdb = myAdapter.storedstartinghour[i];
			int startingminfromdb = myAdapter.storedstartingminute[i];
			dbstarttime[i] = (float) (startinghourfromdb + 0.01 * startingminfromdb);
			dbstarttime2[i] = (float) (startinghourfromdb + 0.01 * startingminfromdb);
		}
		inc_order(dbstarttime, counter2, startingindex);
		for (int i = 0; i < counter2; i++) {
			int endinghourfromdb = myAdapter.storedendinghour[i];
			int endingminfromdb = myAdapter.storedendingminute[i];
			dbendtime[i] = (float) (endinghourfromdb + 0.01 * endingminfromdb);
			dbendtime2[i] = (float) (endinghourfromdb + 0.01 * endingminfromdb);
		}
		dec_order(dbendtime, counter2, endingindex);
		synchronization(counter2, netcurrenttime, dbstarttime2, dbendtime,
				startingindex, endingindex, dbendtime2);
	}
	public void synchronization(int counter2, float netcurrenttime,
			float dbstarttime2[], float dbendtime[], int starttingindex[],
			int endingindex[], float dbendtime2[]) {
		 flag5=0;
		Editor me = prefs.edit();
		set1 = prefs.getInt("set1", 0);
		set2 = prefs.getInt("set2", 0);
		set3 = prefs.getInt("set3", 0);
		set5 = prefs.getInt("set5", 0);
		set6 = prefs.getInt("set6", 0);
		@SuppressWarnings("unused")
		int m = 0;
		for (int i = counter2 - 1; i >= 0; i--) {
			int log = starttingindex[i];
			if (dbstarttime2[log] <= dbendtime2[log]) {
				if ((netcurrenttime >= dbstarttime2[log])
						&& (netcurrenttime <= dbendtime2[log])) {
					flag5=1;
					Log.i("value of flag5 inside execution is",String.valueOf(flag5));
					Log.i("shifter","starting time is smaller than ending time");
					Log.i("shifter-value of index is",String.valueOf(log));
					m = 1;
					if (myAdapter.storedspinbox[log] == 0) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set3", "value of set3 is" + String.valueOf(set3));
						if (set1 == 0) {
							ringtone();
							Airplane_mode(false);
							Log.i("tone", "Ringtone is called");
							me.putInt("set1", 1);
							me.putInt("set2", 0);
							me.putInt("set3", 0);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.putInt("set6", 0);
							me.commit();
						} else {
							break;
						}
					}
					else if (myAdapter.storedspinbox[log] == 1) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set3", "value of set3 is" + String.valueOf(set3));
						if (set2 == 0) {
							Log.i("tone", "Vibrate is called");
							vibrate();
							Airplane_mode(false);
							me.putInt("set1", 0);
							me.putInt("set2", 1);
							me.putInt("set3", 0);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.putInt("set6", 0);
							me.commit();
						} else {
							break;
						}
					} else if (myAdapter.storedspinbox[log] == 2) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set3", "value of set3 is" + String.valueOf(set3));
						if (set3 == 0) {
							silent();
							Airplane_mode(false);
							Log.i("tone", "silent is called");
							me.putInt("set1", 0);
							me.putInt("set2", 0);
							me.putInt("set3", 1);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.putInt("set6", 0);
							me.commit();
						} else {
							break;
						}
					}
					else if (myAdapter.storedspinbox[log] == 3) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set6", "value of set6 is" + String.valueOf(set6));
						if (set6 == 0) {
							Airplane_mode(true);
							Log.i("tone", "Airplane is called");
							me.putInt("set6", 1);
							me.putInt("set1", 0);
							me.putInt("set2", 0);
							me.putInt("set3", 0);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.commit();
						} else {
							break;
						}
					}
					Log.i("value of flag5 is last",String.valueOf(flag5));
					break;
				}
			}
		if(dbendtime2[log]<dbstarttime2[log]){
			Log.i("dbstarttime",String.valueOf(dbstarttime2[i]));
			Log.i("dbendtime",String.valueOf(dbendtime2[i]));
				if(!(netcurrenttime<dbstarttime2[log]&&netcurrenttime>dbendtime[log])){
					Log.i("shifter","starting time is greater than ending time");
					Log.i("shifter","running due to reverse condition");
					m = 1;
					flag5=1;
					if (myAdapter.storedspinbox[log] == 0) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set3", "value of set3 is" + String.valueOf(set3));
						if (set1 == 0) {
							ringtone();
							Airplane_mode(false);
							Log.i("tone", "Ringtone is called");
							me.putInt("set1", 1);
							me.putInt("set2", 0);
							me.putInt("set3", 0);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.putInt("set6", 0);
							me.commit();
						} else {
							break;
						}
					}
					else if (myAdapter.storedspinbox[log] == 1) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set3", "value of set3 is" + String.valueOf(set3));
						if (set2 == 0) {
							Log.i("tone", "Vibrate is called");
							vibrate();
							Airplane_mode(false);
							me.putInt("set1", 0);
							me.putInt("set2", 1);
							me.putInt("set3", 0);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.putInt("set6", 0);
							me.commit();
						} else {
							break;
						}
					} else if (myAdapter.storedspinbox[log] == 2) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set3", "value of set3 is" + String.valueOf(set3));
						if (set3 == 0) {
							silent();
							Airplane_mode(false);
							Log.i("tone", "silent is called");
							me.putInt("set1", 0);
							me.putInt("set2", 0);
							me.putInt("set3", 1);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.putInt("set6", 0);
							me.commit();
						} else {
							break;
						}
					}
					else if (myAdapter.storedspinbox[log] == 3) {
						Log.i("set1", "value of set1 is" + String.valueOf(set1));
						Log.i("set2", "value of set2 is" + String.valueOf(set2));
						Log.i("set6", "value of set6 is" + String.valueOf(set6));
						if (set6 == 0) {
							Airplane_mode(true);
							Log.i("tone", "Airplane is called");
							me.putInt("set6", 1);
							me.putInt("set1", 0);
							me.putInt("set2", 0);
							me.putInt("set3", 0);
							me.putInt("set4", 0);
							me.putInt("set5", 0);
							me.commit();
						} else {
							break;
						}
					}
					break;
				}
			}
			if ((i == 0) && netcurrenttime > dbendtime2[log]) {
				if (set5 == 0) {
					flag5=1;
					Log.i("shifter", "default is called");
					default_ringtone();
					Airplane_mode(false);
					me.putInt("set1", 0);
					me.putInt("set2", 0);
					me.putInt("set3", 0);
					me.putInt("set4", 0);
					me.putInt("set5", 1);
					me.putInt("set6", 0);
					me.commit();
				}
			} 
			Log.i("shifter search","searching for new algo");
		}
	}
	public float[] inc_order(float dbstarttime[], int counter3,
			int startingindex[]) {
		float min, temp;
		int loc, temp1;
		for (int i = 0; i <= counter3 - 1; i++) {
			min = dbstarttime[i];
			loc = i;
			for (int j = i + 1; j <= counter3 - 1; j++) {
				if (dbstarttime[j] < min) {
					min = dbstarttime[j];
					loc = j;
				}
			}
			if (loc != i) {
				temp = dbstarttime[i];
				dbstarttime[i] = dbstarttime[loc];
				dbstarttime[loc] = temp;
				temp1 = startingindex[i];
				startingindex[i] = startingindex[loc];
				startingindex[loc] = temp1;
			}
		}
		return dbstarttime;
	}
	public float[] dec_order(float dbendtime[], int counter3, int endingindex[]) {
		float max, temp;
		int loc, temp1;
		for (int i = 0; i <= counter3 - 1; i++) {
			max = dbendtime[i];
			loc = i;
			for (int j = i + 1; j <= counter3 - 1; j++) {
				if (dbendtime[j] > max) {
					max = dbendtime[j];
					loc = j;
				}
			}
			if (loc != 0) {
				temp = dbendtime[i];
				dbendtime[i] = dbendtime[loc];
				dbendtime[loc] = temp;
				temp1 = endingindex[i];
				endingindex[i] = endingindex[loc];
				endingindex[loc] = temp1;
			}
		}
		return dbendtime;
	}
}