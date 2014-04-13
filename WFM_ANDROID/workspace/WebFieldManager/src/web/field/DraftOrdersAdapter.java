package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.CustomersAdapter.CustomersHolder;
import web.field.model.simple.CustomerSimple;
import web.field.model.simple.OrderSimple;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class DraftOrdersAdapter extends ArrayAdapter<OrderSimple> {

	Context context;
	int layoutResourceId;
	List<OrderSimple> data = new ArrayList<OrderSimple>();

	public DraftOrdersAdapter(Context context, int layoutResourceId,
			List<OrderSimple> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		DraftOrdersHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new DraftOrdersHolder();
			holder.tvId = (TextView) row.findViewById(R.id.draft_order_id);
			holder.tvSummary = (TextView) row.findViewById(R.id.draft_order_summary);
			holder.tvDate = (TextView) row.findViewById(R.id.draft_order_date);
			// holder.cbStatus = (CheckBox)
			// row.findViewById(R.id.customer_status);
			row.setTag(holder);
		} else {
			holder = (DraftOrdersHolder) row.getTag();
		}
		OrderSimple order = data.get(position);
		holder.tvSummary.setText(order.getOrderSummary());
		holder.tvId.setText(String.valueOf(order.getOrderId()));
		holder.tvDate.setText(order.getOrderDate());
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

	static class DraftOrdersHolder {
		TextView tvId;
		TextView tvSummary;
		TextView tvDate;
		//CheckBox cbStatus;
	}
}
