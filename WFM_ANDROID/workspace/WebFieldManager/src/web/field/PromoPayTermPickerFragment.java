package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.entity.PromoPayTermDetail;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class PromoPayTermPickerFragment extends DialogFragment implements OnItemClickListener {
	
	private List<PromoPayTermDetail> promoPayTermDetailList;
	private PromoPayTermDetail promoPayTermDetail;
	private String logMessage;
	
	private ListView payTermList;
	private Button bCancel;
	
	public PromoPayTermPickerFragment(List<PromoPayTermDetail> choices, PromoPayTermDetail callbackVariable) {
		this.promoPayTermDetailList = choices;
		this.promoPayTermDetail = callbackVariable;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Set title
		getDialog().setTitle(getActivity().getResources().getString(R.string.pick_promo_pay_term_details));
		
		// get view and list reference
		View view = inflater.inflate(R.layout.promo_pay_term_picker_fragment, container, false);
		payTermList = (ListView) view.findViewById(R.id.picker_pay_term_list);
		
		// simplify model to string descriptions (presented to user in selection list)
		List<String> payTermDescriptions = new ArrayList<String>();
		logMessage = "no data found in given promo list!";
		for (PromoPayTermDetail detail : promoPayTermDetailList) {
			payTermDescriptions.add(detail.getDescription());
			if (detail != null)
				logMessage = detail.getDescription();
			// TODO zmienne st¹d rzuaj¹ null :O
			Log.e("promo pay term detail picker", logMessage.toString());
		}
		
		// List
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_expandable_list_item_1, 
				android.R.id.text1, payTermDescriptions);
		
		payTermList.setAdapter(adapter);
		payTermList.setOnItemClickListener(this);
		
		// Cancel button
		bCancel = (Button) view.findViewById(R.id.pay_term_list_cancel_button);
		bCancel.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				dismiss();	
			}
		});
		
		return view;
	}
	
	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onDismiss(dialog);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		promoPayTermDetail = promoPayTermDetailList.get(position); // set callback variable to picked value
		this.dismiss();
	}
		
}
