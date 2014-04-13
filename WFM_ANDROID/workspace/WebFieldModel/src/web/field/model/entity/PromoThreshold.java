package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class PromoThreshold {
	@DatabaseField(id = true, generatedId = false)
	private int PromoThresholdId;
	@DatabaseField
	private int TenantId;
	@DatabaseField
	private String ThresholdDescription;
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
	private ForeignCollection<PromoThresholdDetail> PromoThresholdDetails;

	public PromoThreshold() {

	}

	public int getPromoThresholdId() {
		return PromoThresholdId;
	}

	public void setPromoThresholdId(int promoThresholdId) {
		PromoThresholdId = promoThresholdId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getThresholdDescription() {
		return ThresholdDescription;
	}

	public void setThresholdDescription(String thresholdDescription) {
		ThresholdDescription = thresholdDescription;
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

	public ForeignCollection<PromoThresholdDetail> getPromoThresholdDetail() {
		return PromoThresholdDetails;
	}

	public void setPromoThresholdDetail(
			ForeignCollection<PromoThresholdDetail> promoThresholdDetail) {
		PromoThresholdDetails = promoThresholdDetail;
	}

}
