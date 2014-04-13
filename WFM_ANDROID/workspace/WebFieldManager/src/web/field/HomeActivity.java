package web.field;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class HomeActivity extends FragmentActivity {
	// Nav drawer handling
	private DrawerLayout drawer_layout;
	private ListView drawer_list;
	private ActionBarDrawerToggle drawer_toggle;
	private CharSequence title;
	final String[] fragments ={
            "web.field.HomeFragment",
            "web.field.OrdersFragment",
            "web.field.HomeCustomersFragment",
            "web.field.ProductsFragment",
            "web.field.SettingsFragment"};
	

	// Ui elements, tab swipes fragment
	// private ViewPager view_pager;
	// private HomeTabsAdapter tabs_adapter;
	private ActionBar action_bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		action_bar = getActionBar();
		preapareNavDrawer();
		// if (savedInstanceState == null) {
		// getSupportFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment()).commit();
		// }

		DrawerListAdapter adapter = new DrawerListAdapter(this,
				R.layout.list_row_drawer);
		drawer_list.setAdapter(adapter);
		// go home
		selectItem(0);
	}

	protected void onResume() {
		super.onResume();
		drawer_toggle.setDrawerIndicatorEnabled(true);
	}

	public ActionBarDrawerToggle getDrawerToggle() {
		return drawer_toggle;
	}

	@SuppressLint("NewApi")
	private void preapareNavDrawer() {
		drawer_layout = (DrawerLayout) findViewById(R.id.drawer_menu);
		drawer_list = (ListView) findViewById(R.id.drawer_list);
		drawer_toggle = new ActionBarDrawerToggle(this, drawer_layout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				// action_bar.setTitle(R.string.home);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// action_bar.setTitle(R.string.drawer_title);
				invalidateOptionsMenu();
			}
		};
		drawer_layout.setDrawerListener(drawer_toggle);
		drawer_list.setOnItemClickListener(new DrawerClickListener());
		action_bar.setDisplayHomeAsUpEnabled(true);
		action_bar.setHomeButtonEnabled(true);
	}

	private void selectItem(int position) {
		// TODO code
		action_bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		String[] titles = getResources().getStringArray(R.array.nav_titles);
		Fragment fragment = Fragment.instantiate(HomeActivity.this, fragments[position]);
		action_bar.setTitle(titles[position]);	
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.replace(R.id.home_content_frame, fragment);
		tx.addToBackStack(null);
		tx.commit();
		
		drawer_layout.closeDrawers();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (drawer_toggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		//TODO here's the code for back button using back stack (fragment history) 
		switch (item.getItemId()) {
		case android.R.id.home:
			// TODO navigate to home fragment or to root activity
			drawer_toggle.setDrawerIndicatorEnabled(true);
			FragmentManager sfm = getSupportFragmentManager();
			if (sfm.getBackStackEntryCount() > 0){	
			    sfm.popBackStackImmediate();
			}
			return true;
		case R.id.action_orders:
			// startIntent(".Orders");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setTitle(CharSequence t) {
		action_bar.setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawer_toggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawer_toggle.onConfigurationChanged(newConfig);
	}

	private class DrawerClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawer_layout.isDrawerOpen(drawer_list);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container,
					false);
			return rootView;
		}
	}

}
