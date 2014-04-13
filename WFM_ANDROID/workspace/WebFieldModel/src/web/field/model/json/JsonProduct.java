package web.field.model.json;

import java.math.BigDecimal;

public class JsonProduct {

	private int ProductId;
	private int TenantId;
	private String Code;
	private int ProductCategoryId;
	private int ProductBrandId;
	private int ProductFamilyId;
	private int ProductManufacturerId;
	private String ProductDescription;
	private BigDecimal Price;
	private boolean FlagActive;
	private int CreateUserId;
	private long CreateDate;
	private long ModifiedDate;
	private int ModifiedUserId;

	private JsonProductBrand ProductBrands;
	private JsonProductCategory ProductCategory;
	private JsonProductFamily ProductFamilys;
	private JsonProductManufacturer ProductManufacturers;

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

	public int getProductCategoryId() {
		return ProductCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		ProductCategoryId = productCategoryId;
	}

	public int getProductBrandId() {
		return ProductBrandId;
	}

	public void setProductBrandId(int productBrandId) {
		ProductBrandId = productBrandId;
	}

	public int getProductFamilyId() {
		return ProductFamilyId;
	}

	public void setProductFamilyId(int productFamilyId) {
		ProductFamilyId = productFamilyId;
	}

	public int getProductManufacturerId() {
		return ProductManufacturerId;
	}

	public void setProductManufacturerId(int productManufacturerId) {
		ProductManufacturerId = productManufacturerId;
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

	public int getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(int createUserId) {
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

	public int getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}

	public JsonProductBrand getProductBrands() {
		return ProductBrands;
	}

	public void setProductBrands(JsonProductBrand productBrands) {
		ProductBrands = productBrands;
	}

	public JsonProductCategory getProductCategory() {
		return ProductCategory;
	}

	public void setProductCategory(JsonProductCategory productCategory) {
		ProductCategory = productCategory;
	}

	public JsonProductFamily getProductFamilys() {
		return ProductFamilys;
	}

	public void setProductFamilys(JsonProductFamily productFamilys) {
		ProductFamilys = productFamilys;
	}

	public JsonProductManufacturer getProductManufacturers() {
		return ProductManufacturers;
	}

	public void setProductManufacturers(
			JsonProductManufacturer productManufacturers) {
		ProductManufacturers = productManufacturers;
	}

}
