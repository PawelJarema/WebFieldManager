package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class OrderTemplateThreshold {
	@DatabaseField(id = true, generatedId = false)
	private int OrdersTemplateThresholdId;
	@DatabaseField
	private int TenantId;
	@DatabaseField
	private String Name;
	@DatabaseField
	private boolean FlagActive;
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

	@ForeignCollectionField(eager = false)
	private ForeignCollection<OrderTemplateThresholdDetail> OrdersTemplateThresholdDetails;
	
	public OrderTemplateThreshold() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getOrdersTemplateThresholdId() {
		return OrdersTemplateThresholdId;
	}

	public void setOrdersTemplateThresholdId(int ordersTemplateThresholdId) {
		OrdersTemplateThresholdId = ordersTemplateThresholdId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isFlagActive() {
		return FlagActive;
	}

	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
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

	public ForeignCollection<OrderTemplateThresholdDetail> getOrdersTemplateThresholdDetails() {
		return OrdersTemplateThresholdDetails;
	}

	public void setOrdersTemplateThresholdDetails(
			ForeignCollection<OrderTemplateThresholdDetail> ordersTemplateThresholdDetails) {
		OrdersTemplateThresholdDetails = ordersTemplateThresholdDetails;
	}
	
	

}
