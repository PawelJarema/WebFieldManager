package web.field.model.entity.adapter;

import java.util.*;

import web.field.model.entity.*;

public class OrderModelAdapter {
	private Order order;
	private List<OrderDetailModelAdapter> orderDetails = new ArrayList<OrderDetailModelAdapter>();

	public OrderModelAdapter(Order order, OrderTemplate orderTemplate) {
		this.order = order;
		
		OrderDetail[] orderDetailsArr = order.getOrderDetails().toArray(
				new OrderDetail[] {});
		List<OrderDetail> orderDetailsTmp = new ArrayList<OrderDetail>(
				Arrays.asList(orderDetailsArr));
		
		for(OrderDetail orderDetail : orderDetailsTmp){
			OrderTemplateDetail orderTemplateDetail = null;
			for(OrderTemplateDetail det : orderTemplate.getOrdersTemplateDetails()){
				if(orderDetail.getProduct().getProductId() == det.getProductId()){
					orderTemplateDetail = det;
				}
			}
			orderDetails.add(new OrderDetailModelAdapter(this, orderDetail, orderTemplateDetail));
		}
	}

	private List<String> validationErrors = new ArrayList<>();

	public int getOrderId() {
		return order.getOrderId();
	}

	public int getTenantId() {
		return order.getTenantId();
	}

	public int getUserId() {
		return order.getUserId();
	}

	public long getOrderDate() {
		return order.getOrderDate();
	}

	public long getDeliveryDate() {
		return order.getDeliveryDate();
	}

	public boolean isFlagValid() {
		return order.isFlagValid();
	}

	public void setFlagValid(boolean flagValid) {
		order.setFlagValid(flagValid);
	}

	public boolean isFlagError() {
		return order.isFlagError();
	}

	public void setFlagError(boolean flagError) {
		order.setFlagError(flagError);
	}

	public String getComment() {
		return order.getComment();
	}

	public String getOrderTempId() {
		return order.getOrderTempId();
	}

	public List<OrderDetailModelAdapter> getOrderDetails() {
		return orderDetails;
	}

	public int getStatus() {
		return order.getStatus();
	}

	public void setStatus(int status) {
		order.setStatus(status);
	}

	public double getDiscountHeaderTemplate() {
		return order.getDiscountHeaderTemplate();
	}

	public void setDiscountHeaderTemplate(double discountHeaderTemplate) {
		order.setDiscountHeaderTemplate(discountHeaderTemplate);
	}

	public double getDiscountHeaderThreshold() {
		return order.getDiscountHeaderThreshold();
	}

	public void setDiscountHeaderThreshold(double discountHeaderThreshold) {
		order.setDiscountHeaderThreshold(discountHeaderThreshold);
	}

	public double getDiscountHeaderPayterms() {
		return order.getDiscountHeaderPayterms();
	}

	public void setDiscountHeaderPayterms(double discountHeaderPayterms) {
		order.setDiscountHeaderPayterms(discountHeaderPayterms);
	}

	public long getCreateDate() {
		return order.getCreateDate();
	}

	public int getCreateUserId() {
		return order.getCreateUserId();
	}

	public long getModifiedDate() {
		return order.getModifiedDate();
	}

	public int getModifiedUserId() {
		return order.getModifiedUserId();
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
