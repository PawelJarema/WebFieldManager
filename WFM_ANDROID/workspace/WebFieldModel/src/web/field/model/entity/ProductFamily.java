package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class ProductFamily {

	@DatabaseField(id = true, generatedId = false)
	private int ProductFamilyId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String FamilyDescription;

	@DatabaseField
	private boolean FlagValid;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Product> products;

	public ProductFamily() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public int getProductFamilyId() {
		return ProductFamilyId;
	}

	public void setProductFamilyId(int productFamilyId) {
		ProductFamilyId = productFamilyId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getFamilyDescription() {
		return FamilyDescription;
	}

	public void setFamilyDescription(String familyDescription) {
		FamilyDescription = familyDescription;
	}

	public boolean isFlagValid() {
		return FlagValid;
	}

	public void setFlagValid(boolean flagValid) {
		FlagValid = flagValid;
	}
}
