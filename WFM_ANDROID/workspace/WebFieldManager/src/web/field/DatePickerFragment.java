package web.field;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener {
	
	public EditText et;
	
	public DatePickerFragment(EditText et) {
		this.et = et;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstenceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(
				getActivity(), 
				this,
				year,
				month,
				day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		String m = String.valueOf(month);
		if (month < 10) 
			m = "0" + m;
		String d = String.valueOf(day);
		if (day < 10) 
			d = "0" + d;
		et.setText(String.valueOf(year) + "-" + m + "-" + d);
	}
}
