package web.field;

import java.util.List;

import web.field.model.entity.CustomerAddress;
import web.field.model.simple.ProductSimple;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.dao.ForeignCollection;

public class AddressAdapter extends ArrayAdapter<CustomerAddress> {

	Context context;
	int layoutResourceId;
	ForeignCollection<CustomerAddress> data;

	public AddressAdapter(Context context, int layoutResourceId,
			ForeignCollection<CustomerAddress> data) {
		super(context, layoutResourceId);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		OrderHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new OrderHolder();
			
			holder.tvAddressId = (TextView) row.findViewById(R.id.order_product_id);
			holder.tvAddressDetails = (TextView) row.findViewById(R.id.order_product_description);
			holder.tvAddressCodeAndCity = (TextView) row.findViewById(R.id.order_product_number);
			
			row.setTag(holder);
		} else {
			holder = (OrderHolder) row.getTag();
		}
		CustomerAddress address = ((List<CustomerAddress>) data).get(position);
		holder.tvAddressId.setText(String.valueOf(address.getCustomerAddressId()));
		holder.tvAddressDetails.setText(address.getCustomerAddressDescription());
		//TODO read quantity of product in related order
		holder.tvAddressCodeAndCity.setText(address.getPostalCode() + " " + address.getCity());
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
	public CustomerAddress getItem(int index) {
		return ((List<CustomerAddress>) data).get(index);
	}

	@Override
	public long getItemId(int index) {
		return ((List<CustomerAddress>) data).get(index).getCustomerAddressId();
	}

	static class OrderHolder {
		TextView tvAddressId;
		TextView tvAddressDetails;
		TextView tvAddressCodeAndCity;
	}

}