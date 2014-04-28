package web.field.model.json;

public class JsonPromoPayTermDetail {
	private int PromoPayTermDetailId;
	private int TenantId;
	private int PromoPayTermId;
	private String Description;
	private double Discount;
	private String ExternalId_1;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;

	public int getPromoPayTermDetailId() {
		return PromoPayTermDetailId;
	}

	public void setPromoPayTermDetailId(int promoPayTermDetailId) {
		PromoPayTermDetailId = promoPayTermDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public int getPromoPayTermId() {
		return PromoPayTermId;
	}

	public void setPromoPayTermId(int promoPayTermId) {
		PromoPayTermId = promoPayTermId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public String getExternalId_1() {
		return ExternalId_1;
	}

	public void setExternalId_1(String externalId_1) {
		ExternalId_1 = externalId_1;
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
