package web.field;

import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.simple.OrderTemplateSimple;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class OrderTemplatesActivity extends WebFieldActivity {

	OrderTemplatesAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_templates);

		IDBAdapter db = new DBAdapter(getHelper(), getTenantProvider());

		ListView lvTemplates = (ListView) findViewById(R.id.templates_list);

		// check if customer id provided

		List<OrderTemplateSimple> templates = db.listOrderTemplates();

		adapter = new OrderTemplatesAdapter(OrderTemplatesActivity.this,
				R.layout.order_template_entry, templates);

		lvTemplates.setAdapter(adapter);

		lvTemplates
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
