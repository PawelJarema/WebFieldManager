package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.simple.OrderSimple;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrdersAdapter extends ArrayAdapter<OrderSimple> {

	Context context;
	int layoutResourceId;
	List<OrderSimple> data = new ArrayList<OrderSimple>();

	public OrdersAdapter(Context context, int layoutResourceId,
			List<OrderSimple> data) {
		super(context, layoutResourceId, data);
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
			holder.tvDate = (TextView) row.findViewById(R.id.order_delivery_date);
			holder.tvId = (TextView) row.findViewById(R.id.order_id);
			holder.tvSummary = (TextView) row.findViewById(R.id.order_ship_to);
			holder.ivPicture = (ImageView) row.findViewById(R.id.order_image);
			row.setTag(holder);
		} else {
			holder = (OrderHolder) row.getTag();
		}
		OrderSimple order = data.get(position);
		holder.ivPicture.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_search));
		holder.tvDate.setText(order.getOrderDate());
		holder.tvId.setText(String.valueOf(order.getOrderId()));
		holder.tvSummary.setText(order.getOrderSummary());
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
	public OrderSimple getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return data.get(index).getOrderId();
	}

	static class OrderHolder {
		TextView tvId;
		TextView tvDate;
		TextView tvSummary;
		ImageView ivPicture;
	}

}
