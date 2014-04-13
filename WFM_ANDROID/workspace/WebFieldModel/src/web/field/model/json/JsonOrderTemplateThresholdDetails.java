package web.field.model.json;

public class JsonOrderTemplateThresholdDetails {
	private int OrderTemplateThresholdDetailId;
	private int TenantId;
	private int OrderTemplateThresholdId;
	private double OrderTotal;
	private double Discount;
	private boolean FlagCanceled;
	private long CreateDate;
	private int CreateUserId;
	private long ModifiedDate;
	private int ModifiedUserId;

	public int getOrderTemplateThresholdDetailId() {
		return OrderTemplateThresholdDetailId;
	}

	public void setOrderTemplateThresholdDetailId(
			int orderTemplateThresholdDetailId) {
		OrderTemplateThresholdDetailId = orderTemplateThresholdDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public int getOrderTemplateThresholdId() {
		return OrderTemplateThresholdId;
	}

	public void setOrderTemplateThresholdId(int orderTemplateThresholdId) {
		OrderTemplateThresholdId = orderTemplateThresholdId;
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

}
