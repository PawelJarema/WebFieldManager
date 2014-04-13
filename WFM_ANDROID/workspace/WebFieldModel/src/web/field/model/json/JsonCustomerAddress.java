package web.field.model.json;

public class JsonCustomerAddress {

	private int CustomerAddressId;

	private int TenantId;

	private int CustomerId;

	private String CustomerAddressDescription;

	private String Street;

	private int CodeCountryId;

	private String District;

	private String City;

	private String PostalCode;

	private String Phone1;

	private String Phone2;

	private String Fax;

	private String Email;

	private boolean ShipToFlag;

	private boolean BillToFlag;

	private long CreateUserId;

	private long CreateDate;

	private long ModifiedDate;

	private long ModifiedUserId;

	private long StartDate;

	private long EndDate;

	private Double Latitude;

	private Double Longitude;
	
	private String ERPCode;
	
	
	
	public Double getLatitude() {
		return Latitude;
	}

	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}

	public Double getLongitude() {
		return Longitude;
	}

	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

	public int getCustomerAddressId() {
		return CustomerAddressId;
	}

	public void setCustomerAddressId(int customerAddressId) {
		CustomerAddressId = customerAddressId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public String getCustomerAddressDescription() {
		return CustomerAddressDescription;
	}

	public void setCustomerAddressDescription(String customerAddressDescription) {
		CustomerAddressDescription = customerAddressDescription;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public int getCodeCountryId() {
		return CodeCountryId;
	}

	public void setCodeCountryId(int codeCountryId) {
		CodeCountryId = codeCountryId;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getPhone1() {
		return Phone1;
	}

	public void setPhone1(String phone1) {
		Phone1 = phone1;
	}

	public String getPhone2() {
		return Phone2;
	}

	public void setPhone2(String phone2) {
		Phone2 = phone2;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String fax) {
		Fax = fax;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isShipToFlag() {
		return ShipToFlag;
	}

	public void setShipToFlag(boolean shipToFlag) {
		ShipToFlag = shipToFlag;
	}

	public boolean isBillToFlag() {
		return BillToFlag;
	}

	public void setBillToFlag(boolean billToFlag) {
		BillToFlag = billToFlag;
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

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public long getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		ModifiedUserId = modifiedUserId;
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

	public String getERPCode() {
		return ERPCode;
	}

	public void setERPCode(String eRPCode) {
		ERPCode = eRPCode;
	}
}
