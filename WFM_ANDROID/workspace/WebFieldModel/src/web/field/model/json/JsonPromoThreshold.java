package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonPromoThreshold {
	private int PromoThresholdId;
	private int TenantId;
	private String ThresholdDescription;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;
	private boolean FlagActive;
	private List<JsonPromoThresholdDetail> PromoThresholdDetail = new ArrayList<JsonPromoThresholdDetail>();

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

	public List<JsonPromoThresholdDetail> getPromoThresholdDetail() {
		return PromoThresholdDetail;
	}

	public void setPromoThresholdDetail(
			List<JsonPromoThresholdDetail> promoThresholdDetail) {
		PromoThresholdDetail = promoThresholdDetail;
	}

}
