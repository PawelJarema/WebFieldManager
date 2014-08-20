package web.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import web.field.helpers.Converter;
import web.field.model.entity.adapter.ProductModelAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductsArrayAdapter extends ArrayAdapter<ProductModelAdapter> {
	Context context;
	int layoutResourceId;
	List<ProductModelAdapter> data = new ArrayList<ProductModelAdapter>();
	String priceLbl;
	String codeLbl;
	String stockLbl;

	public ProductsArrayAdapter(Context context, int layoutResourceId,
			List<ProductModelAdapter> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		priceLbl = context.getResources().getString(R.string.product_price);
		codeLbl = context.getResources().getString(R.string.product_code);
		stockLbl = context.getResources().getString(R.string.product_stock);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ProductsHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ProductsHolder();
			holder.tvCode = (TextView) row
					.findViewById(R.id.listrow_product_code);
			holder.tvPrice = (TextView) row
					.findViewById(R.id.listrow_product_price);
			holder.tvDescription = (TextView) row
					.findViewById(R.id.order_product_description);
			holder.ivPicture = (ImageView) row
					.findViewById(R.id.order_product_image);

			holder.tvManufacturer = (TextView) row
					.findViewById(R.id.listrow_product_manufacturer);
			holder.tvBrand = (TextView) row
					.findViewById(R.id.listrow_product_brand);
			holder.tvFamily = (TextView) row
					.findViewById(R.id.listrow_product_family);
			holder.tvCategory = (TextView) row
					.findViewById(R.id.listrow_product_category);

			holder.tvCodeDetail = (TextView) row
					.findViewById(R.id.listrow_product_code_detail);
			holder.tvDescriptionDetail = (TextView) row
					.findViewById(R.id.order_product_description_detail);
			holder.tvManufacturerDetails = (TextView) row
					.findViewById(R.id.listrow_product_manufacturer_detail);
			holder.tvBrandDetails = (TextView) row
					.findViewById(R.id.listrow_product_brand_detail);
			holder.tvFamilyDetails = (TextView) row
					.findViewById(R.id.listrow_product_family_detail);
			holder.tvCategoryDetails = (TextView) row
					.findViewById(R.id.listrow_product_category_detail);
			holder.tvCodeDetail = (TextView) row
					.findViewById(R.id.listrow_product_code_detail);
			holder.tvPriceDetail = (TextView) row
					.findViewById(R.id.listrow_product_price_detail);
			holder.tvStockDetails = (TextView) row
					.findViewById(R.id.listrow_product_stock_detail);

			row.setTag(holder);
		} else {
			holder = (ProductsHolder) row.getTag();
		}
		ProductModelAdapter product = data.get(position);
		holder.ivPicture.setImageDrawable(context.getResources().getDrawable(
				R.drawable.ic_action_search));
		holder.tvCode.setText(product.getCode());
		holder.tvPrice.setText(Converter.formatMoney(product.getPrice()));
		holder.tvDescription.setText(product.getProductDescription());
		holder.tvManufacturer.setText(product.getProductManufacturerDescription());
		holder.tvBrand.setText(product.getProductBrandDescription());
		holder.tvFamily.setText(product.getProductFamilyDescription());
		holder.tvCategory.setText(product.getProductCategoryDescription());

		holder.tvManufacturerDetails.setText(product.getProductManufacturerDescription());
		holder.tvBrandDetails.setText(product.getProductBrandDescription());
		holder.tvFamilyDetails.setText(product.getProductFamilyDescription());
		holder.tvCategoryDetails.setText(product.getProductCategoryDescription());
		holder.tvDescriptionDetail.setText(product.getProductDescription());

		holder.tvPriceDetail.setText(priceLbl + ": "
				+ Converter.formatMoney(product.getPrice()));
		holder.tvCodeDetail.setText(codeLbl + ": " + product.getCode());
		holder.tvStockDetails.setText(stockLbl + ": ");

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
	public ProductModelAdapter getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return data.get(index).getProductId();
	}
	
	@SuppressWarnings("unchecked")
	public void sortDataBy(final int byWhat) 
	{
		if (data == null)
			return;
		
		Collections.sort(data, new Comparator() {
			@Override
			public int compare(Object lhs, Object rhs) {
				ProductModelAdapter first = (ProductModelAdapter) lhs;
				ProductModelAdapter second = (ProductModelAdapter) rhs;
				switch(byWhat)
				{
					case R.id.sortProductsByManufacturer:
						return first.getProductManufacturerDescription().compareToIgnoreCase(
								second.getProductManufacturerDescription());
					case R.id.sortProductsByBrand:
						return first.getProductBrandDescription().compareToIgnoreCase(
								second.getProductBrandDescription());
					case R.id.sortProductsByFamily:
						return first.getProductFamilyDescription().compareToIgnoreCase(
								second.getProductFamilyDescription());
					case R.id.sortProductsByCategory:
						return first.getProductCategoryDescription().compareToIgnoreCase(
								second.getProductCategoryDescription());			
				}
				return 0;
			}
		});
		
		this.notifyDataSetChanged();
	}
	
	static class ProductsHolder {
		ImageView ivPicture;
		// TextView tvId;
		TextView tvCode;
		TextView tvPrice;
		TextView tvDescription;

		TextView tvManufacturer;
		TextView tvBrand;
		TextView tvFamily;
		TextView tvCategory;

		TextView tvManufacturerDetails;
		TextView tvBrandDetails;
		TextView tvFamilyDetails;
		TextView tvCategoryDetails;
		TextView tvStockDetails;

		TextView tvDescriptionDetail;
		TextView tvCodeDetail;
		TextView tvPriceDetail;

	}
}
