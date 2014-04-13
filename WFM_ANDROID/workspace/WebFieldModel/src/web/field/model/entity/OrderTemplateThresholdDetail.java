package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class OrderTemplateThresholdDetail {
	
	@DatabaseField(id = true, generatedId = false)
	private int OrdersTemplateThresholdDetailId;
	
	@DatabaseField
	private int TenantId;
	
	@DatabaseField
	private double OrderTotal;
	@DatabaseField
	private double Discount;
	@DatabaseField
	private boolean FlagCanceled;
	@DatabaseField
	private long CreateDate;
	@DatabaseField
	private int CreateUserId;
	@DatabaseField
	private long ModifiedDate;
	@DatabaseField
	private int ModifiedUserId;
	
	@DatabaseField(foreign = true)
	private OrderTemplateThreshold OrdersTemplateThreshold;
	
	public OrderTemplateThresholdDetail() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getOrdersTemplateThresholdDetailId() {
		return OrdersTemplateThresholdDetailId;
	}

	public void setOrdersTemplateThresholdDetailId(
			int ordersTemplateThresholdDetailId) {
		OrdersTemplateThresholdDetailId = ordersTemplateThresholdDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public double getOrderTotal() {
		return OrderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		OrderTotal = orderTotal;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public boolean isFlagCanceled() {
		return FlagCanceled;
	}

	public void setFlagCanceled(boolean flagCanceled) {
		FlagCanceled = flagCanceled;
	}

	public long getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}

	public int getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(int createUserId) {
		CreateUserId = createUserId;
	}

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public int getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}

	public OrderTemplateThreshold getOrdersTemplateThreshold() {
		return OrdersTemplateThreshold;
	}

	public void setOrdersTemplateThreshold(
			OrderTemplateThreshold ordersTemplateThreshold) {
		OrdersTemplateThreshold = ordersTemplateThreshold;
	}
	
	
}
