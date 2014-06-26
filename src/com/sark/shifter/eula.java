package com.sark.shifter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;

/**
 * Displays an EULA ("End User License Agreement") that the user has to accept
 * before using the application.
 */
class Eula {
	private static final String ASSET_EULA = "EULA.txt";
	private static final String PREFERENCE_EULA_ACCEPTED = "eula.accepted";
	private static final String PREFERENCES_EULA = "eula";

	/**
	 * callback to let the activity know when the user accepts the EULA.
	 */
	static interface OnEulaAgreedTo {
		void onEulaAgreedTo();
	}

	/**
	 * Displays the EULA if necessary.
	 */
	static boolean show(final Activity activity) {
		final SharedPreferences preferences = activity.getSharedPreferences(
				PREFERENCES_EULA, Activity.MODE_PRIVATE);
		// to test:
		// preferences.edit()
		// .putBoolean(PREFERENCE_EULA_ACCEPTED, false).commit();

		if (!preferences.getBoolean(PREFERENCE_EULA_ACCEPTED, false)) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(
					activity);
			builder.setTitle(R.string.eula_tilte);
			builder.setCancelable(true);
			builder.setPositiveButton(R.string.eula_accept,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							accept(preferences);
							if (activity instanceof OnEulaAgreedTo) {
								((OnEulaAgreedTo) activity).onEulaAgreedTo();
								
							}
						}
					});
			builder.setNegativeButton(R.string.eula_refuse,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							refuse(activity);
						}
					});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					refuse(activity);
				}
			});
			builder.setMessage(readEula(activity));
			builder.create().show();
			return false;
		}
		return true;
	}

	private static void accept(SharedPreferences preferences) {
		preferences.edit().putBoolean(PREFERENCE_EULA_ACCEPTED, true).commit();
//		AutomaticprofileChangerActivity ob = new AutomaticprofileChangerActivity();
//		ob.start_service();
//		Log.i("inside the EULA","first starting");
	}

	private static void refuse(Activity activity) {
		activity.finish();
	}

	private static CharSequence readEula(Activity activity) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(activity.getAssets()
					.open(ASSET_EULA)));

			String line;
			StringBuilder buffer = new StringBuilder();
			while ((line = in.readLine()) != null)
				buffer.append(line);

			return buffer;
		} catch (IOException e) {
			return "";
		} finally {
			closeStream(in);
		}
	}

	/**
	 * Closes the specified stream.
	 */
	private static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}
}