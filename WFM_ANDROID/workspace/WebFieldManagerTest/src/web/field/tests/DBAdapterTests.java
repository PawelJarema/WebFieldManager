package web.field.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.db.IDaoProvider;
import web.field.model.entity.Order;
import web.field.model.simple.*;
import web.field.tests.TestDaoProvider;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DBAdapterTests {

	ConnectionSource connectionSource = null;
	IDaoProvider daoProvider = null;
	IDBAdapter db = null;
	
	@Test
	public void listCustomersTest() {
		List<CustomerSimple> customers = db.listCustomers();
		
		assertNotNull(customers);
		assertTrue(customers.size() > 0);
	}
	
	@Test
	public void listProductFamilies_test(){
		List<ProductFamilySimple> list = db.listProductFamilies();
		
		assertNotNull(list);
		assertTrue(list.size() > 0);
	
	}
	
	@Test
	public void listProductCategories_test(){
		List<ProductCategorySimple> list = db.listProductCategories();
		
		assertNotNull(list);
		assertTrue(list.size() > 0);
	
	}
	
	@Test
	public void listProductManufactures_test(){
		List<ProductManufacturerSimple> list = db.listProductManufactures();
		
		assertNotNull(list);
		assertTrue(list.size() > 0);
	
	}
	
	@Test
	public void listProductBrands_test(){
		List<ProductBrandSimple> list = db.listProductBrands();
		
		assertNotNull(list);
		assertTrue(list.size() > 0);
	
	}
	
	@Test
	public void getOrderTest(){
		List<OrderSimple> orders = db.listOrders(null);
		
		assertNotNull(orders);
		assertTrue(orders.size() > 0);
		
		for(OrderSimple orderSimple : orders){
			Order orderFull = db.getOrder(orderSimple.getOrderId());
			assertNotNull(orderFull);
			assertNotNull(orderFull.getOrderDetails());
			System.out.println(orderFull.getOrderDetails().size() + " " + orderSimple.getOrderId());
			assertTrue(orderFull.getOrderDetails().size() > 0);
		}
	}

	@Before
	public void setUp() throws Exception {
		// create sqlite connection
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		// create a database connection
		connectionSource = new JdbcConnectionSource("jdbc:sqlite:c:/Users/Adam/Downloads/5webfield.db");
		
		// create dao provider
		daoProvider = new TestDaoProvider(connectionSource);
		
		// create db adapter
		db = new DBAdapter(daoProvider, new TestTenantProvider());

	}

	@After
	public void tearDown() throws Exception {
		 if(connectionSource != null){
			 connectionSource.close();
		 }
	}

	

}
