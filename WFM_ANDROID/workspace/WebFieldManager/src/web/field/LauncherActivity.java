package web.field;

import android.os.Bundle;
import android.view.Menu;

public class LauncherActivity extends WebFieldActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		checkPIN(this);
	}
	
	//no menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		return true;
	}
}
