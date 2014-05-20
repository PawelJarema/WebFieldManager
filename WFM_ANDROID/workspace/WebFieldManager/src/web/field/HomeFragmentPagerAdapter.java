package web.field;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		// do NOT restore state
	}

	public HomeFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int index) {
		switch(index) {
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
