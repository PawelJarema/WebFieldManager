package web.field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import web.field.QtyPickerFragment.OnCompleteListener;
import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.model.entity.Order;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.OrderTemplateThreshold;
import web.field.model.entity.PromoPayTermDetail;
import web.field.order.processing.OrderCache;
import web.field.order.processing.OrderCalculationRequest;
import web.field.order.processing.OrderCalculationResult;
import web.field.order.processing.ProcessOrderTask;
import web.field.sync.ISendOrderCallback;
import web.field.sync.ISendOrderStrategy;
import web.field.sync.SendOrderStrategy;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.ForeignCollection;

public class AddProductsActivity extends FragmentActivity implements
		ISendOrderCallback, OnCompleteListener {

	// Ui and TextViews
	private Button bFilterByBrand;
	private Button bFilterByCategory;
	private Button bFilterByProducer;
	private Button bFilterByFamiliy;

	private TextView tvOrderTemplateDiscount;
	private TextView tvTemplateThresholdDiscount;
	private TextView tvPayTemrsDiscount;
	private TextView tvOrderValueBeforeDiscounts;
	private TextView tvTotalDisountValue;
	private TextView tvValueOfFreeProducts;
	private TextView tvOrderValue;

	private Order order;
	private OrderTemplate orderTemplate;
	private PromoPayTermDetail payTermDetail;

	// Product List
	private OrderDetailsAdapter adapter;
	private ListView lvOrderLines;

	// Summary TextViews
	private TextView summary; // <to display total order value and messages

	// Db
	private IDBAdapter db;
	private List<OrderDetail> orderDetails;

	private ISendOrderStrategy sendOrderStrategy;

	private OrderCalculationRequest calculatiorRequest;
	private OrderCache orderCache;
	private List<PromoPayTermDetail> promoPayTermDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sendOrderStrategy = new SendOrderStrategy(this);

		setContentView(R.layout.activity_addproducts);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		db = new DBAdapter(getHelper());

		getActionBar().setTitle("Compose Order");

		// get order
		String orderTempId = getIntent().getStringExtra("orderTempId");
		order = db.getOrder(orderTempId);
		OrderDetail[] orderDetailsArr = order.getOrderDetails().toArray(
				new OrderDetail[] {});
		orderDetails = new ArrayList<OrderDetail>(
				Arrays.asList(orderDetailsArr));

		promoPayTermDetails = db.getPromoPayTermDetails();

		int tamplateId = getIntent().getIntExtra("templateId", 0);
		orderTemplate = db.getOrderTemplate(tamplateId);

		orderCache = new OrderCache(orderTemplate, promoPayTermDetails, db);

		calculatiorRequest = new OrderCalculationRequest(this.order,
				this.orderCache);

		prepareUiElements();
		restoreQtyData(savedInstanceState, adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addproducts, menu);
		return true;
	}

	private void prepareUiElements() {

		adapter = new OrderDetailsAdapter(this, R.layout.list_row_addproducts,
				orderDetails);

		lvOrderLines = (ListView) findViewById(R.id.addproduct_list);
		lvOrderLines.setAdapter(adapter);

		lvOrderLines.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				QtyPickerFragment f = new QtyPickerFragment();
				Bundle bundle = new Bundle();
				int current_qty = adapter.getQtyForOrder(position);
				bundle.putInt("position", position);
				bundle.putInt("qty", current_qty);
				f.setArguments(bundle);
				f.show(getSupportFragmentManager(), "quantity picker");
			}
		});

		tvOrderTemplateDiscount = (TextView) findViewById(R.id.order_template_discount);
		tvOrderTemplateDiscount.setText(orderTemplate.getDiscount().toString());

		tvTemplateThresholdDiscount = (TextView) findViewById(R.id.order_template_threshold_discount);
		if (orderTemplate.getOrderTemplateThreshold() != null) {
			OrderTemplateThreshold orderTemplateThreshold = orderTemplate
					.getOrderTemplateThreshold();
		}

		tvPayTemrsDiscount = (TextView) findViewById(R.id.order_payterms_discount);
		tvPayTemrsDiscount.setText("TODO");

		tvOrderValueBeforeDiscounts = (TextView) findViewById(R.id.order_total_before_discount);

		tvTotalDisountValue = (TextView) findViewById(R.id.order_total_discount);

		tvValueOfFreeProducts = (TextView) findViewById(R.id.order_total_free_qty_value);

		tvOrderValue = (TextView) findViewById(R.id.order_total_value);

	}

	private OrderDetail rewriteOrderQty(int position) {
		OrderDetail order_detail = orderDetails.get(position);
		// adapter stores order qty data
		int qty = adapter.getQtyForOrder(position);
		order_detail.setQty(qty);
		return order_detail;
	}

	private boolean saveDraft() {
		// TODO
		return true;
	}

	private boolean sendOrder() {
		// adapter stores order qty data
		// adapter has methods to get ordered items with respective quantity
		Set<Integer> ordered_items_by_list_position = adapter
				.getOrderQtyDataHash().keySet();
		for (int position : ordered_items_by_list_position) {
			int qty = adapter.getQtyForOrder(position);
			orderDetails.set(position, rewriteOrderQty(position));
		}
		this.sendOrderStrategy.sendOrder(order);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
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

	private OrmDbHelper databaseHelper = null;

	protected OrmDbHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					OrmDbHelper.class);
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

	@Override
	public void orderSend(boolean result) {
		String msg = "";
		if (result) {
			msg = getString(R.string.order_send_ok);
		} else {
			msg = getString(R.string.order_send_error);
		}
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	private void message(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onComplete(int position, int qty) {
		// adapter stores order qty data
		if (qty > 0) {
			adapter.addOrderItemQty(position, qty);
			
			// do order recalculation
			ProcessOrderTask processTask = new ProcessOrderTask() {
				@Override
				protected void onPostExecute(OrderCalculationResult result) {
					super.onPostExecute(result);

					if (result.getOrderTemplateThresholdDiscount() != null) {
						tvTemplateThresholdDiscount
								.setText(result
										.getOrderTemplateThresholdDiscount()
										.toString());
					}
					tvOrderValueBeforeDiscounts.setText(result.getFullValue()
							.toString());
					tvTotalDisountValue.setText(String.valueOf(result
							.getTotalDiscounts()));
					tvValueOfFreeProducts.setText("TODO");
					tvOrderValue
							.setText(String.valueOf(result.getOrderTotal()));
				}
			};
			processTask.execute(calculatiorRequest);

			// refresh adapter
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (adapter != null) {
			outState.putSerializable("qty_values",
					adapter.getOrderQtyDataHash());
		}
	}

	private void restoreQtyData(Bundle savedInstanceState,
			OrderDetailsAdapter adapter) {
		if (savedInstanceState != null) {
			adapter.setOrderQtyDataHash((HashMap<Integer, Integer>) savedInstanceState
					.getSerializable("qty_values"));
			adapter.notifyDataSetChanged();
		}
	}
}
