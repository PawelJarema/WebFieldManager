package web.field;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HomeTabsAdapter extends FragmentStatePagerAdapter {
	
	public HomeTabsAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new HomeCustomersFragment();
		case 1:
			return new HomeDraftOrdersFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}
}
