package web.field.order.processing;

import java.util.List;

public class OrderCalculationResult {
	
	private List<OrderCalculationDetaiResult> calculationDetails;
	
	private double subTotal;
	private double totalDiscounts;
	private double promoSum;
	
	private double orderTemplateThresholdDiscount;
	private double fullValue;
	
	private double orderTotal;

	

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotalDiscounts() {
		return totalDiscounts;
	}

	public void setTotalDiscounts(double totalDiscounts) {
		this.totalDiscounts = totalDiscounts;
	}

	public double getPromoSum() {
		return promoSum;
	}

	public void setPromoSum(double promoSum) {
		this.promoSum = promoSum;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public List<OrderCalculationDetaiResult> getCalculationDetails() {
		return calculationDetails;
	}

	public void setCalculationDetails(List<OrderCalculationDetaiResult> calculationDetails) {
		this.calculationDetails = calculationDetails;
	}

	public double getOrderTemplateThresholdDiscount() {
		return orderTemplateThresholdDiscount;
	}

	public void setOrderTemplateThresholdDiscount(
			double orderTemplateThresholdDiscount) {
		this.orderTemplateThresholdDiscount = orderTemplateThresholdDiscount;
	}

	public double getFullValue() {
		return fullValue;
	}

	public void setFullValue(double fullValue) {
		this.fullValue = fullValue;
	}


	
	
	
}
