package web.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Order;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.PromoPayTermDetail;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class AddProductsActivity extends Activity {

	// Ui and TextViews
	private Button bFilterByBrand;
	private Button bFilterByCategory;
	private Button bFilterByProducer;
	private Button bFilterByFamiliy;
	
	private Order order;
	private OrderTemplate orderTemplate;
	private PromoPayTermDetail payTermDetail;
	
	// Product List
	private ListView lvOrderLines;
	
	// Summary TextViews
	private TextView summary;  // <to display total order value and messages
	
	// Db
	private IDBAdapter db;
	private HashMap<Integer, Float> product_quantity_memo;
	private List<OrderDetail> orderDetails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addproducts);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		db = new DBAdapter(getHelper());
		
		getActionBar().setTitle("Compose Order");
	
		
		// get order
		String orderTempId = getIntent().getStringExtra("orderTempId");
		order = db.getOrder(orderTempId);
		
		
		OrderDetail[] orderDetailsArr = order.getOrderDetails().toArray(new OrderDetail[] {});

		orderDetails = new ArrayList<OrderDetail>(Arrays.asList(orderDetailsArr));
		prepareUiElements();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addproducts, menu);
		return true;
	}
	
	private boolean saveDraft() {
		//TODO
		return true;
	}
	
	private boolean sendOrder() {
		//TODO
		return true;
	}
	
	private void prepareUiElements() {
		
		OrderDetailsAdapter adapter = new OrderDetailsAdapter(this, R.layout.list_row_addproducts, orderDetails);
		lvOrderLines = (ListView) findViewById(R.id.addproduct_list);
		lvOrderLines.setAdapter(adapter);
		
		lvOrderLines.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/* TODO when list clicked 
				 * and EditText in row changed
				 * remember product quantity and store it in product_quantity_memo (HashMap)
				 */	
			}		
		}); 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id) {
			case android.R.id.home:
				this.finish();
				return true;
			case R.id.action_save_draft:
				saveDraft();
				return true;
			case R.id.action_send_order:
				sendOrder();
				return true;
			case R.id.action_settings:
				return true;
			default:
		}
		return super.onOptionsItemSelected(item);
	}
	
	private OrmDbHelper  databaseHelper = null;
    protected OrmDbHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                OpenHelperManager.getHelper(this, OrmDbHelper .class);
        }
        return databaseHelper;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
