package web.field;

import java.util.concurrent.TimeUnit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
	
	protected void showYesNoDialog(String message, DialogInterface.OnClickListener listener) {
		if (listener == null)
			listener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				        	getSupportFragmentManager().popBackStackImmediate();
							dialog.dismiss();
				            break;
				        case DialogInterface.BUTTON_NEGATIVE:
				            dialog.dismiss();
				            break;
			        }
			    }
			}; // default listener closes fragments
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage(message);
		builder.setPositiveButton(getResources().getString(R.string.yes), listener);
		builder.setNegativeButton(getResources().getString(R.string.no), listener).show();
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
	
	public void startActivityAsync(Intent i) {
		final Intent intent = i;
		AsyncTask loadActivity = new AsyncTask() {
			@Override 
			protected void onPreExecute() {
				showProgressDialog();
			}
			
			@Override
			protected Object doInBackground(Object... params) {
				startActivity(intent);
				dismissProgressDialog();
				return null;
			}
		};
		loadActivity.execute();
	}
}
