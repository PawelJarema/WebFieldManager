package web.field;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class QtyPickerFragment extends DialogFragment implements
		OnClickListener {

	// Ui keyboard
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	private Button btn0;
	private Button btnDel;

	EditText etQty;
	int position;
	int qty;

	private OnCompleteListener on_complete_listener;

	public static interface OnCompleteListener {
		public abstract void onComplete(int position, int qty);
	}

	public QtyPickerFragment() {
		this.qty = 0;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.on_complete_listener = (OnCompleteListener) activity;
		} catch (Exception e) {
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.qty_picker_fragment, container,
				false);
		getDialog().setTitle(getResources().getString(R.string.set_quantity));
		getDialog().setCanceledOnTouchOutside(false);
		this.position = getArguments().getInt("position");
		int previous_qty = getArguments().getInt("qty"); 
		etQty = (EditText) view.findViewById(R.id.qty_edit);
		etQty.setFocusable(false); // edit text worked via visual keyboard
		if (previous_qty > 0)
			etQty.setText(String.valueOf(previous_qty));
		Button btnOk = (Button) view.findViewById(R.id.qty_ok);
		btnOk.setOnClickListener(this);

		// Ui keyboard
		btn1 = (Button) view.findViewById(R.id.btnOne);
		btn2 = (Button) view.findViewById(R.id.btnTwo);
		btn3 = (Button) view.findViewById(R.id.btnThree);
		btn4 = (Button) view.findViewById(R.id.btnFour);
		btn5 = (Button) view.findViewById(R.id.btnFive);
		btn6 = (Button) view.findViewById(R.id.btnSix);
		btn7 = (Button) view.findViewById(R.id.btnSeven);
		btn8 = (Button) view.findViewById(R.id.btnEight);
		btn9 = (Button) view.findViewById(R.id.btnNine);
		btn0 = (Button) view.findViewById(R.id.btnZero);
		btnDel = (Button) view.findViewById(R.id.btnDelete);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn0.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		return view;
	}

	public void onClick(View v) {
		qty = 0;
		switch (v.getId()) {
		case R.id.qty_ok:
			try {
				qty = Integer.parseInt(etQty.getText().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// activity callback
			on_complete_listener.onComplete(position, qty);
			dismiss();
			break;
		case R.id.btnDelete:
			deleteNumberFromEditText();
			break;
		// buttons
		default:
			char digit = ((Button) v).getText().toString().charAt(0);
			addNumberToEditText(digit);
		}
	}

	private void addNumberToEditText(char digit) {
		String current_text = etQty.getText().toString();
		etQty.setText(current_text + digit);
	}

	private void deleteNumberFromEditText() {
		String current_text = etQty.getText().toString();
		int length = current_text.length();
		if (length > 0)
			etQty.setText(current_text.substring(0, length - 1));
	}
}
