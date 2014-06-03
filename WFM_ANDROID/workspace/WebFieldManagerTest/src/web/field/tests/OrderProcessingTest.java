package web.field.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

import org.junit.*;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.db.IDaoProvider;
import web.field.model.entity.*;
import web.field.order.processing.*;

public class OrderProcessingTest {

	ConnectionSource connectionSource = null;
	IDaoProvider daoProvider = null;
	IDBAdapter db = null;

	private OrderCache orderCache;
	private Order order;
	private OrderTemplate orderTemplate;
	private List<PromoPayTermDetail> promoPayTermDetails = new ArrayList<PromoPayTermDetail>();
	private OrderTemplateThreshold orderTemplateThreshold;
	private PromoThreshold promoThreshold;

	private OrderCalculationRequest calculationRequest;

	private ProcessOrderStrategy processOrderStrategy = new ProcessOrderStrategy();

	private Product testProduct1 = new Product();
	private Product testProduct2 = new Product();
	private Product testProduct3 = new Product();

	@Before
	public void setUp() throws Exception {
		testProduct1.setProductId(1);
		testProduct1.setProductDescription("Unit Test Product 1");
		testProduct1.setCode("P1");
		testProduct1.setPrice(10);

		testProduct2.setProductId(2);
		testProduct2.setProductDescription("Unit Test Product 2");
		testProduct2.setCode("P2");
		testProduct2.setPrice(20);

		testProduct3.setProductId(3);
		testProduct3.setProductDescription("Unit Test Product 3");
		testProduct3.setCode("P3");
		testProduct3.setPrice(30);

		orderCache = new OrderCache();
		orderCache.setPromoPayTermDetails(promoPayTermDetails);

		// create sqlite connection
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		// create a database connection
		connectionSource = new JdbcConnectionSource(
				"jdbc:sqlite:d:/my/webfield.db");

		// create dao provider
		daoProvider = new TestDaoProvider(connectionSource);

		// create db adapter
		db = new DBAdapter(daoProvider, new TestTenantProvider());
	}

