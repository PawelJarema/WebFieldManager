package web.field.tests;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import web.field.db.IDaoProvider;
import web.field.model.entity.*;

public class TestDaoProvider implements IDaoProvider{
	
	public TestDaoProvider(ConnectionSource connectionSource){
		this.connectionSource = connectionSource;
	}
	
	private ConnectionSource connectionSource;
	
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
	
	@Override
	public Dao<Customer, Integer> getCustomerDao() throws SQLException {
		if (customerDao == null) {
			customerDao = DaoManager.createDao(connectionSource, Customer.class);
		}
		return customerDao;
	}

	@Override
	public Dao<CustomerAddress, Integer> getCustomerAddressDao()
			throws SQLException {
		if (customerAddressDao == null) {
			customerAddressDao = DaoManager.createDao(connectionSource, CustomerAddress.class);
		}
		return customerAddressDao;
	}

	@Override
	public Dao<CustomerCategory, Integer> getCustomerCategoryDao()
			throws SQLException {
		if (customerCategoryDao == null) {
			customerCategoryDao = DaoManager.createDao(connectionSource, CustomerCategory.class);
		}
		return customerCategoryDao;
	}

	@Override
	public Dao<ProductManufacturer, Integer> getProductManufacturerDao()
			throws SQLException {
		if (ProductManufacturerDao == null) {
			ProductManufacturerDao = DaoManager.createDao(connectionSource, ProductManufacturer.class);
		}
		return ProductManufacturerDao;
	}

	@Override
	public Dao<ProductBrand, Integer> getProductBrandDao() throws SQLException {
		if (ProductBrandDao == null) {
			ProductBrandDao = DaoManager.createDao(connectionSource, ProductBrand.class);
		}
		return ProductBrandDao;
	}

	@Override
	public Dao<ProductCategory, Integer> getProductCategoryDao()
			throws SQLException {
		if (ProductCategoryDao == null) {
			ProductCategoryDao = DaoManager.createDao(connectionSource, ProductCategory.class);
		}
		return ProductCategoryDao;
	}

	@Override
	public Dao<ProductFamily, Integer> getProductFamilyDao()
			throws SQLException {
		if (ProductFamilyDao == null) {
			ProductFamilyDao = DaoManager.createDao(connectionSource, ProductFamily.class);
		}
		return ProductFamilyDao;
	}

	@Override
	public Dao<Product, Integer> getProductDao() throws SQLException {
		if (ProductDao == null) {
			ProductDao = DaoManager.createDao(connectionSource, Product.class);
		}
		return ProductDao;
	}

	@Override
	public Dao<OrderTemplateDetail, Integer> getOrderTemplateDetailDao()
			throws SQLException {
		if (OrderTemplateDetailDao == null) {
			OrderTemplateDetailDao = DaoManager.createDao(connectionSource, OrderTemplateDetail.class);
		}
		return OrderTemplateDetailDao;
	}

	@Override
	public Dao<OrderTemplate, Integer> getOrderTemplateDao()
			throws SQLException {
		if (OrderTemplateDao == null) {
			OrderTemplateDao = DaoManager.createDao(connectionSource, OrderTemplate.class);
		}
		return OrderTemplateDao;
	}

	@Override
	public Dao<Order, String> getOrderDao() throws SQLException {
		if (OrderDao == null) {
			OrderDao = DaoManager.createDao(connectionSource, Order.class);
		}
		return OrderDao;
	}

	@Override
	public Dao<OrderDetail, String> getOrderDetailDao() throws SQLException {
		if (OrderDetailsDao == null) {
			OrderDetailsDao = DaoManager.createDao(connectionSource, OrderDetail.class);
		}
		return OrderDetailsDao;
	}

	@Override
	public Dao<OrderTemplateThreshold, Integer> getOrderTemplateThresholdDao()
			throws SQLException {
		if (OrderTemplateThresholdDao == null) {
			OrderTemplateThresholdDao = DaoManager.createDao(connectionSource, OrderTemplateThreshold.class);
		}
		return OrderTemplateThresholdDao;
	}
	
	@Override
	public Dao<PromoGroup, Integer> getPromoGroupDao() throws SQLException {
		if (PromoGroupDao == null) {
			PromoGroupDao = DaoManager.createDao(connectionSource, PromoGroup.class);
		}
		return PromoGroupDao;
	}

	@Override
	public Dao<PromoPayTerm, Integer> getPromoPayTermDao() throws SQLException {
		if (PromoPayTermDao == null) {
			PromoPayTermDao = DaoManager.createDao(connectionSource, PromoPayTerm.class);
		}
		return PromoPayTermDao;
	}

	@Override
	public Dao<PromoThreshold, Integer> getPromoThresholdDao()
			throws SQLException {
		if (PromoThresholdDao == null) {
			PromoThresholdDao = DaoManager.createDao(connectionSource, PromoThreshold.class);
		}
		return PromoThresholdDao;
	}

	@Override
	public Dao<OrderTemplateThresholdDetail, Integer> getOrdersTemplateThresholdDetailsDao()
			throws SQLException {
		if (OrdersTemplateThresholdDetailsDao == null) {
			OrdersTemplateThresholdDetailsDao = DaoManager.createDao(connectionSource, OrderTemplateThresholdDetail.class);
		}
		return OrdersTemplateThresholdDetailsDao;
	}

	@Override
	public Dao<PromoGroupDetail, Integer> getPromoGroupDetailDao()
			throws SQLException {
		if (PromoGroupDetailDao == null) {
			PromoGroupDetailDao = DaoManager.createDao(connectionSource, PromoGroupDetail.class);
		}
		return PromoGroupDetailDao;
	}

	@Override
	public Dao<PromoPayTermDetail, Integer> getPromoPayTermsDetailDao()
			throws SQLException {
		if (PromoPayTermsDetailDao == null) {
			PromoPayTermsDetailDao = DaoManager.createDao(connectionSource, PromoPayTermDetail.class);
		}
		return PromoPayTermsDetailDao;
	}

	@Override
	public Dao<PromoThresholdDetail, Integer> getPromoThresholdDetailDao()
			throws SQLException {
		if (PromoThresholdDetailDao == null) {
			PromoThresholdDetailDao = DaoManager.createDao(connectionSource, PromoThresholdDetail.class);
		}
		return PromoThresholdDetailDao;
	}
	
	@Override
	public Dao<Outbound, Long> getOutboundDao()
			throws SQLException {
		if (OutboundDao == null) {
			OutboundDao = DaoManager.createDao(connectionSource, Outbound.class);
		}
		return OutboundDao;
	}

	@Override
	public Dao<User, Integer> getUserDao() throws SQLException {
		if (UserDao == null) {
			UserDao = DaoManager.createDao(connectionSource, User.class);
		}
		return UserDao;
	}

}
