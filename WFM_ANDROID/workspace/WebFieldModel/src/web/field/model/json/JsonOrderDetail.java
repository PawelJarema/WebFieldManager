package web.field.model.json;

import web.field.model.entity.OrderDetail;


public class JsonOrderDetail {
	private int OrderDetailId;
	private int TenantId;
	private int OrderId;
	private int ProductId;
	private int Qty;
	private int FreeQty;
	private double Discount;
	private double Price;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;
	private String OrderTempId;
	private String OrderDetailTempId;
	
	public JsonOrderDetail(){
	
	}
	
	public JsonOrderDetail(OrderDetail orderDetail){
		OrderDetailId = orderDetail.getOrderDetailId();
		TenantId = orderDetail.getTenantId();
		OrderId = orderDetail.getOrderId();
		ProductId = orderDetail.getProduct().getProductId();
		Qty = orderDetail.getQty();
		FreeQty = orderDetail.getFreeQty();
		Discount = orderDetail.getDiscount();
		Price = orderDetail.getPrice();
		CreateUserId = orderDetail.getCreateUserId();
		CreateDate = orderDetail.getCreateDate();
		ModifiedUserId = orderDetail.getModifiedUserId();
		ModifiedDate = orderDetail.getModifiedDate();
		OrderTempId = orderDetail.getOrder().getOrderTempId();
		OrderDetailTempId = orderDetail.getOrderDetailTempId();
	}

	public int getOrderDetailId() {
		return OrderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		OrderDetailId = orderDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public int getFreeQty() {
		return FreeQty;
	}

	public void setFreeQty(int freeQty) {
		FreeQty = freeQty;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public int getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(int createUserId) {
		CreateUserId = createUserId;
	}

	public long getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}

	public int getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public String getOrderTempId() {
		return OrderTempId;
	}

	public void setOrderTempId(String orderTempId) {
		OrderTempId = orderTempId;
	}

	public String getOrderDetailTempId() {
		return OrderDetailTempId;
	}

	public void setOrderDetailTempId(String orderDetailTempId) {
		OrderDetailTempId = orderDetailTempId;
	}

}
