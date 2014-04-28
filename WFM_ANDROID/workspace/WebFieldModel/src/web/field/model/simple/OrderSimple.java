package web.field.model.simple;

public class OrderSimple {
	private String orderTempId;
	
	private int orderId;

	private String orderDate;

	private String orderSummary;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(String orderSummary) {
		this.orderSummary = orderSummary;
	}

	public String getOrderTempId() {
		return orderTempId;
	}

	public void setOrderTempId(String orderTempId) {
		this.orderTempId = orderTempId;
	}

}
