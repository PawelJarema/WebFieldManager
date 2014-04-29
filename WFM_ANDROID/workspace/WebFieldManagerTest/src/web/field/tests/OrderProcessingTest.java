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
		db = new DBAdapter(daoProvider);
	}

	@Test
	public void simple_PromoPayTermDetail_discount_Test() throws SQLException {

		// create order
		Order order = new Order();
		
		// 5% discount from payment terms
		order.setDiscountHeaderPayterms(5);

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
		orderTemplate.setOrdersTemplateDetails(new TestForeignCollection<OrderTemplateDetail>());
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
		assertTrue(result.getTotalDiscountsValue() == 0.5);

	}

}
