package web.field;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class WebfieldFragmentActivityInner extends FragmentActivity {

	private ProgressDialog progressDialog = null; 
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		showProgressDialog();
	}
	
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
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage(message);
		builder.setPositiveButton(getResources().getString(R.string.yes), listener);
		builder.setNegativeButton(getResources().getString(R.string.no), listener).show();
	}
}
