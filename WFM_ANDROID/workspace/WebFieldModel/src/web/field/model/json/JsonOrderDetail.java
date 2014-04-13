package web.field.model.json;

import java.math.BigDecimal;

public class JsonOrderDetail {
	private int OrderDetailId;
	private int TenantId;
	private int OrderId;
	private int ProductId;
	private int Qty;
	private int FreeQty;
	private BigDecimal Discount;
	private BigDecimal Price;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;
	private String OrderTempId;
	private String OrderDetailTempId;

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

	public BigDecimal getDiscount() {
		return Discount;
	}

	public void setDiscount(BigDecimal discount) {
		Discount = discount;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
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
