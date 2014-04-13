package web.field;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import web.field.helpers.Observer;
import web.field.helpers.SynchronizationResult;
import web.field.sync.SynchronizationService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SynchronizationActivity extends OrmLiteBaseActivity<OrmDbHelper>
		implements Observer {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_synchronization);

		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		String serviceAddress = preferences.getString(
				SharedPreferencesKeys.connection_service, null);
		if (serviceAddress != null) {
			SynchronizationService service = new SynchronizationService();

			// register for notification
			service.register(this);

			service.doInitialDataPull(getHelper());

		}

	}

	@Override
	public void update(Object obj) {
		SynchronizationResult syncResult = (SynchronizationResult) obj;
		if (syncResult != null) {
			if (syncResult.isSucceed()) {
				SharedPreferences preferences = WebFieldApplication
						.getSharedPreferences();
				SharedPreferences.Editor editor = preferences.edit();

				editor.putString(
						SharedPreferencesKeys.last_synchronization_time,
						String.valueOf(System.currentTimeMillis()));
				editor.commit();
				Intent i = new Intent(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			} else {
				Toast.makeText(getApplicationContext(), syncResult.getResult(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
