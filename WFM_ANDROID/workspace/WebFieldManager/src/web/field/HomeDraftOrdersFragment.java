package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.simple.OrderSimple;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HomeDraftOrdersFragment extends WebFieldListFragment {
	private List<OrderSimple> data;
	private IDBAdapter db;
	private DraftOrdersAdapter adapter;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.logout, menu);	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(false);

		// you only need to instantiate these the first time your fragment is
		// created; then, the method above will do the rest
		if (adapter == null) {
			data = new ArrayList<OrderSimple>();
			adapter = new DraftOrdersAdapter(getActivity(),
					R.layout.list_row_home_draft_orders, data);
		}
		getListView().setAdapter(adapter);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				view = parent.getChildAt(position);
				view.setBackgroundResource(R.color.holo_blue);
				getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
				FragmentManager fm = getActivity().getSupportFragmentManager();
				// TODO pass id instead of position if necessary
				Fragment fragment = new OrderFragment();
				Bundle bundle = new Bundle();
				bundle.putInt("order_id", position);
				bundle.putInt(WebFieldFragment.DRAWER_FRAGMENT_PROPERTY, WebFieldFragment.SUBLEVELNAV);
				fragment.setArguments(bundle);
				fm.beginTransaction()
					.replace(R.id.home_content_frame, fragment)
					.addToBackStack("subLevel1")
					.commit();
			}
		});

		getLoaderManager().initLoader(1, null, this);
	}
	
	@Override
	public Loader<Void> onCreateLoader(int arg0, Bundle arg1) {
		AsyncTaskLoader<Void> loader = new AsyncTaskLoader<Void>(getActivity()) {
			
			@Override
			public Void loadInBackground() {
				Log.e("In Draft Order", "Async Task Started. Loading Orders data.");
				db = new DBAdapter(getHelper());
				
				// TODO:, only draft orders
				data = db.listOrders(0);
				return null;
			}
		};
		// somehow the AsyncTaskLoader doesn't want to start its job without
		// calling this method
		loader.forceLoad();
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Void> arg0, Void arg1) {
		Log.e("In Draft Order", "Async Task Finished. Orders data should be ready.");
		adapter.clear();
		adapter.addAll(data);
		// The list should now be shown.
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<Void> arg0) {
		Log.e("In Draft Order", "Resetting loader.");
	}
}
