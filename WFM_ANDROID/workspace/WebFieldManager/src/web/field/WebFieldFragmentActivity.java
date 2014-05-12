package web.field;

import java.util.concurrent.TimeUnit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class WebFieldFragmentActivity extends FragmentActivity {
	
	private ProgressDialog progressDialog = null; 
	
	protected void showProgressDialog() {
		this.progressDialog = ProgressDialog.show(this, 
				getResources().getString(R.string.network), 
				getResources().getString(R.string.downloading),
				true, false);
	}
	
	protected void dismissProgressDialog() {
		if (this.progressDialog != null)
			this.progressDialog.dismiss();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showProgressDialog();
		if (needsDataPull()) {
			dismissProgressDialog();
			Intent i = new Intent("web.field.SynchronizationActivity");
			startActivity(i);
		}
	}
	
	protected boolean needsDataPull() {
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		String lastSynchornizationTime = preferences.getString(
				SharedPreferencesKeys.last_synchronization_time, null);

		return lastSynchornizationTime == null;
	}

	protected boolean needsSync() {
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		String lastSynchornizationTime = preferences.getString(
				SharedPreferencesKeys.last_synchronization_time, null);
		long now = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
		boolean needsSync = false;
		if (lastSynchornizationTime == null) {
			needsSync = true;
		} else {
			long lastSync = Long.parseLong(lastSynchornizationTime);
			long seconds = (now - lastSync);
			if (seconds > 5) {
				needsSync = true;
			}
		}

		return needsSync;
	}
}
