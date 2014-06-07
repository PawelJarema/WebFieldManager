package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.simple.CustomerSimple;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomersAdapter extends ArrayAdapter<CustomerSimple> {

	Context context;
	int layoutResourceId;
	List<CustomerSimple> data = new ArrayList<CustomerSimple>();
	static OnClickListener listButtonListener;

	public CustomersAdapter(Context context, int layoutResourceId,
			List<CustomerSimple> data, OnClickListener listButtonListener) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.listButtonListener = listButtonListener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CustomersHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new CustomersHolder();
			holder.tvId = (TextView) row.findViewById(R.id.customer_id);
			holder.tvClient = (TextView) row.findViewById(R.id.customer_client);
			holder.tvLocation = (TextView) row.findViewById(R.id.customer_location);
			
			holder.bQuickDetails = (Button) row.findViewById(R.id.customer_go_to_quick_details);
			holder.bQuickNewOrder = (Button) row.findViewById(R.id.customer_go_to_quick_new_order);
			holder.bQuickEmail = (Button) row.findViewById(R.id.customer_go_to_quick_email);
			holder.bGoToCustomer = (Button) row.findViewById(R.id.customer_go_to_customer);
			holder.setOnClickListeners();
			//holder.tvVat = (TextView) row.findViewById(R.id.customer_vat);
			
			// holder.cbStatus = (CheckBox)
			// row.findViewById(R.id.customer_status);
			row.setTag(holder);
		} else {
			holder = (CustomersHolder) row.getTag();
		}
		CustomerSimple customer = data.get(position);
		holder.tvClient.setText(customer.getCustomerName());
		holder.tvId.setText(takeFirst8CharsFrom(String.valueOf(customer.getCustomerId())));
		
		// IMPORTANT pass id to button tag for NEW ORDER action
		holder.bQuickNewOrder.setTag(customer.getCustomerId());
		//
		
		holder.centerText();
		//holder.tvVat.setText(customer.getVatId());
		// holder.cbStatus.setChecked(customer.isActive());
		
		// style list depending on position
				if (position % 2 == 0)
					row.setBackgroundColor(context.getResources().getColor(R.color.list_dark));
				else
					row.setBackgroundColor(context.getResources().getColor(R.color.list_light));

		return row;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public CustomerSimple getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return data.get(index).getCustomerId();
	}

	private String takeFirst8CharsFrom(String user) {
		if (user != null && user.length() > 8)
			return user.substring(0, 8);
		return user;
	}
	
	static class CustomersHolder {
		TextView tvId;
		TextView tvClient;
		TextView tvLocation;
		
		Button bQuickDetails;
		Button bQuickNewOrder;
		Button bQuickEmail;
		Button bGoToCustomer;
		
		public void centerText() {
			tvId.setGravity(Gravity.CENTER);
		}

		public void setOnClickListeners() {
			bQuickNewOrder.setOnClickListener(listButtonListener);			
		}
	}
}
