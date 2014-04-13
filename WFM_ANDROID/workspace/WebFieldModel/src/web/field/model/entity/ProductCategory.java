package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class ProductCategory {

	@DatabaseField(id = true, generatedId = false)
	private int ProductCategoryId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String CategoryDescription;

	@DatabaseField
	private boolean FlagValid;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Product> products;

	public ProductCategory() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getProductCategoryId() {
		return ProductCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		ProductCategoryId = productCategoryId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
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
