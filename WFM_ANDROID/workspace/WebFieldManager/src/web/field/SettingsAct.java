package web.field;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsAct extends WebfieldFragmentActivityInner {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction()
		.replace(android.R.id.content, 
				new SettingsActFragment()).commit();
		
		dismissProgressDialog();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// just to display message that a new user needs his/her pin setup
		SharedPreferences preferences = WebFieldApplication.getSharedPreferences();
		String userToken = preferences.getString("user_token", null);
		String oldPin = preferences.getString("pin_" + userToken, null);
		if (userToken != null && oldPin == null)
			message(R.string.set_pin_welcome);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			this.finish();
			return true;
		}
		return false;
	}
}
