package web.field.model.json;

public class JsonProductManufacturer {
	private int ProductManufacturerId;
	private int TenantId;
	private String ManufacturerDescription;
	private boolean FlagValid;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;

	public int getProductManufacturerId() {
		return ProductManufacturerId;
	}

	public void setProductManufacturerId(int productManufacturerId) {
		ProductManufacturerId = productManufacturerId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getManufacturerDescription() {
		return ManufacturerDescription;
	}

	public void setManufacturerDescription(String manufacturerDescription) {
		ManufacturerDescription = manufacturerDescription;
	}

	public boolean isFlagValid() {
		return FlagValid;
	}

	public void setFlagValid(boolean flagValid) {
		FlagValid = flagValid;
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

}
