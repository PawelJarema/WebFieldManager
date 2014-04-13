package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonOrderTemplateThreshold {
	private int OrderTemplateThresholdId;
	private int TenantId;
	private String Name;
	private boolean FlagActive;
	private boolean FlagCanceled;
	private long CreateDate;
	private int CreateUserId;
	private long ModifiedDate;
	private int ModifiedUserId;

	private List<JsonOrderTemplateThresholdDetails> OrderTemplateThresholdDetails = new ArrayList<JsonOrderTemplateThresholdDetails>();

	public int getOrderTemplateThresholdId() {
		return OrderTemplateThresholdId;
	}

	public void setOrderTemplateThresholdId(int orderTemplateThresholdId) {
		OrderTemplateThresholdId = orderTemplateThresholdId;
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

	public List<JsonOrderTemplateThresholdDetails> getOrderTemplateThresholdDetails() {
		return OrderTemplateThresholdDetails;
	}

	public void setOrderTemplateThresholdDetails(
			List<JsonOrderTemplateThresholdDetails> orderTemplateThresholdDetails) {
		OrderTemplateThresholdDetails = orderTemplateThresholdDetails;
	}

}
