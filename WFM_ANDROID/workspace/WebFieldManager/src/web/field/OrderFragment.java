package web.field;

import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Customer;
import web.field.model.entity.Order;
import web.field.model.simple.ProductSimple;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class OrderFragment extends WebFieldFragment {
	
	private ListView list_view;
	// order data
	private Order order;
	private Customer customer;
	private List<ProductSimple> products;
	
	private IDBAdapter db;
	private int order_id;
	
	// TextViews
	ListView product_list;
	TextView customer_name;
	TextView order_date;
	TextView delivery_date;
	TextView billing_address;
	TextView shipping_address;
	TextView comments;
	TextView total_products;
	TextView discount;
	TextView total_value;
		
	// the two methods below are actually needed to clear menu; any other way ?
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		order_id = getArguments().getInt("order_id");
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		
		getRelatedDbData();
		populateTextViews(view);
		

		return view;
	}	
	
	private void getRelatedDbData() {
		db = new DBAdapter(getHelper());
		order = db.getOrder(order_id);
		// TODO get related customer and product data
		// customer = order.getCustomer();
		products = db.listProducts();
	}
	
	private void populateTextViews(View view) {
		product_list = (ListView)view.findViewById(R.id.order_product_list);
		customer_name = (TextView)view.findViewById(R.id.order_customer);
		order_date = (TextView)view.findViewById(R.id.order_date);
		delivery_date = (TextView)view.findViewById(R.id.order_delivery_date);
		billing_address = (TextView)view.findViewById(R.id.order_billing_address);
		shipping_address = (TextView)view.findViewById(R.id.order_ship_to);
		comments = (TextView)view.findViewById(R.id.order_comments);
		total_products = (TextView)view.findViewById(R.id.order_total_products);
		discount = (TextView)view.findViewById(R.id.order_discount);
		total_value = (TextView)view.findViewById(R.id.order_value);
		
		OrderAdapter adapter = new OrderAdapter(getActivity(), R.layout.list_row_fragment_order_product, products);
		list_view = (ListView)view.findViewById(R.id.order_product_list);
		list_view.setAdapter(adapter);
	}
}

