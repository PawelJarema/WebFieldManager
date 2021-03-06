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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HomeDraftOrdersFragment extends WebFieldListFragment {
	private List<OrderSimple> data;
	private IDBAdapter db;
	private OrdersAdapter adapter;

	private static final int INTERNAL_PROGRESS_CONTAINER_ID = 0x00ff0002;
	private static final int INTERNAL_LIST_CONTAINER_ID = 0x00ff0003;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_orders, container, false);
		castViewIDs(view);
		return view;
	}

	// method is needed to make list fragment helpers work with custom layout
	public static void castViewIDs(View view) {
		view.findViewById(R.id.ordersListContainerId).setId(INTERNAL_LIST_CONTAINER_ID);
		view.findViewById(R.id.ordersProgressContainerId).setId(INTERNAL_PROGRESS_CONTAINER_ID);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(false);

		// you only need to instantiate these the first time your fragment is
		// created; then, the method above will do the rest
		if (adapter == null) {
			data = new ArrayList<OrderSimple>();
			adapter = new OrdersAdapter(getActivity(),
					R.layout.list_row_fragment_orders, data);
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
				db = new DBAdapter(getHelper(), getTenantProvider());
				
				// TODO:, only draft orders
				data = db.listDraftOrders(0);
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
		arg0.reset();
	}
}
