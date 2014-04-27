package web.field.order.processing;

import java.math.BigDecimal;
import java.util.List;

public class OrderCalculationResult {
	
	private List<OrderCalculationDetaiResult> calculationDetails;
	
	private double subTotal;
	private double totalDiscounts;
	private double promoSum;
	
	private BigDecimal orderTemplateThresholdDiscount;
	private BigDecimal fullValue;
	
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

	public BigDecimal getOrderTemplateThresholdDiscount() {
		return orderTemplateThresholdDiscount;
	}

	public void setOrderTemplateThresholdDiscount(
			BigDecimal orderTemplateThresholdDiscount) {
		this.orderTemplateThresholdDiscount = orderTemplateThresholdDiscount;
	}

	public BigDecimal getFullValue() {
		return fullValue;
	}

	public void setFullValue(BigDecimal fullValue) {
		this.fullValue = fullValue;
	}


	
	
	
}
