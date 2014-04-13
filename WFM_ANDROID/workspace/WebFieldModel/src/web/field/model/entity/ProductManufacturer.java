package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class ProductManufacturer {

	@DatabaseField(id = true, generatedId = false)
	private int ProductManufacturerId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String ManufacturerDescription;

	@DatabaseField
	private boolean FlagValid;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Product> products;

	public ProductManufacturer() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

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
}
