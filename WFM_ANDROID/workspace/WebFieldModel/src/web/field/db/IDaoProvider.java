package web.field.db;

import java.sql.SQLException;

import web.field.model.entity.*;

import com.j256.ormlite.dao.Dao;

public interface IDaoProvider {
	public Dao<Customer, Integer> getCustomerDao() throws SQLException;

	public Dao<CustomerAddress, Integer> getCustomerAddressDao()
			throws SQLException;

	public Dao<CustomerCategory, Integer> getCustomerCategoryDao()
			throws SQLException;

	public Dao<ProductManufacturer, Integer> getProductManufacturerDao()
			throws SQLException;

	public Dao<ProductBrand, Integer> getProductBrandDao() throws SQLException;

	public Dao<ProductCategory, Integer> getProductCategoryDao()
			throws SQLException;

	public Dao<ProductFamily, Integer> getProductFamilyDao()
			throws SQLException;

	public Dao<Product, Integer> getProductDao() throws SQLException;

	public Dao<OrderTemplateDetail, Integer> getOrderTemplateDetailDao()
			throws SQLException;

	public Dao<OrderTemplate, Integer> getOrderTemplateDao()
			throws SQLException;

	public Dao<Order, String> getOrderDao() throws SQLException;

	public Dao<OrderDetail, String> getOrderDetailDao() throws SQLException;

	public Dao<OrderTemplateThreshold, Integer> getOrderTemplateThresholdDao()
			throws SQLException;

	public Dao<PromoGroup, Integer> getPromoGroupDao() throws SQLException;

	public Dao<PromoPayTerm, Integer> getPromoPayTermDao() throws SQLException;

	public Dao<PromoThreshold, Integer> getPromoThresholdDao()
			throws SQLException;

	public Dao<OrderTemplateThresholdDetail, Integer> getOrdersTemplateThresholdDetailsDao()
			throws SQLException;

	public Dao<PromoGroupDetail, Integer> getPromoGroupDetailDao()
			throws SQLException;

	public Dao<PromoPayTermDetail, Integer> getPromoPayTermsDetailDao()
			throws SQLException;

	public Dao<PromoThresholdDetail, Integer> getPromoThresholdDetailDao()
			throws SQLException;

	public Dao<Outbound, Long> getOutboundDao() throws SQLException;
	
	public Dao<User, Integer> getUserDao() throws SQLException;

}
