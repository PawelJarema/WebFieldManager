package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonCustomer {
	private int CustomerId;

	private int TenantId;

	private String CustomerName;

	private String VatId;

	private Integer CustomerCategoryId;

	private boolean FlagTemp;

	private long CreateUserId;

	private long CreateDate;

	private long ModifiedUserId;

	private long ModifiedDate;

	private long StartDate;

	private long EndDate;
	
	private String CustomerERPCode;

	private List<JsonCustomerAddress> CustomerAddress = new ArrayList<JsonCustomerAddress>();

	private JsonCustomerCategory CustomerCategory;

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getVatId() {
		return VatId;
	}

	public void setVatId(String vatId) {
		VatId = vatId;
	}

	public Integer getCustomerCategoryId() {
		return CustomerCategoryId;
	}

	public void setCustomerCategoryId(Integer customerCategoryId) {
		CustomerCategoryId = customerCategoryId;
	}

	public boolean isFlagTemp() {
		return FlagTemp;
	}

	public void setFlagTemp(boolean flagTemp) {
		FlagTemp = flagTemp;
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

	public long getStartDate() {
		return StartDate;
	}

	public void setStartDate(long startDate) {
		StartDate = startDate;
	}

	public long getEndDate() {
		return EndDate;
	}

	public void setEndDate(long endDate) {
		EndDate = endDate;
	}

	public List<JsonCustomerAddress> getCustomerAddress() {
		return CustomerAddress;
	}

	public void setCustomerAddress(List<JsonCustomerAddress> customerAddress) {
		CustomerAddress = customerAddress;
	}

	public JsonCustomerCategory getCustomerCategory() {
		return CustomerCategory;
	}

	public void setCustomerCategory(JsonCustomerCategory customerCategory) {
		CustomerCategory = customerCategory;
	}

	public String getCustomerERPCode() {
		return CustomerERPCode;
	}

	public void setCustomerERPCode(String customerERPCode) {
		CustomerERPCode = customerERPCode;
	}
}
