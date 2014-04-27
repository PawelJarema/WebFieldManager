package web.field;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.helpers.Converter;
import web.field.model.entity.Customer;
import web.field.model.entity.CustomerAddress;
import web.field.model.entity.Order;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.User;
import web.field.model.simple.OrderTemplateSimple;
import web.field.viewmodel.OrderViewModel;
import web.field.viewmodel.ViewModelFactory;
import gueei.binding.Binder;
import gueei.binding.Binder.InflateResult;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

public class NewOrderBindingFragment extends WebFieldFragment {

	private Order order;
	private Customer customer;
	private CustomerAddress[] addresses;
	private List<OrderTemplateSimple> templates;

	private OrderViewModel orderViewModel;

	private IDBAdapter db;
	private int orderId;
	private ViewModelFactory factory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		InflateResult result = Binder.inflateView(getActivity(),
				R.layout.fragment_neworder_binding, null, false);

		db = new DBAdapter(getHelper());

		templates = db.listOrderTemplates();

		orderId = getArguments().getInt("order_id");
		if (orderId != 0) {
			order = db.getOrder(orderId);
		} else {
			order = new Order();
			order.setOrderTempId(UUID.randomUUID().toString());

			// set order date
			order.setOrderDate(Converter.dateToSeconds(new Date()));

			// set delivery date
			order.setDeliveryDate(Converter.dateToSeconds(new Date()));
		}

		long customerId = getArguments().getLong("customerId");
		this.customer = db.getCustomer((int) customerId);
		this.addresses = customer.getAddresses().toArray(
				new CustomerAddress[] {});

		order.setCustomer(customer);
		order.setBillTo(addresses[0]);
		order.setShipTo(addresses[0]);

		factory = new ViewModelFactory(getActivity());
		orderViewModel = factory.CreateOrderViewModel(order);

		setHasOptionsMenu(true);

		return Binder.bindView(getActivity(), result, orderViewModel);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.new_order, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_new_order_save) {
			registerForContextMenu(getView());
			getActivity().openContextMenu(getView());
			unregisterForContextMenu(getView());
		}
		return true;
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

		/*
		 * switch (v.getId()) { case R.id.order_bill_to:
		 * contextMenu.setHeaderTitle("Bill to:"); int addrBillToPos = 0;
		 * action_group_code = 0; for (CustomerAddress addr : addresses) {
		 * contextMenu.add(action_group_code, addr.getCustomerAddressId(),
		 * addrBillToPos, addr.fullAddress()); addrBillToPos++; }
		 * 
		 * break; case R.id.order_ship_to: int addrShipToPos = 0;
		 * action_group_code = 1; for (CustomerAddress addr : addresses) {
		 * contextMenu.add(action_group_code, addr.getCustomerAddressId(),
		 * addrShipToPos, addr.fullAddress()); addrShipToPos++; }
		 * contextMenu.setHeaderTitle("Ship to:");
		 * 
		 * break; case R.id.neworder_goto_add_product:
		 */
		int templatePos = 0;
		action_group_code = 108;
		for (OrderTemplateSimple template : templates) {
			contextMenu.add(action_group_code, template.getTemplateId(),
					templatePos, template.getTemplateName());
			templatePos++;
		}
		// }

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
			// tvShippingAddress.setText(address);
		} else if (action_group == 0) {
			address = addresses[position].fullAddress();
			// tvBillingAddress.setText(address);
		} else if (action_group == 108) {

			// set order data
			// String orderDetiveryDateStr =
			// etDeliveryDate.getText().toString();
			// try {
			// order.setDeliveryDate(Converter.stringDateToSeconds(orderDetiveryDateStr));
			// } catch (ParseException e) {
			// Toast.makeText(this.getActivity(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
			// e.printStackTrace();
			// }

			// set order template
			OrderTemplate template = db.getOrderTemplate(item.getItemId());
			order.setOrderTemplate(template);

			// set tenant to order
			SharedPreferences preferences = WebFieldApplication
					.getSharedPreferences();
			String token = preferences.getString(
					SharedPreferencesKeys.user_token, null);

			User currentUser = db.getUser(token);
			// TODO this doesn't work
			order.setTenantId(currentUser.getTenantId());
			// order.setTenantId(1); << stub

			// save order as draft and go to next step
			db.saveOrderFromTemplate(order, template);

			//Intent i = new Intent(getActivity(), AddProductsActivity.class);
			Intent i = new Intent(getActivity(), AddProductsBindingActivity.class);
			i.putExtra("templateId", template.getOrdersTemplateId());
			i.putExtra("orderTempId", order.getOrderTempId());
			startActivity(i);
		}

		return true;
	}

}
