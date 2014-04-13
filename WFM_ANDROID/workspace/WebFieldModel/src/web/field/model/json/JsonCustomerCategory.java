package web.field.model.json;

public class JsonCustomerCategory {
	private long CustomerCategoryId;

	private long TenantId;

	private String CategoryDescription;

	private boolean FlagValid;

	private long CreateUserId;

	private long CreateDate;

	private long ModifiedUserId;

	private long ModifiedDate;

	public long getCustomerCategoryId() {
		return CustomerCategoryId;
	}

	public void setCustomerCategoryId(long customerCategoryId) {
		CustomerCategoryId = customerCategoryId;
	}

	public long getTenantId() {
		return TenantId;
	}

	public void setTenantId(long tenantId) {
		TenantId = tenantId;
	}

	public String getCategoryDescription() {
		return CategoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}

	public boolean isFlagValid() {
		return FlagValid;
	}

	public void setFlagValid(boolean flagValid) {
		FlagValid = flagValid;
	}

	public long getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(long createUserId) {
		CreateUserId = createUserId;
	}

	public long getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}

	public long getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

}
