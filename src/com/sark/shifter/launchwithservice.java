package com.sark.shifter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
public class launchwithservice extends Activity {
	private static final String[] oceans = { "Ringtone", "Vibrate", "Silent",
			"Airplane" };
	private boolean c1 = false, c2 = false, c3 = false, c4 = false, c5 = false,
			c6 = false, c7 = false;
	private CharSequence name_of_profile;
	static int x = 0;
	static int flag2;
	int currenthour;
	private int Spinner_value;
	private int Timepicker1_hour;
	private int Timepicker1_minute;
	private int Timepicker2_hour;
	private int Timepicker2_minute;
	private int id;	
	MyDB dba;
	MyDBhelper db;
	Thread th;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		final EditText et = (EditText) findViewById(R.id.edit1);
		final TimePicker tp1 = (TimePicker) findViewById(R.id.timePicker1);
		currenthour = tp1.getCurrentHour();
		final TimePicker tp2 = (TimePicker) findViewById(R.id.timePicker2);
		final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
		final CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
		final CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox3);
		final CheckBox cb4 = (CheckBox) findViewById(R.id.checkBox4);
		final CheckBox cb5 = (CheckBox) findViewById(R.id.checkBox5);
		final CheckBox cb6 = (CheckBox) findViewById(R.id.checkBox6);
		final CheckBox cb7 = (CheckBox) findViewById(R.id.checkBox7);
		final Spinner sp = (Spinner) findViewById(R.id.spinner1);
		dba = new MyDB(this);
		dba.open();
		Button btn = (Button) findViewById(R.id.button1);
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
				R.layout.activity_spinner_entry, oceans);
		mAdapter.setDropDownViewResource(R.layout.activity_spinner_entry);
		sp.setAdapter(mAdapter);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Cursor c = dba.getdiaries();
				id = c.getCount();
				name_of_profile = et.getText();
				Timepicker1_hour = tp1.getCurrentHour();
				Timepicker1_minute = tp1.getCurrentMinute();
				Timepicker2_minute = tp2.getCurrentMinute();
				Timepicker2_hour = tp2.getCurrentHour();
				Spinner_value = sp.getSelectedItemPosition();
				if (cb1.isChecked()) {
					c1 = true;
				}
				if (cb2.isChecked())
					c2 = true;
				if (cb3.isChecked())
					c3 = true;
				if (cb4.isChecked())
					c4 = true;
				if (cb5.isChecked())
					c5 = true;
				if (cb6.isChecked())
					c6 = true;
				if (cb7.isChecked())
					c7 = true;
					saveItToDB();
					x = 1;
					MainActivity.flag = 0;
					dba.close();
					finish();
			}
		});
		Button btn2 = (Button) findViewById(R.id.cancelbutton);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				x = 0;
				finish();
			}
		});
	}
	public void saveItToDB() {
		dba.insertdiary(id + 1, name_of_profile.toString(), Timepicker1_hour,
				Timepicker1_minute, Timepicker2_hour, Timepicker2_minute, c1,
				c2, c3, c4, c5, c6, c7, Spinner_value);
		dba.close();
	}
	@Override
	protected void onDestroy() {
			super.onDestroy();
	}
	public static int returnvalue() {
		if (x == 1) {
			MainActivity.flag2 = 1;
		}
		return MainActivity.flag2;
	}
}