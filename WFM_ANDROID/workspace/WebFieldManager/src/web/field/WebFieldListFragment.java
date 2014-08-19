package web.field;

import java.text.DecimalFormat;
import java.util.Currency;

import web.field.helpers.ITenantProvider;
import web.field.helpers.TenantProvider;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.view.View;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public abstract class WebFieldListFragment extends ListFragment implements
		LoaderCallbacks<Void> {
	
	private OrmDbHelper databaseHelper = null;
	protected static final int INTERNAL_PROGRESS_CONTAINER_ID = 0x00ff0002;
	protected static final int INTERNAL_LIST_CONTAINER_ID = 0x00ff0003;
	
	protected OrmDbHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					OrmDbHelper.class);
		}
		return databaseHelper;
	}
	
	protected ITenantProvider getTenantProvider() {
		return new TenantProvider(getHelper());
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}
