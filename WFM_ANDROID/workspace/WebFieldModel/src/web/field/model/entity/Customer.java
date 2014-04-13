package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/***
 * Customer class
 * 
 * @author Adam
 * 
 */
public class Customer {

	@DatabaseField(id = true, generatedId = false)
	private int CustomerId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String CustomerName;

	@DatabaseField
	private String VatId;

	@DatabaseField
	private boolean FlagTemp;

	@DatabaseField
	private long StartDate;

	@DatabaseField
	private long EndDate;

	@ForeignCollectionField(eager = true)
	private ForeignCollection<CustomerAddress> addresses;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private CustomerCategory CustomerCategory;
	
	@DatabaseField
	private String CustomerERPCode;

	public Customer() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public boolean isActive() {
		long secs = System.currentTimeMillis() / 1000;
		return secs > StartDate && secs < EndDate;
	}

	public ForeignCollection<CustomerAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(ForeignCollection<CustomerAddress> addresses) {
		this.addresses = addresses;
	}

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

	public boolean isFlagTemp() {
		return FlagTemp;
	}

	public void setFlagTemp(boolean flagTemp) {
		FlagTemp = flagTemp;
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

	public CustomerCategory getCustomerCategory() {
		return CustomerCategory;
	}

	public void setCustomerCategory(CustomerCategory customerCategory) {
		CustomerCategory = customerCategory;
	}

	public String getCustomerERPCode() {
		return CustomerERPCode;
	}

	public void setCustomerERPCode(String customerERPCode) {
		CustomerERPCode = customerERPCode;
	}

}
