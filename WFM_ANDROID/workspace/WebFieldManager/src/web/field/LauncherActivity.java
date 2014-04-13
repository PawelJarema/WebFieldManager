package web.field;

import android.os.Bundle;

public class LauncherActivity extends WebFieldActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkPIN(this);
	}

}
