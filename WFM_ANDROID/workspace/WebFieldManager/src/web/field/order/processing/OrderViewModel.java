package web.field.order.processing;

import java.util.*;

import web.field.model.entity.*;

public class OrderViewModel {
	private OrderTemplate orderTemplate;
	private List<OrderTemplateDetail> orderTemplateDetails = new ArrayList<OrderTemplateDetail>();
	private List<PromoPayTermDetail> promoPayTermDetails = new ArrayList<PromoPayTermDetail>();
	private OrderTemplateThreshold orderTemplateThreshold;
	private List<OrderTemplateThresholdDetail> orderTemplateThresholdDetails = new ArrayList<OrderTemplateThresholdDetail>();
	private PromoThreshold promoThreshold;
	private List<PromoThresholdDetail> promoThresholdDetails = new ArrayList<PromoThresholdDetail>();

	private Map<Integer, OrderTemplateDetail> templateByProduct = new Hashtable<Integer, OrderTemplateDetail>();
	private Map<Integer, List<PromoThresholdDetail>> promoThresholdsByProduct = new Hashtable<Integer, List<PromoThresholdDetail>>();

	public OrderTemplate getOrderTemplate() {
		return orderTemplate;
	}

	public void setOrderTemplate(OrderTemplate orderTemplate) {
		this.orderTemplate = orderTemplate;
	}

	public List<OrderTemplateDetail> getOrderTemplateDetails() {
		return orderTemplateDetails;
	}

	public void setOrderTemplateDetails(
			List<OrderTemplateDetail> orderTemplateDetails) {
		this.orderTemplateDetails = orderTemplateDetails;
	}

	public List<PromoPayTermDetail> getPromoPayTermDetails() {
		return promoPayTermDetails;
	}

	public void setPromoPayTermDetails(
			List<PromoPayTermDetail> promoPayTermDetails) {
		this.promoPayTermDetails = promoPayTermDetails;
	}

	public OrderTemplateThreshold getOrderTemplateThreshold() {
		return orderTemplateThreshold;
	}

	public void setOrderTemplateThreshold(
			OrderTemplateThreshold orderTemplateThreshold) {
		this.orderTemplateThreshold = orderTemplateThreshold;
	}

	public List<OrderTemplateThresholdDetail> getOrderTemplateThresholdDetails() {
		return orderTemplateThresholdDetails;
	}

	public void setOrderTemplateThresholdDetails(
			List<OrderTemplateThresholdDetail> orderTemplateThresholdDetails) {
		this.orderTemplateThresholdDetails = orderTemplateThresholdDetails;
	}

	public PromoThreshold getPromoThreshold() {
		return promoThreshold;
	}

	public void setPromoThreshold(PromoThreshold promoThreshold) {
		this.promoThreshold = promoThreshold;
	}

	public List<PromoThresholdDetail> getPromoThresholdDetails() {
		return promoThresholdDetails;
	}

	public void setPromoThresholdDetails(
			List<PromoThresholdDetail> promoThresholdDetails) {
		this.promoThresholdDetails = promoThresholdDetails;
	}

	public OrderTemplateDetail getOrderTemplateDetailForOrderDetail(
			OrderDetail orderDetail) {
		OrderTemplateDetail result = null;
		int productId = orderDetail.getProduct().getProductId();

		// try to get from map
		if (templateByProduct.containsKey(productId)) {
			result = templateByProduct.get(productId);
		} else {
			// find in collection and add to map
			for (OrderTemplateDetail templateDetail : this.orderTemplateDetails) {
				if (templateDetail.getProductId() == productId) {
					result = templateDetail;
					templateByProduct.put(productId, result);
					return result;
				}
			}
		}

		return result;
	}

	public OrderTemplateThresholdDetail getOrderTemplateThresholdDetailForOrder(
			double orderTotal) {
		OrderTemplateThresholdDetail result = null;
		

		return result;
	}

	public PromoThresholdDetail getPromoThresholdDetailForOrderDetail(
			OrderDetail detail) {
		PromoThresholdDetail result = null;
		int productId = detail.getProduct().getProductId();
		double productPrice = detail.getProduct().getPrice();
		List<PromoThresholdDetail> promoThresholdDetails = null;
		
		// get by product
		if(promoThresholdsByProduct.containsKey(productId)){
			promoThresholdDetails = promoThresholdsByProduct.get(productId);
		}
		else{
			promoThresholdDetails = new ArrayList<PromoThresholdDetail>();
			for(PromoThresholdDetail thresholdDetail : this.promoThresholdDetails){
				if(thresholdDetail.getProduct().getProductId() == productId){
					promoThresholdDetails.add(thresholdDetail);
				}
			}
			promoThresholdsByProduct.put(productId, promoThresholdDetails);
		}
		
		// get basing on detail value
		int qty = detail.getQty();
		double detailValue = productPrice * qty;
		for(PromoThresholdDetail thresholdDetail : promoThresholdDetails){
			int minValue = thresholdDetail.getThresholdMinValue();
			int maxValue = thresholdDetail.getThresholdMaxValue();
			if(detailValue > minValue  && detailValue < maxValue){
				result = thresholdDetail;
				return result;
			}
		}
		
		return result;
	}

}
