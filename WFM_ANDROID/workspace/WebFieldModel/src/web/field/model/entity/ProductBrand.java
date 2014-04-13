package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class ProductBrand {

	@DatabaseField(id = true, generatedId = false)
	private int ProductBrandId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String BrandDescription;

	@DatabaseField
	private boolean FlagValid;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Product> products;

	public ProductBrand() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getProductBrandId() {
		return ProductBrandId;
	}

	public void setProductBrandId(int productBrandId) {
		ProductBrandId = productBrandId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getBrandDescription() {
		return BrandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		BrandDescription = brandDescription;
	}

	public boolean isFlagValid() {
		return FlagValid;
	}

	public void setFlagValid(boolean flagValid) {
		FlagValid = flagValid;
	}

}