	@Test
	public void getFullOrderValue_Test() {
		// create order
		Order order = new Order();

		// add order row
		OrderDetail orderDetail = new OrderDetail();
		testProduct1.setPrice(3.11);
		orderDetail.setProduct(testProduct1);// add P1 product

		// set qty
		orderDetail.setQty(1);
		ForeignCollection<OrderDetail> details = new TestForeignCollection<OrderDetail>();
		details.add(orderDetail);
		order.OrdersDetail = details;

		// create order template
		orderTemplate = new OrderTemplate();

		orderTemplate
				.setOrdersTemplateDetails(new TestForeignCollection<OrderTemplateDetail>());
		OrderTemplateDetail orderTemplateDetail = new OrderTemplateDetail();
		orderTemplateDetail.setProduct(testProduct1);
		orderTemplate.getOrdersTemplateDetails().add(orderTemplateDetail);

		// create order cashe
		orderCache = new OrderCache(orderTemplate, promoPayTermDetails, db);

		OrderCalculationRequest request = new OrderCalculationRequest(order,
				orderCache);

		// call process
		OrderCalculationResult result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 3.11);
	}

	@Test
	public void simple_PromoThresholdDetail_Test() throws SQLException {

		// create order
		Order order = new Order();

		// add order row
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(testProduct1);// add P1 product

		// set qty
		orderDetail.setQty(10);
		ForeignCollection<OrderDetail> details = new TestForeignCollection<OrderDetail>();
		details.add(orderDetail);
		order.OrdersDetail = details;

		// create order template
		orderTemplate = new OrderTemplate();

		orderTemplate
				.setOrdersTemplateDetails(new TestForeignCollection<OrderTemplateDetail>());
		OrderTemplateDetail orderTemplateDetail = new OrderTemplateDetail();
		orderTemplateDetail.setProduct(testProduct1);
		orderTemplate.getOrdersTemplateDetails().add(orderTemplateDetail);

		// add template order
		order.setOrderTemplate(orderTemplate);

		// create order promo threshold
		PromoThreshold threshold = new PromoThreshold();
		TestForeignCollection<PromoThresholdDetail> thDetails = new TestForeignCollection<PromoThresholdDetail>();
		threshold.setPromoThresholdDetail(thDetails);
		PromoThresholdDetail thDet1 = new PromoThresholdDetail();
		thDet1.setThresholdMinValue(200);
		thDet1.setThresholdMaxValue(1000);
		thDet1.setThresholdDiscount(0.05);
		thDet1.setProduct(testProduct1);
		thDetails.add(thDet1);

		PromoThresholdDetail thDet2 = new PromoThresholdDetail();
		thDet2.setThresholdMinValue(1001);
		thDet2.setThresholdMaxValue(2000);
		thDet2.setThresholdDiscount(0.1);
		thDet2.setProduct(testProduct1);
		thDetails.add(thDet2);

		orderTemplate.setPromoThreshold(threshold);

		// create order cashe
		orderCache = new OrderCache(orderTemplate, promoPayTermDetails, db);

		OrderCalculationRequest request = new OrderCalculationRequest(order,
				orderCache);

		// call process
		OrderCalculationResult result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 100);
		assertTrue(result.getTotalDiscountsValue() == 0);

		// increase qty to 200
		orderDetail.setQty(60);

		// call process
		result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 600);
		assertTrue(result.getTotalDiscountsValue() == 30);// 5% disc should be
															// applied

		// increase qty to 2000
		orderDetail.setQty(150);

		// call process
		result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 1500);
		assertTrue(result.getTotalDiscountsValue() == 150);// 10% disc should
	}

	@Test
	public void simple_ordertemplateThresholdDiscount_Test()
			throws SQLException {

		// create order
		Order order = new Order();

		// add order row
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(testProduct1);// add P1 product

		// set qty
		orderDetail.setQty(10);
		ForeignCollection<OrderDetail> details = new TestForeignCollection<OrderDetail>();
		details.add(orderDetail);
		order.OrdersDetail = details;

		// create order template
		orderTemplate = new OrderTemplate();

		orderTemplate
				.setOrdersTemplateDetails(new TestForeignCollection<OrderTemplateDetail>());
		OrderTemplateDetail orderTemplateDetail = new OrderTemplateDetail();
		orderTemplateDetail.setProduct(testProduct1);
		orderTemplate.getOrdersTemplateDetails().add(orderTemplateDetail);

		// add template order
		order.setOrderTemplate(orderTemplate);

		// create order template threshold
		OrderTemplateThreshold otThreshold = new OrderTemplateThreshold();
		TestForeignCollection<OrderTemplateThresholdDetail> thDetails = new TestForeignCollection<OrderTemplateThresholdDetail>();
		otThreshold.setOrdersTemplateThresholdDetails(thDetails);
		// add 5% discount for 100 value
		OrderTemplateThresholdDetail otd = new OrderTemplateThresholdDetail();
		otd.setDiscount(0.05);
		otd.setOrderTotal(1000);
		thDetails.add(otd);

		// add 10% discount for 1000 value
		OrderTemplateThresholdDetail otd2 = new OrderTemplateThresholdDetail();
		otd2.setDiscount(0.1);
		otd2.setOrderTotal(10000);
		thDetails.add(otd2);

		// set threshold for templates
		orderTemplate.setOrderTemplateThreshold(otThreshold);

		// create order cashe
		orderCache = new OrderCache(orderTemplate, promoPayTermDetails, db);

		OrderCalculationRequest request = new OrderCalculationRequest(order,
				orderCache);

		// call process
		OrderCalculationResult result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 100);// 10 products times 10 = 100
		assertTrue(result.getTotalDiscountsValue() == 0);

		// increase qty to 200
		orderDetail.setQty(200);

		// call process
		result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 2000);
		assertTrue(result.getTotalDiscountsValue() == 100);// 5% disc should be
															// applied

		// increase qty to 2000
		orderDetail.setQty(2000);

		// call process
		result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 20000);
		assertTrue(result.getTotalDiscountsValue() == 2000);// 10% disc should
															// be applied

	}

	@Test
	public void simple_orderTemplateDiscount_Test() throws SQLException {

		// create order
		Order order = new Order();

		// add order row
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(testProduct1);// add P1 product

		// set qty
		orderDetail.setQty(10);
		ForeignCollection<OrderDetail> details = new TestForeignCollection<OrderDetail>();
		details.add(orderDetail);
		order.OrdersDetail = details;

		// create order template
		orderTemplate = new OrderTemplate();

		// 5% discount from template
		orderTemplate.setDiscount(0.05);
		orderTemplate
				.setOrdersTemplateDetails(new TestForeignCollection<OrderTemplateDetail>());
		OrderTemplateDetail orderTemplateDetail = new OrderTemplateDetail();
		orderTemplateDetail.setProduct(testProduct1);
		orderTemplate.getOrdersTemplateDetails().add(orderTemplateDetail);

		// create order cashe
		orderCache = new OrderCache(orderTemplate, promoPayTermDetails, db);

		// add template order
		order.setOrderTemplate(orderTemplate);

		OrderCalculationRequest request = new OrderCalculationRequest(order,
				orderCache);

		// call process
		OrderCalculationResult result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 100);// 10 products times 10 = 100
		assertTrue(result.getTotalDiscountsValue() == 5);

	}

	@Test
	public void simple_PromoPayTermDetail_discount_Test() throws SQLException {

		// create order
		Order order = new Order();

		// 5% discount from payment terms
		order.setDiscountHeaderPayterms(0.05);

		// add order row
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(testProduct1);// add P1 product

		// set qty
		orderDetail.setQty(10);
		ForeignCollection<OrderDetail> details = new TestForeignCollection<OrderDetail>();
		details.add(orderDetail);
		order.OrdersDetail = details;

		// create order template
		orderTemplate = new OrderTemplate();
		orderTemplate
				.setOrdersTemplateDetails(new TestForeignCollection<OrderTemplateDetail>());
		OrderTemplateDetail orderTemplateDetail = new OrderTemplateDetail();
		orderTemplateDetail.setProduct(testProduct1);
		orderTemplate.getOrdersTemplateDetails().add(orderTemplateDetail);

		OrderCalculationRequest request = new OrderCalculationRequest(order,
				orderCache);

		// call process
		OrderCalculationResult result = processOrderStrategy.process(request);

		// check result
		assertNotNull(result);
		assertTrue(result.getFullValue() == 100);// 10 products times 10 = 100
		assertTrue(result.getTotalDiscountsValue() == 5);

	}

}
