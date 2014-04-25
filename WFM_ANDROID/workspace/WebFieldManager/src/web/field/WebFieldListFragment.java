package web.field;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public abstract class WebFieldListFragment extends ListFragment implements
		LoaderCallbacks<Void> {
	private OrmDbHelper databaseHelper = null;

	protected OrmDbHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					OrmDbHelper.class);
		}
		return databaseHelper;
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
