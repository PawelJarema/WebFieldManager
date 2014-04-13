package web.field;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class WebFieldApplication extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		WebFieldApplication.context = getApplicationContext();
	}

	public static Context getAppContext() {
		return WebFieldApplication.context;
	}

	public static SharedPreferences getSharedPreferences() {
		return PreferenceManager
				.getDefaultSharedPreferences(WebFieldApplication
						.getAppContext());
	}

	public void clearSharedPreferences() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(WebFieldApplication
						.getAppContext());
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}

}
