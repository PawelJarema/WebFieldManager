package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Product;
import web.field.model.entity.adapter.ProductModelAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

public class ProductsFragment extends WebFieldListFragment {
	
	private ProductsArrayAdapter adapter;
	private List<ProductModelAdapter> data;
	private List<ProductModelAdapter> dataTmp;
	private IDBAdapter db;
	
	private LinearLayout moreData; // cashing expanding drawer in row 
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.logout_with_search, menu);	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);

		// you only need to instantiate these the first time your fragment is
		// created; then, the method above will do the rest
		if (adapter == null) {
			data = new ArrayList<ProductModelAdapter>();
			adapter = new ProductsArrayAdapter(getActivity(),
					R.layout.list_row_fragment_order_product, data);
		}
		getListView().setAdapter(adapter);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO check if id corresponds to position
				view = parent.getChildAt(position);
				Drawable rememberedListRowColor = view.getBackground();
				view.setBackgroundResource(R.color.holo_blue);
				
				// TODO for now: instead of inflating new screen to show more product data
				// use expanding row like on iOS
				if (moreData != null && moreData.getVisibility() == View.VISIBLE)
					moreData.setVisibility(View.GONE);
				else {
					moreData = (LinearLayout) view.findViewById(R.id.productsExpandingContainer);
					moreData.setVisibility(View.VISIBLE);
				}
				
				view.setBackground(rememberedListRowColor);
				
				/* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				Fragment fragment = new ProductFragment();
				Bundle bundle = new Bundle();
				bundle.putInt("productr_id", position);
				bundle.putInt("fragment_nav_level", WebFieldFragment.SUBLEVELNAV);
				fragment.setArguments(bundle);
				fragmentManager.beginTransaction()
					.replace(R.id.home_content_frame, fragment)
					.addToBackStack("subLevel1")
					.commit(); */
			}
		});

		// initiate the loader to do the background work
		getLoaderManager().initLoader(0, null, this);
	}
	

	@Override
	public Loader<Void> onCreateLoader(int arg0, Bundle arg1) {
		AsyncTaskLoader<Void> loader = new AsyncTaskLoader<Void>(getActivity()) {
			@Override
			public Void loadInBackground() {
				db = new DBAdapter(getHelper(), getTenantProvider());
				List<Product> products = db.listProductsFull();
				dataTmp = new ArrayList<ProductModelAdapter>();
				for(Product product : products){
					ProductModelAdapter adapter = new ProductModelAdapter(product);
					dataTmp.add(adapter);
					
					//find promotions
					
				}
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
		adapter.clear();
		adapter.addAll(dataTmp);
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
