package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class PromoThresholdDetail {
	@DatabaseField(id = true, generatedId = false)
	private int PromoThresholdDetailId;
	@DatabaseField
	private int TenantId;

	@DatabaseField
	private int ThresholdType;
	@DatabaseField
	private int ThresholdMinValue;
	@DatabaseField
	private int ThresholdMaxValue;
	@DatabaseField
	private int ThresholdFixedFreeQty;
	@DatabaseField
	private double ThresholdFreeQty;
	@DatabaseField
	private double ThresholdDiscount;
	@DatabaseField
	private int CreateUserId;
	@DatabaseField
	private long CreateDate;
	@DatabaseField
	private int ModifiedUserId;
	@DatabaseField
	private long ModifiedDate;

	@DatabaseField(foreign = true)
	private PromoThreshold PromoThreshold;

	@DatabaseField(foreign = true)
	private Product Product;

	public PromoThresholdDetail() {

	}

	public int getPromoThresholdDetailId() {
		return PromoThresholdDetailId;
	}

	public void setPromoThresholdDetailId(int promoThresholdDetailId) {
		PromoThresholdDetailId = promoThresholdDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	public int getThresholdType() {
		return ThresholdType;
	}

	public void setThresholdType(int thresholdType) {
		ThresholdType = thresholdType;
	}

	public int getThresholdMinValue() {
		return ThresholdMinValue;
	}

	public void setThresholdMinValue(int thresholdMinValue) {
		ThresholdMinValue = thresholdMinValue;
	}

	public int getThresholdMaxValue() {
		return ThresholdMaxValue;
	}

	public void setThresholdMaxValue(int thresholdMaxValue) {
		ThresholdMaxValue = thresholdMaxValue;
	}

	public int getThresholdFixedFreeQty() {
		return ThresholdFixedFreeQty;
	}

	public void setThresholdFixedFreeQty(int thresholdFixedFreeQty) {
		ThresholdFixedFreeQty = thresholdFixedFreeQty;
	}

	public double getThresholdFreeQty() {
		return ThresholdFreeQty;
	}

	public void setThresholdFreeQty(double thresholdFreeQty) {
		ThresholdFreeQty = thresholdFreeQty;
	}

	public double getThresholdDiscount() {
		return ThresholdDiscount;
	}

	public void setThresholdDiscount(double thresholdDiscount) {
		ThresholdDiscount = thresholdDiscount;
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

	public int getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public PromoThreshold getPromoThreshold() {
		return PromoThreshold;
	}

	public void setPromoThreshold(PromoThreshold promoThreshold) {
		PromoThreshold = promoThreshold;
	}

}
