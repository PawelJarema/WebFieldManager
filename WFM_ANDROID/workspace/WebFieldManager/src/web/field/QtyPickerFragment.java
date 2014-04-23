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

public class QtyPickerFragment extends DialogFragment implements OnClickListener {

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
			this.on_complete_listener = (OnCompleteListener)activity;
		}
		catch (Exception e) { }		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.qty_picker_fragment, container, false);
		getDialog().setTitle(getResources().getString(R.string.set_quantity));
		getDialog().setCanceledOnTouchOutside(false);
		this.position = getArguments().getInt("position");
		
		etQty = (EditText) view.findViewById(R.id.qty_edit);
		Button btnOk = (Button) view.findViewById(R.id.qty_ok);
		btnOk.setOnClickListener(this);
		
		return view;
	}
	
	
	public void onClick(View v) {
		qty = 0;
		switch(v.getId()) {
			case R.id.qty_ok:
				try { qty = Integer.parseInt(etQty.getText().toString()); }
				catch(Exception e) { };
				//activity callback
				on_complete_listener.onComplete(position, qty);	
				break;
			default:
		}
		dismiss();
	}
}
