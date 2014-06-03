package web.field;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Customer;
import web.field.model.entity.CustomerAddress;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.ForeignCollection;

public class CustomerFragment extends WebFieldFragment {
	private ListView list_view;
	private int customer_id;
	private Customer customer;
	private IDBAdapter db;
	
	// Data
	private ForeignCollection<CustomerAddress> customer_addresses;
	
	// Views
	private TextView customer_email;
	private TextView customer_phone1;
	private TextView customer_phone2;
	private TextView customer_fax;
	private TextView customer_vat;
	private TextView customer_bill_to;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_customer, container, false);
		getArguments().getInt("customer_id");
		getDbData();
		populateViews(view);
	
		return view;
	}
	
	private void getDbData() {
		db = new DBAdapter(getHelper(), getTenantProvider());
		customer = db.getCustomer(customer_id);
	}
	
	private void populateViews(View view) {
		customer_email = (TextView) view.findViewById(R.id.customer_email);
		customer_phone1 = (TextView) view.findViewById(R.id.customer_phone1);
		customer_phone2 = (TextView) view.findViewById(R.id.customer_phone2);
		customer_fax = (TextView) view.findViewById(R.id.customer_fax);
		customer_vat = (TextView) view.findViewById(R.id.customer_vat);
		customer_bill_to = (TextView) view.findViewById(R.id.customer_bill_to);
		
		// TODO supply data
		customer_email.setText("email");
		customer_phone1.setText("phone1");
		customer_phone2.setText("phone2");
		// customer_vat.setText(customer.getVatId());
		// get address with ship to flag
		// customer_bill_to.setText(customer.getCustomerName());
		// customer_addresses = customer.getAddresses();
		
		// list
		//AddressAdapter adapter = new AddressAdapter(getActivity(), R.layout.list_row_fragment_customer_address, customer_addresses);
		//list_view = (ListView)view.findViewById(R.id.customer_address_list);
		//list_view.setAdapter(adapter);
	}
}
