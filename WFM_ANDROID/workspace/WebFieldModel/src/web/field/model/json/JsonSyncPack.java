package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonSyncPack {
	
	private List<JsonCustomer> Customers = new ArrayList<JsonCustomer>();
	private List<JsonOrder> Orders = new ArrayList<JsonOrder>();
	private List<JsonOrderTemplate> OrderTemplates = new ArrayList<JsonOrderTemplate>();
	private List<JsonOrderTemplateThreshold> OrderTemplateThresholds = new ArrayList<JsonOrderTemplateThreshold>();
	private List<JsonProduct> Products = new ArrayList<JsonProduct>();
	private List<JsonPromoGroup> PromoGroups = new ArrayList<JsonPromoGroup>();
	private List<JsonPromoPayTerm> PromoPayTerms = new ArrayList<JsonPromoPayTerm>();
	private List<JsonPromoThreshold> PromoThresholds = new ArrayList<JsonPromoThreshold>();

	public List<JsonCustomer> getCustomers() {
		return Customers;
	}

	public void setCustomers(List<JsonCustomer> customers) {
		Customers = customers;
	}

	public List<JsonOrder> getOrders() {
		return Orders;
	}

	public void setOrders(List<JsonOrder> orders) {
		Orders = orders;
	}

	public List<JsonOrderTemplate> getOrderTemplates() {
		return OrderTemplates;
	}

	public void setOrderTemplates(List<JsonOrderTemplate> orderTemplates) {
		OrderTemplates = orderTemplates;
	}

	public List<JsonOrderTemplateThreshold> getOrderTemplateThresholds() {
		return OrderTemplateThresholds;
	}

	public void setOrderTemplateThresholds(
			List<JsonOrderTemplateThreshold> orderTemplateThresholds) {
		OrderTemplateThresholds = orderTemplateThresholds;
	}

	public List<JsonProduct> getProducts() {
		return Products;
	}

	public void setProducts(List<JsonProduct> products) {
		Products = products;
	}

	public List<JsonPromoGroup> getPromoGroups() {
		return PromoGroups;
	}

	public void setPromoGroups(List<JsonPromoGroup> promoGroups) {
		PromoGroups = promoGroups;
	}

	public List<JsonPromoPayTerm> getPromoPayTerms() {
		return PromoPayTerms;
	}

	public void setPromoPayTerms(List<JsonPromoPayTerm> promoPayTerms) {
		PromoPayTerms = promoPayTerms;
	}

	public List<JsonPromoThreshold> getPromoThresholds() {
		return PromoThresholds;
	}

	public void setPromoThresholds(List<JsonPromoThreshold> promoThresholds) {
		PromoThresholds = promoThresholds;
	}

}
