package web.field.order.processing;

public class OrderCalculationDetaiResult {
	private int productId;
	private boolean belowMinValue;
	private boolean aboweMaxValue;
	private double discount;
	private double freeQuantity;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean isBelowMinValue() {
		return belowMinValue;
	}

	public void setBelowMinValue(boolean belowMinValue) {
		this.belowMinValue = belowMinValue;
	}

	public boolean isAboweMaxValue() {
		return aboweMaxValue;
	}

	public void setAboweMaxValue(boolean aboweMaxValue) {
		this.aboweMaxValue = aboweMaxValue;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFreeQuantity() {
		return freeQuantity;
	}

	public void setFreeQuantity(double freeQuantity) {
		this.freeQuantity = freeQuantity;
	}
}
