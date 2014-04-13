package web.field.model.json;

import java.math.BigDecimal;

public class JsonPromoThresholdDetail {
	private int PromoThresholdDetailId;
	private int TenantId;
	private int PromoThresholdId;
	private int ProductId;
	private int ThresholdType;
	private int ThresholdMinValue;
	private int ThresholdMaxValue;
	private int ThresholdFixedFreeQty;
	private BigDecimal ThresholdFreeQty;
	private BigDecimal ThresholdDiscount;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;

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

	public int getPromoThresholdId() {
		return PromoThresholdId;
	}

	public void setPromoThresholdId(int promoThresholdId) {
		PromoThresholdId = promoThresholdId;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
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

	public BigDecimal getThresholdFreeQty() {
		return ThresholdFreeQty;
	}

	public void setThresholdFreeQty(BigDecimal thresholdFreeQty) {
		ThresholdFreeQty = thresholdFreeQty;
	}

	public BigDecimal getThresholdDiscount() {
		return ThresholdDiscount;
	}

	public void setThresholdDiscount(BigDecimal thresholdDiscount) {
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

}
