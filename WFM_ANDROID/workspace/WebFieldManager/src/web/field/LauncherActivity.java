package web.field;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;

public class LauncherActivity extends WebFieldActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(this, R.xml.all_settings, false);
		getActionBar().hide();
		checkPIN(this);
	}
	
	//no menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		return true;
	}
}
