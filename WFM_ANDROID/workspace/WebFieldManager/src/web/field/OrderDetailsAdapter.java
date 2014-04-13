package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.entity.*;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderDetailsAdapter extends ArrayAdapter<OrderDetail>{
	Context context;
	int layoutResourceId;
	List<OrderDetail> data = new ArrayList<OrderDetail>();

	public OrderDetailsAdapter(Context context, int layoutResourceId,
			List<OrderDetail> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		OrderDetailsHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new OrderDetailsHolder();
			holder.tvCode = (TextView) row.findViewById(R.id.order_product_code);
			holder.tvId = (TextView) row.findViewById(R.id.order_product_id);
			holder.tvDescription = (TextView) row.findViewById(R.id.order_product_description);
			holder.ivPicture = (ImageView) row.findViewById(R.id.order_product_image);
			row.setTag(holder);
		} else {
			holder = (OrderDetailsHolder) row.getTag();
		}
		OrderDetail orderDetail = data.get(position);
		Product product = orderDetail.getProduct();
		holder.ivPicture.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_search));
		holder.tvCode.setText(product.getCode());
		holder.tvId.setText(orderDetail.getOrderDetailTempId());
		holder.tvDescription.setText(product.getProductDescription());
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
	public OrderDetail getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		// while primery key is string, return index
		return index;
	}
	
	

	static class OrderDetailsHolder {
		ImageView ivPicture;
		TextView tvId;
		TextView tvCode;
		TextView tvDescription;
	}
}
