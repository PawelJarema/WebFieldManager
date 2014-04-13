package web.field;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.helpers.Converter;
import web.field.model.entity.*;
import web.field.model.simple.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewOrderFragment extends WebFieldFragment {

	private Order order;
	private Customer customer;
	private CustomerAddress[] addresses;
	private List<OrderTemplateSimple> templates;

	private IDBAdapter db;
	private int orderId;

	// TextViews
	ListView product_list;
	TextView tvCustomer;
	TextView tvOrderDate;
	TextView tvBillingAddress;
	TextView tvShippingAddress;
	
	EditText etDeliveryDate;
	EditText etComments;

	Button bBillTo;
	Button bShipTo;
	Button bSave;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_neworder, container,
				false);

		getRelatedDbData();
		populateTextViews(view);

		return view;
	}

	private void getRelatedDbData() {
		db = new DBAdapter(getHelper());
		orderId = getArguments().getInt("order_id");
		if (orderId != 0) {
			order = db.getOrder(orderId);
		} else {
			order = new Order();
			order.setOrderTempId(UUID.randomUUID().toString());
			
			// set order date
			order.setOrderDate(Converter.dateToSeconds(new Date()));
		}

		long customerId = getArguments().getLong("customerId");
		this.customer = db.getCustomer((int) customerId);
		this.addresses = customer.getAddresses().toArray(
				new CustomerAddress[] {});
		
		order.setCustomer(customer);
		order.setBillTo(addresses[0]);
		order.setShipTo(addresses[0]);
		templates = db.listOrderTemplates();
	}

	public void onResume() {
		super.onResume();

	}

	private void populateTextViews(View view) {

		product_list = (ListView) view.findViewById(R.id.order_product_list);

		// set customer data
		tvCustomer = (TextView) view.findViewById(R.id.order_customer);
		tvCustomer.setText(customer.getCustomerName());

		tvOrderDate = (TextView) view.findViewById(R.id.neworder_order_date);
		tvOrderDate.setText(Converter.secondsToDateString(order.getOrderDate()));

		// if customer has only one address, disable buttons
		tvBillingAddress = (TextView) view
				.findViewById(R.id.order_billing_address);
		tvShippingAddress = (TextView) view
				.findViewById(R.id.order_shipping_address);
		bBillTo = (Button) view.findViewById(R.id.order_bill_to);
		bShipTo = (Button) view.findViewById(R.id.order_ship_to);
		if (customer.getAddresses().size() < 2) {
			bBillTo.setEnabled(false);
			bShipTo.setEnabled(false);
		}
		tvBillingAddress.setText(addresses[0].fullAddress());
		tvShippingAddress.setText(addresses[0].fullAddress());

		etDeliveryDate = (EditText) view.findViewById(R.id.order_delivery_date);
		
				
		etComments = (EditText) view.findViewById(R.id.order_comments);

		bSave = (Button) view.findViewById(R.id.neworder_goto_add_product);

		/*
		 * OrderAdapter adapter = new OrderAdapter(getActivity(),
		 * R.layout.list_row_fragment_order_product, products); list_view =
		 * (ListView)view.findViewById(R.id.order_product_list);
		 * list_view.setAdapter(adapter);
		 */

		// TODO modify if necessary
		OnClickListener click_listener = new OnClickListener() {
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.order_bill_to:
				case R.id.order_ship_to:
				case R.id.neworder_goto_add_product:
				default:
					registerForContextMenu(v);
					getActivity().openContextMenu(v);
					break;
				}
				unregisterForContextMenu(v);
			}
		};
		bBillTo.setOnClickListener(click_listener);
		bShipTo.setOnClickListener(click_listener);
		bSave.setOnClickListener(click_listener);
	}

	@Override
	public void onCreateContextMenu(ContextMenu contextMenu, View v,
			ContextMenuInfo menuInfo) {

		OnMenuItemClickListener listener = new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				onContextItemSelected(item);
				return true;
			}
		};

		CustomerAddress[] addresses = customer.getAddresses().toArray(
				new CustomerAddress[] {});

		// sets up context menu
		int action_group_code = 108; // 0 = bill to; 1 = ship_to; 108 = select

		switch (v.getId()) {
		case R.id.order_bill_to:
			contextMenu.setHeaderTitle("Bill to:");
			int addrBillToPos = 0;
			action_group_code = 0;
			for (CustomerAddress addr : addresses) {
				contextMenu.add(action_group_code, addr.getCustomerAddressId(),
						addrBillToPos, addr.fullAddress());
				addrBillToPos++;
			}

			break;
		case R.id.order_ship_to:
			int addrShipToPos = 0;
			action_group_code = 1;
			for (CustomerAddress addr : addresses) {
				contextMenu.add(action_group_code, addr.getCustomerAddressId(),
						addrShipToPos, addr.fullAddress());
				addrShipToPos++;
			}
			contextMenu.setHeaderTitle("Ship to:");

			break;
		case R.id.neworder_goto_add_product:
			int templatePos = 0;
			action_group_code = 108;
			for (OrderTemplateSimple template : templates) {
				contextMenu.add(action_group_code, template.getTemplateId(),
						templatePos, template.getTemplateName());
				templatePos++;
			}
		}

		// sets context menu clicks to be handled from fragment, not activity
		for (int i = 0; i < contextMenu.size(); i++) {
			contextMenu.getItem(i).setOnMenuItemClickListener(listener);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int position = item.getOrder();
		int action_group = item.getGroupId(); // 0 = bill to; 1 = ship_to; 108 =
												// select template and +products
		String address = "";
		// update text field
		if (action_group == 1) {
			address = addresses[position].fullAddress();
			tvShippingAddress.setText(address);
		} else if (action_group == 0) {
			address = addresses[position].fullAddress();
			tvBillingAddress.setText(address);
		} else if (action_group == 108) {
			
			// set order data
			String orderDetiveryDateStr = etDeliveryDate.getText().toString();
			try {
				order.setDeliveryDate(Converter.stringDateToSeconds(orderDetiveryDateStr));
			} catch (ParseException e) {
				Toast.makeText(this.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			
			// set order template
			OrderTemplate template = db.getOrderTemplate(item.getItemId());
			order.setOrderTemplate(template);

			// set tenant to order
			SharedPreferences preferences = WebFieldApplication
					.getSharedPreferences();
			String token = preferences.getString(
					SharedPreferencesKeys.user_token, null);

			User currentUser = db.getUser(token);
			order.setTenantId(currentUser.getTenantId());

			// save order as draft and go to next step
			db.saveOrderFromTemplate(order, template);

			Intent i = new Intent(getActivity(), AddProductsActivity.class);
			i.putExtra("templateId", template.getOrdersTemplateId());
			i.putExtra("orderTempId", order.getOrderTempId());
			startActivity(i);
		}

		return true;
	}
}