package web.field;

import gueei.binding.menu.MenuItemBridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import web.field.QtyPickerFragment.OnCompleteListener;
import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.helpers.Converter;
import web.field.helpers.ITenantProvider;
import web.field.helpers.TenantProvider;
import web.field.model.entity.Order;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.OrderTemplateThreshold;
import web.field.model.entity.PromoPayTermDetail;
import web.field.model.entity.adapter.OrderDetailModelAdapter;
import web.field.model.entity.adapter.OrderModelAdapter;
import web.field.order.processing.OrderCache;
import web.field.order.processing.OrderCalculationRequest;
import web.field.order.processing.OrderCalculationResult;
import web.field.order.processing.ProcessOrderTask;
import web.field.sync.ISendOrderCallback;
import web.field.sync.ISendOrderStrategy;
import web.field.sync.SendOrderStrategy;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class AddProductsActivity extends WebfieldFragmentActivityInner
		implements ISendOrderCallback, OnCompleteListener {

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

	// adapter
	private OrderModelAdapter orderModelAdapter;
	private List<OrderDetailModelAdapter> orderDetailModelAdapters;

	// Product List
	private OrderDetailsModelArrayAdapter adapter;
	private ListView lvOrderLines;
	private LinearLayout product_data_popup;
	private LinearLayout qty_picker_fragment_layout;

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
		qty_picker_fragment_layout = (LinearLayout) this
				.findViewById(R.id.addproducts_qty_fragment_container);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		db = new DBAdapter(getHelper(), getTenantProvider());

		getActionBar().setTitle("New Order");

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

		// create adapters
		orderModelAdapter = new OrderModelAdapter(order, orderTemplate);
		orderDetailModelAdapters = orderModelAdapter.getOrderDetails();

		prepareUiElements();

		// set template discount
		if (orderTemplate != null) {
			tvOrderTemplateDiscount.setText(getResources().getString(
					R.string.order_template_discount)
					+ ":"
					+ Converter.formatDecimal(orderTemplate.getDiscount()) + "%");
		}

		restoreQtyData(savedInstanceState, adapter);

		dismissProgressDialog();

		// run initial callculation
		calculateOrder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();
		getMenuInflater().inflate(R.menu.addproducts, menu);
		return true;
	}

	private void prepareUiElements() {

		adapter = new OrderDetailsModelArrayAdapter(this,
				R.layout.list_row_addproducts, orderDetailModelAdapters, this);

		lvOrderLines = (ListView) findViewById(R.id.addproduct_list);
		lvOrderLines.setAdapter(adapter);

		lvOrderLines.setOnItemClickListener(new OnItemClickListener() {

			// this listener is for product list row clicks
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO a lot of code to service expanding list row layout (new
				// meth refactor?)
				if (product_data_popup != null)
					product_data_popup.setVisibility(View.GONE);
				product_data_popup = (LinearLayout) view
						.findViewById(R.id.order_popup_layout);
				product_data_popup.setVisibility(View.VISIBLE);
				adapter.notifyRowIsActive(position);

				// TODO showMoreProductData();
				QtyPickerFragment frag = new QtyPickerFragment();
				Bundle bundle = new Bundle();
				int current_qty = adapter.getQtyForOrder(position);
				bundle.putInt("position", position);
				bundle.putInt("qty", current_qty);
				frag.setArguments(bundle);
				// shows QtyPicker as Dialog Window:
				// f.show(getSupportFragmentManager(), "quantity picker");
				// shows QtyPicker as layout's child
				FragmentManager manager = getSupportFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(R.id.addproducts_qty_fragment_container,
						frag);
				transaction.commit();
			}
		});
		tvOrderTemplateDiscount = (TextView) findViewById(R.id.order_template_discount);

		tvTemplateThresholdDiscount = (TextView) findViewById(R.id.order_template_threshold_discount);

		tvPayTemrsDiscount = (TextView) findViewById(R.id.order_payterms_discount);

		tvOrderValueBeforeDiscounts = (TextView) findViewById(R.id.order_total_before_discount);

		tvTotalDisountValue = (TextView) findViewById(R.id.order_total_discount);

		tvValueOfFreeProducts = (TextView) findViewById(R.id.order_total_free_qty_value);

		tvOrderValue = (TextView) findViewById(R.id.order_total_value);

		// set strings
		tvTemplateThresholdDiscount.setText(getResources().getString(
				R.string.order_template_threshold_discount)
				+ ": ");

		tvOrderValueBeforeDiscounts.setText(getResources().getString(
				R.string.order_total_before_discount)
				+ ": ");
		tvTotalDisountValue.setText(getResources().getString(
				R.string.order_total_discount)
				+ ": ");
		tvValueOfFreeProducts.setText(getResources().getString(
				R.string.order_total_free_qty_value)
				+ ": ");
		tvOrderValue.setText(getResources().getString(
				R.string.order_total_value)
				+ ": ");

		tvPayTemrsDiscount.setText(getResources().getString(
				R.string.order_payterms_discount)
				+ ": ");
	}

	private OrderDetail rewriteOrderQty(int position) {
		OrderDetail order_detail = orderDetails.get(position);
		// adapter stores order qty data
		int qty = adapter.getQtyForOrder(position);
		order_detail.setQty(qty);
		return order_detail;
	}

	private void fillOrderData() {
		// adapter stores order qty data
		// adapter has methods to get ordered items with respective quantity
		Set<Integer> ordered_items_by_list_position = adapter
				.getOrderQtyDataHash().keySet();
		for (int position : ordered_items_by_list_position) {
			int qty = adapter.getQtyForOrder(position);
			orderDetails.set(position, rewriteOrderQty(position));
		}
	}

	private boolean saveDraft() {
		showProgressDialog();
		
		fillOrderData();
		// try copy data to order
		for (OrderDetail od : this.orderDetails) {
			boolean detailExists = false;
			for (OrderDetail savedDetail : this.order.getOrderDetails()) {
				if (od.getOrderDetailTempId().compareToIgnoreCase(
						savedDetail.getOrderDetailTempId()) == 0) {
					detailExists = true;
					// order detail found, copy qty, value etc
					savedDetail.setDiscount(od.getDiscount());
					savedDetail.setFreeQty(od.getFreeQty());
					savedDetail.setPrice(od.getPrice());
					savedDetail.setQty(od.getQty());
				}

				if (!detailExists) {
					// not exists, need to add one
					OrderDetail newDetail = new OrderDetail();
					newDetail
							.setOrderDetailTempId(UUID.randomUUID().toString());
					// order detail found, copy qty, value etc
					newDetail.setDiscount(od.getDiscount());
					newDetail.setFreeQty(od.getFreeQty());
					newDetail.setPrice(od.getPrice());
					newDetail.setQty(od.getQty());

					// add to order
					order.OrdersDetail.add(newDetail);
				}
			}

			// save order to local db
			db.saveOrder(order);
			Toast.makeText(this, getResources().getString(
								R.string.order_saved), Toast.LENGTH_LONG).show();
			
			dismissProgressDialog();
		}

		return true;
	}

	private boolean sendOrder() {
		showProgressDialog();
		
		fillOrderData();
		// save order do local db
		db.saveOrder(order);

		this.sendOrderStrategy.sendOrder(order);
		return true;
	}

	// this listener is for action bar menu items and back button
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item == null ? 0 : item.getItemId();
		switch (id) {
		case 0:
		case android.R.id.home:
			showYesNoDialog(
					getResources().getString(
							R.string.do_you_want_to_leave_order),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								closeActivity();
								dialog.dismiss();
								break;
							case DialogInterface.BUTTON_NEGATIVE:
								dialog.dismiss();
								break;
							}
						}
					});
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
	public void onBackPressed() {
		onOptionsItemSelected(null);
	}

	private OrmDbHelper databaseHelper = null;

	protected OrmDbHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					OrmDbHelper.class);
		}
		return databaseHelper;
	}
	
	protected ITenantProvider getTenantProvider() {
		return new TenantProvider(getHelper());
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
		
		dismissProgressDialog();
	}

	@Override
	public void onComplete(int position, int qty) {
		// adapter stores order qty data
		if (qty > 0) {

			calculateOrder();
		}
	}

	public void calculateOrder() {
		// do order recalculation
		ProcessOrderTask processTask = new ProcessOrderTask() {
			@Override
			protected void onPostExecute(OrderCalculationResult result) {
				super.onPostExecute(result);

				
				tvOrderValueBeforeDiscounts
						.setText(getResources().getString(
								R.string.order_total_before_discount)
								+ ": "
								+ Converter.formatDecimal(result.getFullValue()));
				tvTotalDisountValue.setText(getResources().getString(
						R.string.order_total_discount)
						+ ": "
						+ Converter.formatDecimal(result
								.getTotalDiscountsValue()));
				tvValueOfFreeProducts.setText(getResources().getString(
						R.string.order_total_free_qty_value)
						+ ": "
						+ Converter.formatDecimal(result.getFreeProducts()));
				tvOrderValue.setText(getResources().getString(
						R.string.order_total_value)
						+ ": "
						+ Converter.formatDecimal(result.getOrderTotal()));
			}
		};
		processTask.execute(calculatiorRequest);

		// refresh adapter
		adapter.notifyDataSetChanged();
	}

	// this comes in handy when communicating with QtyPickerFragment
	// that has to have access to list row modification meths in adapter
	public OrderDetailsModelArrayAdapter getProductListAdapter() {
		return adapter;
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
			OrderDetailsModelArrayAdapter adapter) {
		if (savedInstanceState != null) {
			adapter.setOrderQtyDataHash((HashMap<Integer, Integer>) savedInstanceState
					.getSerializable("qty_values"));
			adapter.notifyDataSetChanged();
		}
	}

	private void closeActivity() {

		this.finish();
	}
}
