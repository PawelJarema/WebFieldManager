package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * Customer category entity
 * 
 * @author Bro
 * 
 */
public class CustomerCategory {

	@DatabaseField(id = true)
	private int CustomerCategoryId;

	@DatabaseField
	private long TenantId;

	@DatabaseField
	private String CategoryDescription;

	@DatabaseField
	private boolean FlagValid;

	public CustomerCategory() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getCustomerCategoryId() {
		return CustomerCategoryId;
	}

	public void setCustomerCategoryId(int customerCategoryId) {
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

}
