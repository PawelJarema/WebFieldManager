package web.field;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PinPickerPreference extends DialogPreference implements TextWatcher {

	@Override
	protected void showDialog(Bundle state) {
		super.showDialog(state);		
	}
	
	private SharedPreferences preferences;
	private String oldPin;
	private String newPin;
	private String repeatedPin;
	private String userToken;

	private Context context;
	private EditText etOldPin;
	private EditText etNewPin;
	private EditText etRepeatPin;
	
	public PinPickerPreference(Context context, AttributeSet attributes) {
		super(context, attributes);
		setDialogLayoutResource(R.layout.pinpicker);
		setPositiveButtonText(context.getResources().getString(R.string.ok));
		setNegativeButtonText(context.getResources().getString(R.string.cancel));
		this.context = context;
	}
	
	@Override
	protected void onBindDialogView(View view) {
		super.onBindDialogView(view);
		etOldPin = (EditText) view.findViewById(R.id.old_pin);
		etNewPin = (EditText) view.findViewById(R.id.new_pin);
		etRepeatPin = (EditText) view.findViewById(R.id.repeat_pin);
		
		preferences = (SharedPreferences) getSharedPreferences();
		userToken = ((SharedPreferences) preferences).getString("user_token", null);
		oldPin = ((SharedPreferences) preferences).getString("pin_" + userToken, null);
		
		if (userToken == null || userToken.equals("")) {
			message(R.string.no_user_token);
			//TODO dismiss dialog 
		}
		
		if (oldPin == null) {
			etNewPin.setVisibility(View.VISIBLE);
			etRepeatPin.setVisibility(View.VISIBLE);
			etOldPin.setVisibility(View.GONE);
		}
			
		etOldPin.addTextChangedListener(this);	
		etNewPin.addTextChangedListener(this);
		etRepeatPin.addTextChangedListener(this);
	}
	
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		if (restorePersistedValue) {

		}
	}
	
	@Override
	protected void onDialogClosed(boolean positiveResult) {
		if (positiveResult) {
			if (newPin.equals(repeatedPin)) {
				//persistString(newPin);
				preferences.edit().putString("pin_" + userToken, newPin).commit();
				message(R.string.pin_saved);
			} else {
				message(R.string.error_pins_dont_match);
			}
		} else {
			//TODO prevent dismiss?
		}
	}	
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}
	@Override
	public void afterTextChanged(Editable s) {
		String pin = etOldPin.getText().toString();
		if (oldPin == null || oldPin.equals(pin)) {
			etNewPin.setVisibility(View.VISIBLE);
			etRepeatPin.setVisibility(View.VISIBLE);
			
			newPin = etNewPin.getText().toString();
			repeatedPin = etRepeatPin.getText().toString();
			setPositiveButtonText(context.getResources().getString(R.string.ok));
			if (newPin.equals(repeatedPin)) {
				//setPositiveButtonText(context.getResources().getString(R.string.ok));
			}
		} else {
			etNewPin.setVisibility(View.GONE);
			etRepeatPin.setVisibility(View.GONE);
			//setPositiveButtonText(null);
		}
	}
	
	private void message(int resourceId) {
		Toast.makeText(context, context.getResources().getString(resourceId), Toast.LENGTH_LONG).show();
	}
}
	
