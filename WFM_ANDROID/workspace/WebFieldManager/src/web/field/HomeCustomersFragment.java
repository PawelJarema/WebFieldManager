package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.simple.CustomerSimple;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class HomeCustomersFragment extends WebFieldListFragment {

	int uniqueContextMenuId = 11111;

	private static ListView lv;
	private List<CustomerSimple> data;
	private IDBAdapter db;
	private CustomersAdapter adapter;
	
	@Override
	public void onViewStateRestored(Bundle bundle) {
		super.onViewStateRestored(bundle);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(false);

		// you only need to instantiate these the first time your fragment is
		// created; then, the method above will do the rest
		if (adapter == null) {
			data = new ArrayList<CustomerSimple>();
			adapter = new CustomersAdapter(getActivity(),
					R.layout.list_row_home_customers, data);
		}
		
		getListView().setAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO check if id corresponds to position
				view = parent.getChildAt(position);
				view.setBackgroundResource(R.color.holo_blue);
				
				getActivity().getActionBar().setNavigationMode(
						ActionBar.NAVIGATION_MODE_STANDARD);
				FragmentManager fragmentManager = getActivity()
						.getSupportFragmentManager();
				Fragment fragment = new NewOrderFragment();
				Bundle bundle = new Bundle();
				bundle.putLong("customerId", id);
				bundle.putInt(WebFieldFragment.DRAWER_FRAGMENT_PROPERTY, WebFieldFragment.SUBLEVELNAV);
				fragment.setArguments(bundle);
				fragmentManager.beginTransaction()
						.replace(R.id.home_content_frame, fragment)
						.addToBackStack("subLevel1")
						.commit();
			}

		}); 
		// initiate the loader to do the background work
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onCreateContextMenu(ContextMenu contextMenu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == getListView().getId()) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			contextMenu.setHeaderTitle(data.get(info.position)
					.getCustomerName());
			String[] subitems = getResources().getStringArray(
					R.array.customers_context_menu);
			for (int i = 0; i < subitems.length; i++) {
				contextMenu.add(uniqueContextMenuId, i, i, subitems[i]);
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getGroupId() == uniqueContextMenuId) {
			getActivity().getActionBar().removeAllTabs();
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
					.getMenuInfo();
			CustomerSimple listItem = data.get(info.position);
			int id = listItem.getCustomerId();
			FragmentManager fragmentManager = getActivity()
					.getSupportFragmentManager();
			Bundle bundle = new Bundle();
			bundle.putInt("customer_id", id);
			bundle.putInt(WebFieldFragment.DRAWER_FRAGMENT_PROPERTY, WebFieldFragment.SUBLEVELNAV);
			Fragment fragment = new Fragment();
			switch (item.getItemId()) {
			case 0:
				// assign position
				return false;
			case 1:
				// customer details
				fragment = new CustomerFragment();
				break;
			case 2:
				// new order
				fragment = new NewOrderFragment();
				break;
			default:
				break;
			}
			fragment.setArguments(bundle);
			fragmentManager.beginTransaction()
					.replace(R.id.home_content_frame, fragment)
					.addToBackStack("subLevel1")
					.commit();
		}

		return true;
	}

	@Override
	public Loader<Void> onCreateLoader(int arg0, Bundle arg1) {
		AsyncTaskLoader<Void> loader = new AsyncTaskLoader<Void>(getActivity()) {
			@Override
			public Void loadInBackground() {
				// TODO: use localization services
				double latitude = 0;
				double longitude = 0;

				db = new DBAdapter(getHelper());
				data = db.listCustomersNearMe(latitude, longitude);

				return null;
			}
		};
		// somehow the AsyncTaskLoader doesn't want to start its job without
		// calling this method
		loader.forceLoad();
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Void> loader, Void arg1) {
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
		// TODO Auto-generated method stub

	}
}
