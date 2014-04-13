package web.field;

import java.util.concurrent.TimeUnit;


import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public abstract class WebFieldActivity extends OrmLiteBaseActivity<OrmDbHelper> {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (needsDataPull()) {
			Intent i = new Intent("web.field.SynchronizationActivity");
			startActivity(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = null;
		switch (item.getItemId()) {
		case R.id.action_order_templates:
			// settings
			i = new Intent("web.field.OrderTemplatesActivity");
			startActivity(i);
			return true;
		case R.id.action_products:
			// settings
			i = new Intent("web.field.ProductsActivity");
			startActivity(i);
			return true;
		case R.id.action_customers:
			// settings
			i = new Intent("web.field.CustomersActivity");
			startActivity(i);
			return true;
		case R.id.action_settings:
			// settings
			i = new Intent("web.field.SettingsActivity");
			startActivity(i);
			return true;
		case R.id.action_orders:
			// settings
			i = new Intent("web.field.OrdersActivity");
			startActivity(i);
			return true;
		case R.id.action_logout:
			logOut();
			return true;
		default:
			return super.onOptionsItemSelected(item);
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

	protected void logOut() {
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(SharedPreferencesKeys.user_token);
		editor.remove(SharedPreferencesKeys.user_name);
		editor.commit();
		clearPIN();
		Intent i = new Intent("web.field.LoginActivity");
		startActivity(i);
	}

	protected void clearPIN() {
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(SharedPreferencesKeys.pin_ok);
		editor.commit();
	}

	protected void checkPIN(final Context context) {
		final SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();

		// user token and PIN is set
		final String token = preferences.getString(
				SharedPreferencesKeys.user_token, null);
		if (token != null) {
			final String pin = preferences.getString("pin_" + token, null);
			// check pin only when user logged on at least once
			LayoutInflater factory = LayoutInflater.from(context);
			if (pin == null) {
				// set new PIN
				final View setPinView = factory.inflate(
						R.layout.dialog_set_pin, null);
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle(R.string.set_pin);
				alert.setView(setPinView);

				// Set to null. We override the onclick
				alert.setPositiveButton(R.string.ok, null);
				alert.setNegativeButton(R.string.cancel, null);

				final AlertDialog dialog = alert.create();
				dialog.setOnShowListener(new DialogInterface.OnShowListener() {

					@Override
					public void onShow(DialogInterface di) {

						Button ok = dialog
								.getButton(AlertDialog.BUTTON_POSITIVE);
						ok.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View view) {
								// String value = input.getText().toString();
								EditText etPin = (EditText) setPinView
										.findViewById(R.id.pin);
								EditText etPinConfirm = (EditText) setPinView
										.findViewById(R.id.pin_confirm);
								String enteredPin = etPin.getText().toString();
								String enteredPinConfirm = etPinConfirm
										.getText().toString();

								if (!TextUtils.equals(enteredPin,
										enteredPinConfirm)) {
									TextView tvPinError = (TextView) setPinView
											.findViewById(R.id.pin_error);
									tvPinError
											.setText(R.string.error_pins_dont_match);
								} else {
									// save user pin
									SharedPreferences.Editor editor = preferences
											.edit();
									editor.putString("pin_" + token, enteredPin);
									editor.commit();
									Intent i = new Intent(getApplicationContext(),
											HomeActivity.class);
									startActivity(i);
									dialog.dismiss();
								}

							}
						});

						Button cancel = dialog
								.getButton(AlertDialog.BUTTON_NEGATIVE);

						cancel.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View view) {
								logOut();
							}

						});
					}
				});

				dialog.show();
			} else {
				// ask for PIN
				final View enterPinView = factory.inflate(
						R.layout.dialog_enter_pin, null);
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle(R.string.enter_pin);
				alert.setMessage(R.string.pin);
				alert.setView(enterPinView);

				alert.setPositiveButton(R.string.ok, null);
				alert.setNegativeButton(R.string.cancel, null);
				final AlertDialog dialog = alert.create();
				dialog.setOnShowListener(new DialogInterface.OnShowListener() {

					@Override
					public void onShow(DialogInterface di) {

						Button ok = dialog
								.getButton(AlertDialog.BUTTON_POSITIVE);
						ok.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View view) {
								// String value = input.getText().toString();
								EditText etPin = (EditText) enterPinView
										.findViewById(R.id.pin);
								String enteredPin = etPin.getText().toString();
								if (!TextUtils.equals(pin, enteredPin)) {
									TextView tvPinError = (TextView) enterPinView
											.findViewById(R.id.pin_error);
									tvPinError
											.setText(R.string.error_pin_wrong);
								} else {
									Intent i = new Intent(getApplicationContext(),
											HomeActivity.class);
									startActivity(i);
									dialog.dismiss();
								}
							}
						});

						Button cancel = dialog
								.getButton(AlertDialog.BUTTON_NEGATIVE);

						cancel.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View view) {
								logOut();
							}

						});
					}
				});
				dialog.show();
			}
		} else {
			Intent i = new Intent("web.field.LoginActivity");
			startActivity(i);
		}
	}

}
