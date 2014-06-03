package web.field.db;

import java.util.List;

import web.field.model.entity.*;
import web.field.model.json.*;
import web.field.model.simple.*;

public interface IDBAdapter {

	/***
	 * Gets full customer data
	 * 
	 * @param customerId
	 *            Customer id
	 * @return Customer object
	 */
	public Customer getCustomer(int customerId);

	/***
	 * Lists basic customers data
	 * 
	 * @return Customers data
	 */
	public List<CustomerSimple> listCustomers();

	/***
	 * Lists basic products data
	 * 
	 * @return Customers data
	 */
	public List<ProductSimple> listProductsSimple();
	
	/***
	 * Lists full products data
	 * 
	 * @return Customers data
	 */
	public List<Product> listProductsFull();

	/***
	 * Lists basic order templates data
	 * 
	 * @return Customers data
	 */
	public List<OrderTemplateSimple> listOrderTemplates();
	
	/**
	 * Gets full order template data
	 * @param id Order template id
	 * @return Full order template data
	 */
	public OrderTemplate getOrderTemplate(int id);

	/**
	 * Inserts customers to database
	 * 
	 * @param customers
	 */
	public void loadCustomers(List<JsonCustomer> customers);

	/**
	 * Inserts products
	 * 
	 * @param products
	 */
	public void loadProducts(List<JsonProduct> products);

	/**
	 * Inserts order templates
	 * 
	 * @param orderTemplates
	 */
	public void loadOrderTemplates(List<JsonOrderTemplate> orderTemplates);

	/**
	 * Inserts orders
	 * 
	 * @param answer
	 */
	public void loadOrders(List<JsonOrder> orders);

	/**
	 * Lists orders
	 * 
	 * @return
	 */
	public List<OrderSimple> listOrders(Integer customerId);
	public List<OrderSimple> listDraftOrders(Integer customerId);
	public Order getOrder(String tmpId);
	public Order getOrder(int id);
	
	public List<OrdersTemplateThresholdSimple> listOrderTemplatesThresholds();
	public List<PromoGroupSimple> listPromoGroups();
	public List<PromoPayTermSimple> listPromoPayTerms();
	public List<PromoThresholdSimple> listPromoTresholdSimple();
	
	public void loadOrderTemplateThresholds(List<JsonOrderTemplateThreshold> thresholds);
	public void loadPromoGroups(List<JsonPromoGroup> promoGroups);
	public void loadPromoPayTerms(List<JsonPromoPayTerm> promoPayTerms);
	public void loadPromoThreshold(List<JsonPromoThreshold> promoThresholds);
	
	public Order saveOrder(Order order);
	public Order saveOrderFromTemplate(Order order, OrderTemplate orderTemplate);
	public OrderTemplateThreshold getOrderTemplateThreshold(int id);
	public PromoGroup getPromoGroup(int id);
	public PromoPayTerm getPromoPayTerm(int id);
	public PromoThreshold getPromoThreshold(int id);
	
	public Outbound saveOutbound(Outbound outbound);
	public List<CustomerSimple> listCustomersNearMe(double latitude, double longitude);
	public User getUser(String token);
	public void loadUsers(List<JsonUser> users);
	
	public List<PromoPayTermDetail> getPromoPayTermDetails();
	
	public List<ProductFamilySimple> listProductFamilies();
	public List<ProductCategorySimple> listProductCategories();
	public List<ProductManufacturerSimple> listProductManufactures();
	public List<ProductBrandSimple> listProductBrands();
	
}
