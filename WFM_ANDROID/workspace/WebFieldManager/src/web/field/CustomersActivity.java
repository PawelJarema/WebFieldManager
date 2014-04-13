package web.field;

import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.simple.CustomerSimple;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

public class CustomersActivity extends WebFieldActivity {
	CustomersAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customers);

		IDBAdapter db = new DBAdapter(getHelper());

		ListView lvCustomers = (ListView) findViewById(R.id.customers_list);

		List<CustomerSimple> customers = db.listCustomers();

		adapter = new CustomersAdapter(CustomersActivity.this,
				R.layout.customers_entry, customers);

		lvCustomers.setAdapter(adapter);

		lvCustomers
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v,
							final int position, long id) {
						Intent i = new Intent(
								"web.field.OrderTemplatesActivity");
						i.putExtra("customerId", (int) id);

						// mark that you want to create order
						i.putExtra("context", "neworder");
						startActivity(i);
					}
				});

		registerForContextMenu(lvCustomers);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		createMenu(menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return menuChoice(item);
	}

	private void createMenu(Menu menu) {
		MenuItem mnu1 = menu.add(0, 0, 0, R.string.edit);
		{
			mnu1.setIcon(R.drawable.selector);
		}
		MenuItem mnu2 = menu.add(0, 1, 1, R.string.assign_position);
		{
			mnu2.setIcon(R.drawable.selector);
		}

	}

	private boolean menuChoice(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case 0:
			Toast.makeText(this, "You clicked on Edit", Toast.LENGTH_LONG)
					.show();
			return true;
		case 1:
			Toast.makeText(this, "You clicked on Assign Position",
					Toast.LENGTH_LONG).show();
			return true;
		}
		return false;

	}

}
