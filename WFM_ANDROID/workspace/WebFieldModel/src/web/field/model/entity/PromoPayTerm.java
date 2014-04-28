package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class PromoPayTerm {
	@DatabaseField(id = true, generatedId = false)
	private int PromoPayTermsId;
	@DatabaseField
	private int TenantId;
	@DatabaseField
	private String PromoPayTermDescription;
	@DatabaseField
	private int CreateUserId;
	@DatabaseField
	private long CreateDate;
	@DatabaseField
	private int ModifiedUserId;
	@DatabaseField
	private long ModifiedDate;
	@DatabaseField
	private boolean FlagActive;
	@ForeignCollectionField(eager = false)
	private ForeignCollection<PromoPayTermDetail> PromoPayTermsDetail;

	public PromoPayTerm() {
	}

	public int getPromoPayTermsId() {
		return PromoPayTermsId;
	}

	public void setPromoPayTermsId(int promoPayTermsId) {
		PromoPayTermsId = promoPayTermsId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getPromoPayTermDescription() {
		return PromoPayTermDescription;
	}

	public void setPromoPayTermDescription(String promoPayTermDescription) {
		PromoPayTermDescription = promoPayTermDescription;
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

	public boolean isFlagActive() {
		return FlagActive;
	}

	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
	}

	public ForeignCollection<PromoPayTermDetail> getPromoPayTermsDetail() {
		return PromoPayTermsDetail;
	}

	public void setPromoPayTermsDetail(
			ForeignCollection<PromoPayTermDetail> promoPayTermsDetail) {
		PromoPayTermsDetail = promoPayTermsDetail;
	}

}
