package web.field;

import java.util.List;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SortPickerFragment extends DialogFragment implements OnClickListener, OnItemClickListener {
	
	private Button cancelButton;
	private ListView lvSortPhrase;
	private List<String> sortPhraseList;
	
	private TextView sortControl;
	private SortPickerDialogListener dismissEvent;
	
	public interface SortPickerDialogListener
	{
		public void onSortDialogDismiss();
	}
	
	public SortPickerFragment(TextView control, List<String> sortPhraseList, 
			SortPickerDialogListener callback) {
		this.sortControl = control;
		this.sortPhraseList = sortPhraseList;
		this.dismissEvent = callback;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sort_phrase_picker_fragment, container, false);
		
		lvSortPhrase = (ListView) view.findViewById(R.id.sortPhrasePicker_PhraseList);
		lvSortPhrase.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				android.R.id.text1, sortPhraseList));
		lvSortPhrase.setOnItemClickListener(this);
		
		cancelButton = (Button) view.findViewById(R.id.sortPhrasePicker_OkButton);
		cancelButton.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		this.dismiss();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		sortControl.setText(sortPhraseList.get(position));
		// sortControl.setBackgroundColor(R.color.app_orange);
		dismissEvent.onSortDialogDismiss();
		this.dismiss();
	}
}
