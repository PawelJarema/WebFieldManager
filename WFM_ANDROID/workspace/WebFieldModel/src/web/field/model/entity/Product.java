package web.field.model.entity;

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
	private double Price;

	@DatabaseField
	private boolean FlagActive;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ProductBrand ProductBrands;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ProductCategory ProductCategory;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ProductFamily ProductFamily;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ProductManufacturer ProductManufacturer;

	public Product() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

	public ProductBrand getProductBrand() {
		return ProductBrands;
	}

	public void setProductBrand(ProductBrand productBrands) {
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

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public boolean isFlagActive() {
		return FlagActive;
	}

	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
	}	
	
	public Product MakeDeepCopy() {
		Product newInstance = new Product();
		
		newInstance.ProductId = this.ProductId;
		newInstance.TenantId = this.TenantId;
		newInstance.Code = this.Code;
		newInstance.ProductDescription = this.ProductDescription;
		newInstance.Price = Price;
		newInstance.FlagActive = this.FlagActive;
		newInstance.ProductBrands = this.ProductBrands;
		newInstance.ProductCategory = this.ProductCategory;
		newInstance.ProductFamily = this.ProductFamily;
		newInstance.ProductManufacturer = this.ProductManufacturer;
		
		return newInstance;
	}
}
