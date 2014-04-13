package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.simple.CustomerSimple;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomersAdapter extends ArrayAdapter<CustomerSimple> {

	Context context;
	int layoutResourceId;
	List<CustomerSimple> data = new ArrayList<CustomerSimple>();

	public CustomersAdapter(Context context, int layoutResourceId,
			List<CustomerSimple> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CustomersHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new CustomersHolder();
			holder.tvName = (TextView) row.findViewById(R.id.customer_name);
			holder.tvId = (TextView) row.findViewById(R.id.customer_id);
			holder.tvVat = (TextView) row.findViewById(R.id.customer_vat);
			// holder.cbStatus = (CheckBox)
			// row.findViewById(R.id.customer_status);
			row.setTag(holder);
		} else {
			holder = (CustomersHolder) row.getTag();
		}
		CustomerSimple customer = data.get(position);
		holder.tvName.setText(customer.getCustomerName());
		holder.tvId.setText(String.valueOf(customer.getCustomerId()));
		holder.tvVat.setText(customer.getVatId());
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

	static class CustomersHolder {
		TextView tvId;
		TextView tvName;
		TextView tvVat;
		CheckBox cbStatus;
	}
}
