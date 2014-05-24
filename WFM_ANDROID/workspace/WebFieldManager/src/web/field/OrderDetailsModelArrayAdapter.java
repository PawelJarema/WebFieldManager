package web.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import web.field.QtyPickerFragment.OnCompleteListener;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.Product;
import web.field.model.entity.adapter.OrderDetailModelAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderDetailsModelArrayAdapter extends
		ArrayAdapter<OrderDetailModelAdapter> implements OnClickListener {

	private Context context;
	private OnCompleteListener onCompleteListener;
	int layoutResourceId;
	List<OrderDetailModelAdapter> data = new ArrayList<OrderDetailModelAdapter>();

	public OrderDetailsModelArrayAdapter(Context context, int layoutResourceId,
			List<OrderDetailModelAdapter> data, OnCompleteListener onCompleteListener) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.onCompleteListener = onCompleteListener;
		this.itemsOrdered = new HashMap<Integer, Integer>();
	}

	// HashMap with method
	private int active_row_position = 0;
	private static HashMap<Integer, Integer> itemsOrdered;

	public void notifyRowIsActive(int position) {
		this.active_row_position = position;
	}

	public int getNumberOfActiveRow() {
		return active_row_position;
	}

	public void increaseOrderItemQty(int position) {
		int qty = 0;
		if (itemsOrdered.containsKey(position)) {
			qty = itemsOrdered.get(position);
		}
		setOrderItemQty(position, ++qty);
	}

	public void decreaseOrderItemQty(int position) {
		int qty = 0;
		if (itemsOrdered.containsKey(position)) {
			qty = itemsOrdered.get(position);
		}
		if (qty > 0)
			setOrderItemQty(position, --qty);
	}

	public void checkLineConditions(int position) {

		OrderDetailModelAdapter activeLine = data.get(position);
		activeLine.setLineError("");

		int qty = activeLine.getQty();
		Integer min = activeLine.getQtyMin();
		Integer max = activeLine.getQtyMax();

		if (min != null && qty < min) {
			activeLine.setLineError(String.format(context.getResources()
					.getString(R.string.order_line_min_error), min));
		}
		else if (max != null && qty > max) {
			activeLine.setLineError(String.format(context.getResources()
					.getString(R.string.order_line_max_error), max));
		}
		
		
	}

	public void setOrderItemQty(int position, int qty) {
		if (qty >= 0) {
			itemsOrdered.put(position, qty);
			// get multiplier
			Integer multi = data.get(position).getQtyMultiples();

			data.get(position).setQty(qty * multi);
			
			// validate line
			checkLineConditions(position);
			
			// notify listener
			this.onCompleteListener.onComplete(position, qty * multi);
		}
		
		
	}

	public int getQtyForOrder(int position) {
		if (itemsOrdered.containsKey(position))
			return itemsOrdered.get(position);
		else
			return 0;
	}

	public HashMap<Integer, Integer> getOrderQtyDataHash() {
		return itemsOrdered;
	}

	public void setOrderQtyDataHash(HashMap<Integer, Integer> qty_data) {
		if (qty_data != null)
			this.itemsOrdered = qty_data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		OrderDetailsHolder holder = null;
		checkLineConditions(position);
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new OrderDetailsHolder();
			holder.tvCode = (TextView) row
					.findViewById(R.id.order_product_code);
			holder.tvId = (TextView) row.findViewById(R.id.order_product_id);
			holder.tvDescription = (TextView) row
					.findViewById(R.id.order_product_description);
			holder.ivPicture = (ImageView) row
					.findViewById(R.id.order_product_image);
			holder.tvQty = (TextView) row.findViewById(R.id.order_detail_qty);
			holder.tvQty.setFocusable(false);
			holder.bPlus = (Button) row.findViewById(R.id.order_plus_one);
			holder.bMinus = (Button) row.findViewById(R.id.order_minus_one);
			
			// validation msg
			holder.tvLineAlert = (TextView) row.findViewById(R.id.order_line_alert);

			row.setTag(holder);
		} else {
			holder = (OrderDetailsHolder) row.getTag();
		}
		OrderDetailModelAdapter orderDetail = data.get(position);
		Product product = orderDetail.getProduct();
		holder.ivPicture.setImageDrawable(context.getResources().getDrawable(
				R.drawable.ic_action_search));
		holder.tvCode.setText(product.getCode());
		holder.tvId.setText(orderDetail.getOrderDetailTempId());
		holder.tvDescription.setText(product.getProductDescription());
		holder.bPlus.setOnClickListener(this);
		holder.bMinus.setOnClickListener(this);
		if (itemsOrdered.containsKey(Integer.valueOf(position)))
			holder.tvQty.setText(itemsOrdered.get(position).toString());
		else
			holder.tvQty.setText("");
		// holder.cbStatus.setChecked(customer.isActive());
		
		// set validation msg and mark as read if any error
		if(orderDetail.getLineError() != null && !orderDetail.getLineError().equals("")){
			holder.tvLineAlert.setText(orderDetail.getLineError());
			holder.tvLineAlert.setBackground(context.getResources().getDrawable(
					R.drawable.rounded_corners_red));
		}
		else{
			// maybe some msg here?
			holder.tvLineAlert.setText("");
			holder.tvLineAlert.setBackground(context.getResources().getDrawable(
					R.drawable.rounded_corners_green));
		}

		// style list depending on position
		if (position % 2 == 0)
			row.setBackgroundColor(context.getResources().getColor(
					R.color.list_dark));
		else
			row.setBackgroundColor(context.getResources().getColor(
					R.color.list_light));

		return row;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public OrderDetailModelAdapter getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		// while primary key is string, return index
		return index;
	}

	static class OrderDetailsHolder {
		TextView tvQty;
		ImageView ivPicture;
		TextView tvId;
		TextView tvCode;
		TextView tvDescription;

		// Product Detail Popup -> +/- buttons
		Button bPlus;
		Button bMinus;

		// line error
		TextView tvLineAlert;
	}

	// Row Expanding Product Detali contains buttons. This is the listener meth
	// for them
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_plus_one:
			increaseOrderItemQty(active_row_position);
			break;
		case R.id.order_minus_one:
			decreaseOrderItemQty(active_row_position);
			break;
		}
		this.notifyDataSetChanged();
	}
}
