package web.field;

import java.sql.SQLException;

import web.field.db.IDaoProvider;
import web.field.model.entity.Customer;
import web.field.model.entity.CustomerAddress;
import web.field.model.entity.CustomerCategory;
import web.field.model.entity.Order;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.OrderTemplateDetail;
import web.field.model.entity.OrderTemplateThreshold;
import web.field.model.entity.OrderTemplateThresholdDetail;
import web.field.model.entity.Outbound;
import web.field.model.entity.Product;
import web.field.model.entity.ProductBrand;
import web.field.model.entity.ProductCategory;
import web.field.model.entity.ProductFamily;
import web.field.model.entity.ProductManufacturer;
import web.field.model.entity.PromoGroup;
import web.field.model.entity.PromoGroupDetail;
import web.field.model.entity.PromoPayTerm;
import web.field.model.entity.PromoPayTermDetail;
import web.field.model.entity.PromoThreshold;
import web.field.model.entity.PromoThresholdDetail;
import web.field.model.entity.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.*;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmDbHelper extends OrmLiteSqliteOpenHelper implements IDaoProvider{

	private static final String DATABASE_NAME = "webfield.db";

	private static final int DATABASE_VERSION = 1;

	private Dao<Customer, Integer> customerDao;
	private Dao<CustomerAddress, Integer> customerAddressDao;
	private Dao<CustomerCategory, Integer> customerCategoryDao;

	private Dao<ProductManufacturer, Integer> ProductManufacturerDao;
	private Dao<ProductBrand, Integer> ProductBrandDao;
	private Dao<ProductCategory, Integer> ProductCategoryDao;
	private Dao<ProductFamily, Integer> ProductFamilyDao;
	private Dao<Product, Integer> ProductDao;

	private Dao<OrderTemplateDetail, Integer> OrderTemplateDetailDao;
	private Dao<OrderTemplate, Integer> OrderTemplateDao;

	private Dao<Order, String> OrderDao;
	private Dao<OrderDetail, String> OrderDetailsDao;

	private Dao<OrderTemplateThreshold, Integer> OrderTemplateThresholdDao;
	private Dao<OrderTemplateThresholdDetail, Integer> OrdersTemplateThresholdDetailsDao;

	private Dao<PromoGroup, Integer> PromoGroupDao;
	private Dao<PromoGroupDetail, Integer> PromoGroupDetailDao;

	private Dao<PromoPayTerm, Integer> PromoPayTermDao;
	private Dao<PromoPayTermDetail, Integer> PromoPayTermsDetailDao;

	private Dao<PromoThreshold, Integer> PromoThresholdDao;
	private Dao<PromoThresholdDetail, Integer> PromoThresholdDetailDao;
	
	private Dao<Outbound, Long> OutboundDao;
	
	private Dao<User, Integer> UserDao;

	public OrmDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(OrmDbHelper.class.getName(), "onCreate");
			// customer tables
			TableUtils.createTable(connectionSource, CustomerCategory.class);
			TableUtils.createTable(connectionSource, CustomerAddress.class);
			TableUtils.createTable(connectionSource, Customer.class);

			// product tables
			TableUtils.createTable(connectionSource, ProductManufacturer.class);
			TableUtils.createTable(connectionSource, ProductBrand.class);
			TableUtils.createTable(connectionSource, ProductCategory.class);
			TableUtils.createTable(connectionSource, ProductFamily.class);
			TableUtils.createTable(connectionSource, Product.class);

			// order template tables
			TableUtils.createTable(connectionSource, OrderTemplateDetail.class);
			TableUtils.createTable(connectionSource, OrderTemplate.class);

			// order tables
			TableUtils.createTable(connectionSource, Order.class);
			TableUtils.createTable(connectionSource, OrderDetail.class);

			// order template threshold tables
			TableUtils.createTable(connectionSource,
					OrderTemplateThreshold.class);
			TableUtils.createTable(connectionSource,
					OrderTemplateThresholdDetail.class);

			// promo group tables
			TableUtils.createTable(connectionSource, PromoGroup.class);
			TableUtils.createTable(connectionSource, PromoGroupDetail.class);

			// promo pay term tables
			TableUtils.createTable(connectionSource, PromoPayTerm.class);
			TableUtils.createTable(connectionSource, PromoPayTermDetail.class);

			// promo threshold tables
			TableUtils.createTable(connectionSource, PromoThreshold.class);
			TableUtils.createTable(connectionSource, PromoThresholdDetail.class);
			
			// users
			TableUtils.createTable(connectionSource, User.class);

		} catch (SQLException e) {
			Log.e(OrmDbHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(OrmDbHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Customer.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(OrmDbHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
	}

	@Override
	public Dao<Customer, Integer> getCustomerDao() throws SQLException {
		if (customerDao == null) {
			customerDao = getDao(Customer.class);
		}
		return customerDao;
	}

	@Override
	public Dao<CustomerAddress, Integer> getCustomerAddressDao()
			throws SQLException {
		if (customerAddressDao == null) {
			customerAddressDao = getDao(CustomerAddress.class);
		}
		return customerAddressDao;
	}

	@Override
	public Dao<CustomerCategory, Integer> getCustomerCategoryDao()
			throws SQLException {
		if (customerCategoryDao == null) {
			customerCategoryDao = getDao(CustomerCategory.class);
		}
		return customerCategoryDao;
	}

	@Override
	public Dao<ProductManufacturer, Integer> getProductManufacturerDao()
			throws SQLException {
		if (ProductManufacturerDao == null) {
			ProductManufacturerDao = getDao(ProductManufacturer.class);
		}
		return ProductManufacturerDao;
	}

	@Override
	public Dao<ProductBrand, Integer> getProductBrandDao() throws SQLException {
		if (ProductBrandDao == null) {
			ProductBrandDao = getDao(ProductBrand.class);
		}
		return ProductBrandDao;
	}

	@Override
	public Dao<ProductCategory, Integer> getProductCategoryDao()
			throws SQLException {
		if (ProductCategoryDao == null) {
			ProductCategoryDao = getDao(ProductCategory.class);
		}
		return ProductCategoryDao;
	}

	@Override
	public Dao<ProductFamily, Integer> getProductFamilyDao()
			throws SQLException {
		if (ProductFamilyDao == null) {
			ProductFamilyDao = getDao(ProductFamily.class);
		}
		return ProductFamilyDao;
	}

	@Override
	public Dao<Product, Integer> getProductDao() throws SQLException {
		if (ProductDao == null) {
			ProductDao = getDao(Product.class);
		}
		return ProductDao;
	}

	@Override
	public Dao<OrderTemplateDetail, Integer> getOrderTemplateDetailDao()
			throws SQLException {
		if (OrderTemplateDetailDao == null) {
			OrderTemplateDetailDao = getDao(OrderTemplateDetail.class);
		}
		return OrderTemplateDetailDao;
	}

	@Override
	public Dao<OrderTemplate, Integer> getOrderTemplateDao()
			throws SQLException {
		if (OrderTemplateDao == null) {
			OrderTemplateDao = getDao(OrderTemplate.class);
		}
		return OrderTemplateDao;
	}

	@Override
	public Dao<Order, String> getOrderDao() throws SQLException {
		if (OrderDao == null) {
			OrderDao = getDao(Order.class);
		}
		return OrderDao;
	}

	@Override
	public Dao<OrderDetail, String> getOrderDetailDao() throws SQLException {
		if (OrderDetailsDao == null) {
			OrderDetailsDao = getDao(OrderDetail.class);
		}
		return OrderDetailsDao;
	}

	@Override
	public Dao<OrderTemplateThreshold, Integer> getOrderTemplateThresholdDao()
			throws SQLException {
		if (OrderTemplateThresholdDao == null) {
			OrderTemplateThresholdDao = getDao(OrderTemplateThreshold.class);
		}
		return OrderTemplateThresholdDao;
	}
	
	@Override
	public Dao<PromoGroup, Integer> getPromoGroupDao() throws SQLException {
		if (PromoGroupDao == null) {
			PromoGroupDao = getDao(PromoGroup.class);
		}
		return PromoGroupDao;
	}

	@Override
	public Dao<PromoPayTerm, Integer> getPromoPayTermDao() throws SQLException {
		if (PromoPayTermDao == null) {
			PromoPayTermDao = getDao(PromoPayTerm.class);
		}
		return PromoPayTermDao;
	}

	@Override
	public Dao<PromoThreshold, Integer> getPromoThresholdDao()
			throws SQLException {
		if (PromoThresholdDao == null) {
			PromoThresholdDao = getDao(PromoThreshold.class);
		}
		return PromoThresholdDao;
	}

	@Override
	public Dao<OrderTemplateThresholdDetail, Integer> getOrdersTemplateThresholdDetailsDao()
			throws SQLException {
		if (OrdersTemplateThresholdDetailsDao == null) {
			OrdersTemplateThresholdDetailsDao = getDao(OrderTemplateThresholdDetail.class);
		}
		return OrdersTemplateThresholdDetailsDao;
	}

	@Override
	public Dao<PromoGroupDetail, Integer> getPromoGroupDetailDao()
			throws SQLException {
		if (PromoGroupDetailDao == null) {
			PromoGroupDetailDao = getDao(PromoGroupDetail.class);
		}
		return PromoGroupDetailDao;
	}

	@Override
	public Dao<PromoPayTermDetail, Integer> getPromoPayTermsDetailDao()
			throws SQLException {
		if (PromoPayTermsDetailDao == null) {
			PromoPayTermsDetailDao = getDao(PromoPayTermDetail.class);
		}
		return PromoPayTermsDetailDao;
	}

	@Override
	public Dao<PromoThresholdDetail, Integer> getPromoThresholdDetailDao()
			throws SQLException {
		if (PromoThresholdDetailDao == null) {
			PromoThresholdDetailDao = getDao(PromoThresholdDetail.class);
		}
		return PromoThresholdDetailDao;
	}
	
	@Override
	public Dao<Outbound, Long> getOutboundDao()
			throws SQLException {
		if (OutboundDao == null) {
			OutboundDao = getDao(Outbound.class);
		}
		return OutboundDao;
	}

	@Override
	public Dao<User, Integer> getUserDao() throws SQLException {
		if (UserDao == null) {
			UserDao = getDao(User.class);
		}
		return UserDao;
	}

}
