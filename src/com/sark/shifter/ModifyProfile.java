package com.sark.shifter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class ModifyProfile extends Activity {
	MyDB dba;
	static int x = 0;
	private static final String[] oceans = { "Ringtone", "Vibrate", "Silent",
			"Airplane" };
	int id;
	EditText et;
	Spinner sp;
	TimePicker tp1;
	TimePicker tp2;
	CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
	String id_at_db;
	String nameofprofile;
	int startinghour, startingmin, endinghour, endingmin, typeofprofile,
			mon = 0, tues = 0, wed = 0, thurs = 0, fri = 0, sat = 0, sun = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dba = new MyDB(this);
		dba.open();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		Intent myintent = getIntent();
		sp = (Spinner) findViewById(R.id.spinner1);
		TextView tv = (TextView) findViewById(R.id.name_of_profile);
		tv.setText(R.string.Ren_the_profile);
		Button savebtn = (Button) findViewById(R.id.button1);
		savebtn.setText(R.string.Modify);
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
				R.layout.activity_spinner_entry, oceans);
		mAdapter.setDropDownViewResource(R.layout.activity_spinner_entry);
		sp.setAdapter(mAdapter);
		et = (EditText) findViewById(R.id.edit1);
		tp1 = (TimePicker) findViewById(R.id.timePicker1);
		tp2 = (TimePicker) findViewById(R.id.timePicker2);
		cb1 = (CheckBox) findViewById(R.id.checkBox1);
		cb2 = (CheckBox) findViewById(R.id.checkBox2);
		cb3 = (CheckBox) findViewById(R.id.checkBox3);
		cb4 = (CheckBox) findViewById(R.id.checkBox4);
		cb5 = (CheckBox) findViewById(R.id.checkBox5);
		cb6 = (CheckBox) findViewById(R.id.checkBox6);
		cb7 = (CheckBox) findViewById(R.id.checkBox7);
		id = myintent.getIntExtra("position_is", 0);
		getdata();
		savebtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				savetodb(id + 1);
				x = 1;
				dba.close();
				finish();
			}
		});
		Button cb = (Button) findViewById(R.id.cancelbutton);
		cb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	@SuppressWarnings("deprecation")
	public void getdata() {
		Cursor c2 = dba.getdiaries();
		startManagingCursor(c2);
		if (c2.moveToFirst()) {
			do {
				id_at_db = c2.getString(c2.getColumnIndex(Constants.KEY_ID));
				if (Integer.valueOf(id_at_db) == id + 1) {
					nameofprofile = c2.getString(c2
							.getColumnIndex(Constants.KEY_name_of_profile));
					et.setText(nameofprofile);
					startinghour = c2.getInt(c2
							.getColumnIndex(Constants.KEY_Startinghour));
					tp1.setCurrentHour(startinghour);
					startingmin = c2.getInt(c2
							.getColumnIndex(Constants.KEY_Startingminute));
					tp1.setCurrentMinute(startingmin);
					endinghour = c2.getInt(c2
							.getColumnIndex(Constants.KEY_Endinghour));
					tp2.setCurrentHour(endinghour);
					endingmin = c2.getInt(c2
							.getColumnIndex(Constants.KEY_Endingminute));
					tp2.setCurrentMinute(endingmin);
					typeofprofile = c2.getInt(c2
							.getColumnIndex(Constants.KEY_spinboxno));
					sp.setSelection(typeofprofile);
					mon = c2.getInt(c2.getColumnIndex(Constants.KEY_Monday));
					if (mon == 1) {
						cb2.setChecked(true);
					}
					tues = c2.getInt(c2.getColumnIndex(Constants.KEY_Tuesday));
					if (tues == 1) {
						cb3.setChecked(true);
					}
					wed = c2.getInt(c2.getColumnIndex(Constants.KEY_Wednesday));
					if (wed == 1) {
						cb4.setChecked(true);
					}
					thurs = c2
							.getInt(c2.getColumnIndex(Constants.KEY_Thursday));
					if (thurs == 1) {
						cb5.setChecked(true);
					}
					fri = c2.getInt(c2.getColumnIndex(Constants.KEY_Friday));
					if (fri == 1) {
						cb6.setChecked(true);
					}
					sat = c2.getInt(c2.getColumnIndex(Constants.KEY_Saturday));
					if (sat == 1) {
						cb7.setChecked(true);
					}
					sun = c2.getInt(c2.getColumnIndex(Constants.KEY_Sunday));
					if (sun == 1) {
						cb1.setChecked(true);
					}
				}
			} while (c2.moveToNext());
		}
	}
	public void savetodb(int id2) {
		nameofprofile = String.valueOf(et.getText());
		typeofprofile = sp.getSelectedItemPosition();
		startinghour = tp1.getCurrentHour();
		startingmin = tp1.getCurrentMinute();
		endinghour = tp2.getCurrentHour();
		endingmin = tp2.getCurrentMinute();
		if (cb1.isChecked()) {
			sun = 1;
		} else
			sun = 0;
		if (cb2.isChecked()) {
			mon = 1;
		} else
			mon = 0;
		if (cb3.isChecked()) {
			tues = 1;
		} else
			tues = 0;
		if (cb4.isChecked()) {
			wed = 1;
		} else
			wed = 0;
		if (cb5.isChecked()) {
			thurs = 1;
		} else
			thurs = 0;
		if (cb6.isChecked()) {
			fri = 1;
		} else
			fri = 0;
		if (cb7.isChecked()) {
			sat = 1;
		} else
			sat = 0;
		dba.modify_row_all_item(id2, nameofprofile, typeofprofile,
				startinghour, startingmin, endinghour, endingmin, mon, tues,
				wed, thurs, fri, sat, sun);
	}
	public static int returnvalue() {
		if (x == 1) {
			MainActivity.flag2 = 1;
		}
		return MainActivity.flag2;
	}
}