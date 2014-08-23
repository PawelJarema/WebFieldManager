package web.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import web.field.SortPickerFragment.SortPickerDialogListener;
import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.helpers.DataHelpers;
import web.field.model.entity.Product;
import web.field.model.entity.adapter.ProductModelAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class ProductsFragment extends WebFieldListFragment implements SortPickerDialogListener,
	OnClickListener, OnQueryTextListener {
	
	private ProductsArrayAdapter adapter;
	private List<ProductModelAdapter> data;
	private List<ProductModelAdapter> dataTmp;
	private IDBAdapter db;
	
	private LinearLayout moreData; // cashing expanding drawer in row 
	private TextView sortProductsByManufacturer;
	private TextView sortProductsByBrand;
	private TextView sortProductsByFamily;
	private TextView sortProductsByCategory;
	
	// sort panel data
	private List<String> manufacturers;
	private List<String> brands;
	private List<String> families;
	private List<String> categories;
	
	private boolean loaderIsRunning = false;
	private boolean searchQueryInProgress = false;
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.logout_with_search, menu);
		
		// enable search widget
		SearchView searchField= (SearchView) menu.findItem(R.id.searchField).getActionView();
		searchField.setOnQueryTextListener(this);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		db = new DBAdapter(getHelper(), getTenantProvider());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// add custom fragment view
		View view =  inflater.inflate(R.layout.fragment_products, container, false);
		castViewIDs(view);
		
		// add sort panel functionality
		sortProductsByManufacturer = (TextView) view.findViewById(R.id.sortProductsByManufacturer);
		sortProductsByBrand = (TextView) view.findViewById(R.id.sortProductsByBrand);
		sortProductsByFamily = (TextView) view.findViewById(R.id.sortProductsByFamily);
		sortProductsByCategory = (TextView) view.findViewById(R.id.sortProductsByCategory);
		sortProductsByManufacturer.setOnClickListener(this); // listener is implemented in this fragment
		sortProductsByBrand.setOnClickListener(this);
		sortProductsByFamily.setOnClickListener(this);
		sortProductsByCategory.setOnClickListener(this);
		
		return view;
	}
	
	// method is needed to make list fragment helpers work with custom layout
	private static void castViewIDs(View view) {
		view.findViewById(R.id.productsListContainerId).setId(INTERNAL_LIST_CONTAINER_ID);
		view.findViewById(R.id.productsProgressContainerId).setId(INTERNAL_PROGRESS_CONTAINER_ID);
	}
		
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
		// prepare data for sort panel
		prepareDataForSortPanel();
		
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
				View rowView = adapter.getView(position, view, parent);
				Drawable rememberedListRowColor = rowView.getBackground();
				rowView.setBackgroundResource(R.color.holo_blue);
				
				// TODO for now: instead of inflating new screen to show more product data
				// use expanding row like on iOS
				if (moreData != null && moreData.getVisibility() == View.VISIBLE)
					moreData.setVisibility(View.GONE);
				else {
					moreData = (LinearLayout) rowView.findViewById(R.id.productsExpandingContainer);
					moreData.setVisibility(View.VISIBLE);
				}
				
				rowView.setBackground(rememberedListRowColor);
				
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
				loaderIsRunning = true;
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
		loaderIsRunning = false;
	}

	@Override
	public void onLoaderReset(Loader<Void> arg0) {
		
	}
	
	// sort panel click handling
	@Override
	public void onClick(View v) {
		if (adapter == null)
			return;
		
		// just sort
		// adapter.sortDataBy(v.getId()); 
		
		// as filters
		List<String> sortByList = null;
		switch(v.getId())
		{
			case R.id.sortProductsByManufacturer:
				sortByList = manufacturers;
				break;
			case R.id.sortProductsByBrand:
				sortByList = brands;
				break;
			case R.id.sortProductsByFamily:
				sortByList = families;
				break;
			case R.id.sortProductsByCategory:
				sortByList = categories;
		}
		SortPickerFragment sortDialog = new SortPickerFragment((TextView) v, sortByList, this);
		sortDialog.show(getActivity().getSupportFragmentManager(), null);
	
		// set filters in adapter - callback onSortDialogDismiss
	}
	
	private void prepareDataForSortPanel() {
		DataHelpers helper = new DataHelpers();
		
		manufacturers = helper.getProductManufacturerNames(db);
		brands = helper.getProductBrandNames(db);
		families = helper.getProductFamilyNames(db);
		categories = helper.getProductCategoryNames(db);
	}
	
	private void filterData(List<ProductModelAdapter> entryData) {
		String manufacturer = sortProductsByManufacturer.getText().toString();
		String brand = sortProductsByBrand.getText().toString();
		String family = sortProductsByFamily.getText().toString();
		String category = sortProductsByCategory.getText().toString();
		if (!manufacturers.contains(manufacturer))
			manufacturer = "";
		if (!brands.contains(brand))
			brand = "";
		if (!families.contains(family))
			family = "";
		if (!categories.contains(category))
			category = "";
		if (entryData == null)
			adapter.applyDataFilters(manufacturer, brand, family, category);
		else
			adapter.applyDataFilters(manufacturer, brand, family, category, entryData);
	}
	
	// sort dialog listener / callback
	@Override
	public void onSortDialogDismiss() {
		filterData(null);
	}

	// individual methods for search field 
	@Override
	public boolean onQueryTextSubmit(String query) {
		showSearchResultsOnList(query);
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		
		if (newText.length() > 0)
			showSearchResultsOnList(newText);
		if (newText.length() == 0)
			showAllDataOnList();
		
		return false;
	}
	
	private void showSearchResultsOnList(String query)
	{
		searchQueryInProgress = true;
		List<ProductModelAdapter> dataCopy = new ArrayList<ProductModelAdapter>();
		
		for (ProductModelAdapter product : data)
		{
			if (product.getProductDescription().toLowerCase()
					.contains(query.toLowerCase()))
					{
						dataCopy.add(product.MakeDeepCopy());
					}
		}

		adapter.setSeenData(dataCopy);
		filterData(dataCopy);
		searchQueryInProgress = false;
	}
	
	private void showAllDataOnList()
	{
		adapter.setSeenData(data);
		filterData(data);
		searchQueryInProgress = false;
	}
}
