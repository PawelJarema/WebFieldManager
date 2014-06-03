package web.field;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Customer;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDetailsActivity extends WebFieldActivity {

	int customerId = -1;
	TextView tvCustomerName;
	TextView tvCustomerVat;
	CheckBox cbStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_details);

		IDBAdapter db = new DBAdapter(getHelper(), getTenantProvider());
		customerId = getIntent().getIntExtra("customerId", -1);
		Customer customer = db.getCustomer(customerId);

		if (customer == null) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Could not find Customer with id = " + customerId,
					Toast.LENGTH_SHORT);
			toast.show();
			finish();
			return;
		}

		tvCustomerName = (TextView) findViewById(R.id.customer_name);
		tvCustomerName.setText(customer.getCustomerName());

		tvCustomerVat = (TextView) findViewById(R.id.customer_vat);
		tvCustomerVat.setText(customer.getVatId());

		cbStatus = (CheckBox) findViewById(R.id.customer_status);
		cbStatus.setChecked(customer.isActive());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_details, menu);
		return true;
	}

}
