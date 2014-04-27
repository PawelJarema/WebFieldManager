package web.field.model.entity;

import java.math.BigDecimal;

import web.field.logic.IVisited;
import web.field.logic.IVisitor;

import com.j256.ormlite.field.DatabaseField;

public class OrderDetail implements IVisited{

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
	private BigDecimal Discount;

	@DatabaseField
	private BigDecimal Price;

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
}
