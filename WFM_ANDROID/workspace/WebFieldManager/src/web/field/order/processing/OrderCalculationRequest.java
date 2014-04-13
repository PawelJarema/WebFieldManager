package web.field.order.processing;

import java.util.List;

import web.field.model.entity.*;

public class OrderCalculationRequest {

	private Order order;
	private List<OrderDetail> orderDetails;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	private OrderCache orderCashe;

	public OrderCache getOrderCashe() {
		return orderCashe;
	}

	public void setOrderCashe(OrderCache orderCashe) {
		this.orderCashe = orderCashe;
	}

}
