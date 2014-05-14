package web.field;

import java.util.prefs.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.PreferenceFragment;
import android.util.AttributeSet;

public class SettingsActFragment extends PreferenceFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.all_settings);
	}
	
}
