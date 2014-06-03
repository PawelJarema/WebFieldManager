package web.field;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductFragment extends WebFieldFragment {

	private ImageView product_picture;
	private TextView product_breadcrumbs;
	private TextView product_price;
	private TextView product_name;
	private IDBAdapter db;
	
	private int product_id;
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		menu.clear();
	    super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_product, container, false); 
		product_id = getArguments().getInt("product_id");
		
		getDbData();
		product_picture = (ImageView) view.findViewById(R.id.product_image);
		product_breadcrumbs = (TextView) view.findViewById(R.id.product_breadcrumbs);
		product_price = (TextView) view.findViewById(R.id.product_price);
		product_name = (TextView) view.findViewById(R.id.product_name);
		
		product_picture.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.img_barney));
		product_name.setText("Banrey the Dinosaur");
		product_price.setText("350\u20AC");
		product_breadcrumbs.setText("OPP > Product Family > Manufactured > Brand");
		
		return view;
	}
	
	private void getDbData() {
		db = new DBAdapter(getHelper(), getTenantProvider());
		//TODO get product details and store in variable (image, name, price, breadcrumbs)...
	}
}
