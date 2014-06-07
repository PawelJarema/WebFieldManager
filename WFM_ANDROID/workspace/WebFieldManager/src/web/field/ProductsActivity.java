package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Product;
import web.field.model.entity.adapter.ProductModelAdapter;
import web.field.model.simple.ProductSimple;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ProductsActivity extends WebFieldActivity {

	ProductsArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);

		IDBAdapter db = new DBAdapter(getHelper(), getTenantProvider());

		ListView lvProducts = (ListView) findViewById(R.id.product_list);

		List<Product> products = db.listProductsFull();
		List<ProductModelAdapter> data = new ArrayList<ProductModelAdapter>();

		adapter = new ProductsArrayAdapter(ProductsActivity.this,
				R.layout.product_entry, data);

		lvProducts.setAdapter(adapter);

		lvProducts
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v,
							final int position, long id) {
						// Intent i = new Intent(
						// "web.field.ProductDetailsActivity");
						// i.putExtra("productId", (int) id);
						// startActivity(i);
					}
				});

	}

}
