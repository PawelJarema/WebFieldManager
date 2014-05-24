package web.field.model.entity.adapter;

import web.field.model.entity.*;

public class OrderDetailModelAdapter {
	private OrderDetail orderDetail;
	private OrderModelAdapter orderAdapter;
	private OrderTemplateDetail orderTemplateDetail;

	public OrderDetailModelAdapter(OrderModelAdapter orderAdapter,
			OrderDetail orderDetail, OrderTemplateDetail orderTemplateDetail) {
		this.orderDetail = orderDetail;
		this.orderAdapter = orderAdapter;
		this.orderTemplateDetail = orderTemplateDetail;
		
		if(this.orderTemplateDetail != null){
			orderDetail.setQty(this.orderTemplateDetail.getQtyProposal());
		}
	}

	private String lineError;

	public int getOrderDetailId() {
		return orderDetail.getOrderDetailId();
	}

	public void setOrderDetailId(int orderDetailId) {
		orderDetail.setOrderDetailId(orderDetailId);
	}

	public String getOrderDetailTempId() {
		return orderDetail.getOrderDetailTempId();
	}

	public void setOrderDetailTempId(String orderDetailTempId) {
		orderDetail.setOrderDetailTempId(orderDetailTempId);
	}

	public int getTenantId() {
		return orderDetail.getTenantId();
	}

	public void setTenantId(int tenantId) {
		orderDetail.setTenantId(tenantId);
	}

	public int getOrderId() {
		return orderDetail.getOrderId();
	}

	public void setOrderId(int orderId) {
		orderDetail.setOrderId(orderId);
	}

	public int getQty() {
		return orderDetail.getQty();
	}

	public void setQty(int qty) {
		orderDetail.setQty(qty);
	}

	public int getFreeQty() {
		return orderDetail.getFreeQty();
	}

	public void setFreeQty(int freeQty) {
		orderDetail.setFreeQty(freeQty);
	}

	public double getDiscount() {
		return orderDetail.getDiscount();
	}

	public void setDiscount(double discount) {
		orderDetail.setDiscount(discount);
	}

	public double getPrice() {
		return orderDetail.getPrice();
	}

	public void setPrice(double price) {
		orderDetail.setPrice(price);
	}

	public OrderModelAdapter getOrderAdapter() {
		return orderAdapter;
	}

	public Product getProduct() {
		return orderDetail.getProduct();
	}

	public long getCreateDate() {
		return orderDetail.getCreateDate();
	}

	public long getModifiedDate() {
		return orderDetail.getModifiedDate();
	}

	public int getCreateUserId() {
		return orderDetail.getCreateUserId();
	}

	public int getModifiedUserId() {
		return orderDetail.getModifiedUserId();
	}

	public String getLineError() {
		return lineError;
	}

	public void setLineError(String lineError) {
		this.lineError = lineError;
	}

	public Integer getQtyMin() {
		if (orderTemplateDetail != null) {
			return orderTemplateDetail.getQtyMin();
		}
		return null;
	}

	public Integer getQtyMax() {
		if (orderTemplateDetail != null) {
			return orderTemplateDetail.getQtyMax();
		}
		return null;
	}

	public Integer getQtyProposal() {
		if (orderTemplateDetail != null) {
			return orderTemplateDetail.getQtyProposal();
		}
		return null;
	}

	public Integer getQtyMultiples() {
		if (orderTemplateDetail != null) {
			return orderTemplateDetail.getQtyMultiples();
		}
		return null;
	}

}
