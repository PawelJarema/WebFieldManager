package web.field;

import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.simple.OrderSimple;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class OrdersActivity extends WebFieldActivity {

	OrdersAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orders);

		IDBAdapter db = new DBAdapter(getHelper(), getTenantProvider());

		ListView lvOrders = (ListView) findViewById(R.id.order_list);

		Integer customerId = getIntent().getIntExtra("customerId", 0);

		List<OrderSimple> orders = db.listOrders(customerId);

		adapter = new OrdersAdapter(OrdersActivity.this, R.layout.order_entry,
				orders);

		lvOrders.setAdapter(adapter);

		lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
