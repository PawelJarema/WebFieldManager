package web.field;

import android.app.ProgressDialog;
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
}
