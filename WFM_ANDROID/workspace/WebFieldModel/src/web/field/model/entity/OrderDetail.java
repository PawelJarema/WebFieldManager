package web.field.model.entity;

import web.field.logic.IVisited;
import web.field.logic.IVisitor;

import com.j256.ormlite.field.DatabaseField;

public class OrderDetail implements IVisited {

	@DatabaseField(id = true, generatedId = false)
	private String OrderDetailTempId;

	@DatabaseField
	private int OrderDetailId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private int OrderId;

	@DatabaseField
	private int Qty;

	@DatabaseField
	private int FreeQty;

	@DatabaseField
	private double Discount;

	@DatabaseField
	private double Price;
	
	@DatabaseField
	private long CreateDate;
	
	@DatabaseField
	private long ModifiedDate;
	
	@DatabaseField
	private int CreateUserId;
	
	@DatabaseField
	private int ModifiedUserId;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Order Order;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Product Product;

	public OrderDetail() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getOrderDetailId() {
		return OrderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		OrderDetailId = orderDetailId;
	}

	public String getOrderDetailTempId() {
		return OrderDetailTempId;
	}

	public void setOrderDetailTempId(String orderDetailTempId) {
		OrderDetailTempId = orderDetailTempId;
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

	public Order getOrder() {
		return Order;
	}

	public void setOrder(Order order) {
		Order = order;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	public long getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public int getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(int createUserId) {
		CreateUserId = createUserId;
	}

	public int getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}
}
