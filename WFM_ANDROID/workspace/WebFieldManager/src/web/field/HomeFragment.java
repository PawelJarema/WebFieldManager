package web.field;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends WebFieldFragment implements
		ActionBar.TabListener {

	private ViewPager view_pager;
	private HomeTabsAdapter tabs_adapter;
	private ActionBar action_bar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		action_bar = getActivity().getActionBar();
		action_bar.removeAllTabs();
	}

	@Override
	public void onResume() {
		super.onResume();
		action_bar.removeAllTabs();
		setSwipeTabs(getView());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		setSwipeTabs(view);
		return view;
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		view_pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		view_pager.setCurrentItem(tab.getPosition());
	}

	private void setSwipeTabs(View view) {
		view_pager = (ViewPager) view.findViewById(R.id.home_pager);
		tabs_adapter = new HomeTabsAdapter(getFragmentManager());
		view_pager.setAdapter(tabs_adapter);
		view_pager
			.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						action_bar.setSelectedNavigationItem(position);
					}

					@Override
					public void onPageScrolled(int arg0, float arg1,
							int arg2) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub
					}
			});
			

		// add tabs to pager
		String[] tabs = getResources().getStringArray(R.array.home_tabs);
		for (String name : tabs) {
			action_bar.addTab(action_bar.newTab().setText(name)
					.setTabListener(this));
		}
		
		action_bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabs_adapter.notifyDataSetChanged();
	}
}
