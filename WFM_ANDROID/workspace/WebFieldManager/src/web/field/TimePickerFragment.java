package web.field;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment 
	implements TimePickerDialog.OnTimeSetListener {
	
	public EditText et;
	
	public TimePickerFragment(EditText et) {
		this.et = et;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstenceState) {
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		return new TimePickerDialog(
				getActivity(), 
				this,
				hour,
				minute,
				DateFormat.is24HourFormat(getActivity()));
	}
	
	public void onTimeSet(TimePicker view, int hour, int minute) {
		String m = String.valueOf(minute);
		if (minute < 10)
			m = "0" + m;
		et.setText(String.valueOf(hour) + ":" + m);
	}
}