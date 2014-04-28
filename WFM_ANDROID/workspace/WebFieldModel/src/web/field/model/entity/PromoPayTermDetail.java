package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class PromoPayTermDetail {
	@DatabaseField(id = true, generatedId = false)
	private int PromoPayTermsDetailId;
	@DatabaseField
	private int TenantId;
	@DatabaseField
	private String Description;
	@DatabaseField
	private double Discount;
	@DatabaseField
	private String ExternalId_1;
	@DatabaseField
	private int CreateUserId;
	@DatabaseField
	private long CreateDate;
	@DatabaseField
	private int ModifiedUserId;
	@DatabaseField
	private long ModifiedDate;
	@DatabaseField(foreign = true)
	private PromoPayTerm PromoPayTerm;

	public PromoPayTermDetail() {
	}

	public int getPromoPayTermsDetailId() {
		return PromoPayTermsDetailId;
	}

	public void setPromoPayTermsDetailId(int promoPayTermsDetailId) {
		PromoPayTermsDetailId = promoPayTermsDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
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

	public PromoPayTerm getPromoPayTerm() {
		return PromoPayTerm;
	}

	public void setPromoPayTerm(PromoPayTerm promoPayTerm) {
		PromoPayTerm = promoPayTerm;
	}

}
