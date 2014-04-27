package web.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.*;
import web.field.sync.ISendOrderCallback;
import web.field.sync.ISendOrderStrategy;
import web.field.viewmodel.OrderViewModel;
import web.field.viewmodel.ViewModelFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import gueei.binding.Binder;
import gueei.binding.Binder.InflateResult;

public class AddProductsBindingActivity extends WebFieldActivity implements ISendOrderCallback{
	
	private ViewModelFactory factory;
	private OrderViewModel orderViewModel;
	private Order order;
	private OrderTemplate orderTemplate;
	private PromoPayTermDetail payTermDetail;
	
	// Db
	private IDBAdapter db;
	private HashMap<Integer, Float> product_quantity_memo;
	private List<OrderDetail> orderDetails;
	
	private ISendOrderStrategy sendOrderStrategy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		db = new DBAdapter(getHelper());
		
		getActionBar().setTitle("Compose Order");
	
		
		// get order
		String orderTempId = getIntent().getStringExtra("orderTempId");
		order = db.getOrder(orderTempId);
		
		
		OrderDetail[] orderDetailsArr = order.getOrderDetails().toArray(new OrderDetail[] {});
		
		orderDetails = new ArrayList<OrderDetail>(Arrays.asList(orderDetailsArr));
		
		factory = new ViewModelFactory(this);
		orderViewModel = factory.CreateOrderViewModel(order);
		
		
		InflateResult result = Binder.inflateView(this,
				R.layout.activity_addproducts_binding, null, false);
		setContentView(Binder.bindView(this, result, orderViewModel));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addproducts, menu);
		return true;
	}
	
	private boolean saveDraft() {
		Toast.makeText(this, "saveDraft", Toast.LENGTH_SHORT).show();
		return true;
	}
	
	private boolean sendOrder() {
		// copy data to order object
		//this.sendOrderStrategy.sendOrder(order);
		Toast.makeText(this, "sendOrder", Toast.LENGTH_SHORT).show();
		return true;
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
	
	@Override
	public void orderSend(boolean result) {
		String msg = ""; 
		if(result){
			msg = getString(R.string.order_send_ok);
		}
		else{
			msg = getString(R.string.order_send_error);
		}
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		
	}
}
