package web.field.model.entity;

import java.math.BigDecimal;

import com.j256.ormlite.field.DatabaseField;

public class Product {

	@DatabaseField(id = true, generatedId = false)
	private int ProductId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String Code;

	@DatabaseField
	private String ProductDescription;

	@DatabaseField
	private BigDecimal Price;

	@DatabaseField
	private boolean FlagActive;

	@DatabaseField(foreign = true)
	private ProductBrand ProductBrands;

	@DatabaseField(foreign = true)
	private ProductCategory ProductCategory;

	@DatabaseField(foreign = true)
	private ProductFamily ProductFamily;

	@DatabaseField(foreign = true)
	private ProductManufacturer ProductManufacturer;

	public Product() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public ProductBrand getProductBrands() {
		return ProductBrands;
	}

	public void setProductBrands(ProductBrand productBrands) {
		ProductBrands = productBrands;
	}

	public ProductCategory getProductCategory() {
		return ProductCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		ProductCategory = productCategory;
	}

	public ProductFamily getProductFamily() {
		return ProductFamily;
	}

	public void setProductFamily(ProductFamily productFamilys) {
		ProductFamily = productFamilys;
	}

	public ProductManufacturer getProductManufacturer() {
		return ProductManufacturer;
	}

	public void setProductManufacturer(ProductManufacturer productManufacturers) {
		ProductManufacturer = productManufacturers;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getProductDescription() {
		return ProductDescription;
	}

	public void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
		Price = price;
	}

	public boolean isFlagActive() {
		return FlagActive;
	}

	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
	}

}
