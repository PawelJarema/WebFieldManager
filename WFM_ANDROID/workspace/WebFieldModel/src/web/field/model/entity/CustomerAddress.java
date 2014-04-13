package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class CustomerAddress {

	@DatabaseField(id = true)
	private int CustomerAddressId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String CustomerAddressDescription;

	@DatabaseField
	private String Street;

	@DatabaseField
	private int CodeCountryId;

	@DatabaseField
	private String District;

	@DatabaseField
	private String City;

	@DatabaseField
	private String PostalCode;

	@DatabaseField
	private String Phone1;

	@DatabaseField
	private String Phone2;

	@DatabaseField
	private String Fax;

	@DatabaseField
	private String Email;

	@DatabaseField
	private boolean ShipToFlag;

	@DatabaseField
	private boolean BillToFlag;

	@DatabaseField
	private long StartDate;

	@DatabaseField
	private long EndDate;

	@DatabaseField
	private Double Latitude;

	@DatabaseField
	private Double Longitude;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Customer customer;
	
	@DatabaseField
	private String ERPCode;

	public CustomerAddress() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String fullAddress() {
		StringBuilder builder = new StringBuilder();
		builder.append(Street);
		builder.append(" ");
		builder.append(District);
		builder.append(" ");
		builder.append(City);
		builder.append(" ");
		builder.append(PostalCode);
		return builder.toString();
	}

	public String getERPCode() {
		return ERPCode;
	}

	public void setERPCode(String eRPCode) {
		ERPCode = eRPCode;
	}

}
