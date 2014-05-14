package web.field;

import android.os.Bundle;
import android.preference.PreferenceActivity;
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
