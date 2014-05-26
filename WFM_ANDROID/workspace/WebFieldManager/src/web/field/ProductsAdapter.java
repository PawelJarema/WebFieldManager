package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.simple.ProductSimple;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductsAdapter extends ArrayAdapter<ProductSimple> {
	Context context;
	int layoutResourceId;
	List<ProductSimple> data = new ArrayList<ProductSimple>();

	public ProductsAdapter(Context context, int layoutResourceId,
			List<ProductSimple> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ProductsHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ProductsHolder();
			holder.tvCode = (TextView) row.findViewById(R.id.order_product_code);
			holder.tvPrice = (TextView) row.findViewById(R.id.listrow_product_price);
			//holder.tvId = (TextView) row.findViewById(R.id.order_product_id);
			holder.tvDescription = (TextView) row.findViewById(R.id.order_product_description);
			holder.ivPicture = (ImageView) row.findViewById(R.id.order_product_image);
			
			holder.tvManufacturer = (TextView) row.findViewById(R.id.listrow_product_manufacturer);
			holder.tvBrand = (TextView) row.findViewById(R.id.listrow_product_brand);
			holder.tvFamily = (TextView) row.findViewById(R.id.listrow_product_family);
			holder.tvCategory = (TextView) row.findViewById(R.id.listrow_product_category);
			row.setTag(holder);
		} else {
			holder = (ProductsHolder) row.getTag();
		}
		ProductSimple product = data.get(position);
		holder.ivPicture.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_search));
		holder.tvCode.setText(product.getProductCode());
		//holder.tvId.setText(String.valueOf(product.getProductId()));
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
	public ProductSimple getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return data.get(index).getProductId();
	}

	static class ProductsHolder {
		ImageView ivPicture;
		//TextView tvId;
		TextView tvCode;
		TextView tvDescription;
		
		//TODO not in productSimple
		TextView tvManufacturer;
		TextView tvBrand;
		TextView tvFamily;
		TextView tvCategory;
		
		TextView tvPrice;
	}
}
