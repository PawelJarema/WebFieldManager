package web.field;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends WebFieldFragmentActivity {
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
	private SettingsActFragment settingsScreen;
	
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

	protected void onStart() {
		super.onStart();
		dismissProgressDialog();
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
		action_bar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
		String[] titles = getResources().getStringArray(R.array.nav_titles);
		// there's currently no Preference Fragment compatible with v4 support Fragment
		// prefs at position 4 must be run from old FragmentManager
		if (position < 4) {
			Fragment fragment = Fragment.instantiate(HomeActivity.this, fragments[position]);
			action_bar.setTitle(titles[position]);	
			FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
			tx.replace(R.id.home_content_frame, fragment);
			tx.addToBackStack(null);
			tx.commit();
		} else {
			Intent settings = new Intent("web.field.SettingsAct");
			startActivity(settings);
			//settingsScreen = new SettingsActFragment();
			//getSupportFragmentManager().beginTransaction().replace(R.id.home_content_frame, new Fragment()).addToBackStack(null).commit();
			//getFragmentManager().beginTransaction().add(R.id.home_content_frame, settingsScreen).addToBackStack("show_settings").commit();
			//settingsScreen.show(settingsScreen); 
		}
		
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
		
		switch (item.getItemId()) {
			case android.R.id.home:
				drawer_toggle.setDrawerIndicatorEnabled(true);
				FragmentManager sfm = getSupportFragmentManager();
				
				if (sfm.getBackStackEntryCount() > 0 && displayFragmentUIMessages(sfm))	
				    sfm.popBackStackImmediate();
				
				return true;
			case R.id.action_orders:
				// startIntent(".Orders");
				return true;
			case R.id.action_logout:
				//AlertDialog
				showYesNoDialog(getResources().getString(R.string.do_you_want_to_log_out),
						dialogClickListener);
				/*AlertDialog.Builder builder = new Builder(this);
				builder.setMessage(getResources().getString(R.string.do_you_want_to_log_out));
				builder.setPositiveButton(getResources().getString(R.string.yes), dialogClickListener);
				builder.setNegativeButton(getResources().getString(R.string.no), dialogClickListener).show();*/
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// displays messages for users on attempt to leave important fragments
	private boolean displayFragmentUIMessages(FragmentManager sfm) {
		if (sfm.getBackStackEntryCount() < 1)
			return true;
		FragmentManager.BackStackEntry backstackEntry = sfm.getBackStackEntryAt(
				sfm.getBackStackEntryCount() - 1);
		if (backstackEntry.toString().contains("displayUIMsgOnBack")) {
			showYesNoDialog(getResources().getString(R.string.do_you_want_to_leave_order),
					null);
			return false;
		}
		else
			return true;
	}

	@Override
	public void setTitle(CharSequence t) {
		action_bar.setTitle(title);
	}

	@Override
	public void onBackPressed() {
		if (displayFragmentUIMessages(getSupportFragmentManager())) {
			getFragmentManager().popBackStack();		
			super.onBackPressed();
		}
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
	
	// Dialog that handles logout request
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		        	Intent login = new Intent("web.field.LoginActivity");
					login.putExtra("logout", true);
					startActivity(login);
					dialog.dismiss();
		            break;
		        case DialogInterface.BUTTON_NEGATIVE:
		            dialog.dismiss();
		            break;
	        }
	    }
	};
}
