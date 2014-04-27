package web.field.order.processing;

import java.util.Arrays;
import java.util.List;

import web.field.model.entity.*;

public class OrderCalculationRequest {

	private Order order;
	private List<OrderDetail> orderDetails;

	private OrderCache orderCashe;
	
	

	public OrderCalculationRequest(Order order, OrderCache orderCashe) {
		super();
		this.order = order;
		this.orderCashe = orderCashe;
		
		OrderDetail[] orderDetailsArr = order
				.getOrderDetails().toArray(
						new OrderDetail[] {});
		this.setOrderDetails(Arrays.asList(orderDetailsArr));
		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderCache getOrderCashe() {
		return orderCashe;
	}

	public void setOrderCashe(OrderCache orderCashe) {
		this.orderCashe = orderCashe;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
