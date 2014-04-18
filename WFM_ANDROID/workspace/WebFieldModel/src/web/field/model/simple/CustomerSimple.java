package web.field.model.simple;

public class CustomerSimple {
	private int customerId;
	private String customerName;
	private String vatId;
	private boolean active;
	
	public CustomerSimple() {
	
	}
	public CustomerSimple(int customerId, String customerName, String vatId,
			boolean active) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.vatId = vatId;
		this.active = active;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getVatId() {
		return vatId;
	}

	public void setVatId(String vatId) {
		this.vatId = vatId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
