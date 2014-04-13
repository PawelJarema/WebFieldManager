package web.field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class WebFieldFragment extends Fragment {

	public static String DRAWER_FRAGMENT_PROPERTY = "fragment_nav_level";
	public static int SUBLEVELNAV = 1;
	public static int MAINLEVELNAV = 0;
	
    private OrmDbHelper  databaseHelper = null;

    protected OrmDbHelper  getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                OpenHelperManager.getHelper(getActivity(), OrmDbHelper .class);
        }
        return databaseHelper;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setRetainInstance(true);
    	checkNavigationLevelAndSetBackButton();
    }
   
    private void runAnotherFragment() {
    	
    }
    
    private void checkNavigationLevelAndSetBackButton() {
    	Bundle arguments = getArguments();
	    if (arguments != null && arguments.getInt("fragment_nav_level") == 1) {
	    	((HomeActivity)getActivity()).getDrawerToggle().setDrawerIndicatorEnabled(false);			
	    }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
