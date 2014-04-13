package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonPromoPayTerm {
	private int PromoPayTermId;
	private int TenantId;
	private String PromoPayTermDescription;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;
	private boolean FlagActive;

	private List<JsonPromoPayTermDetail> PromoPayTermsDetail = new ArrayList<JsonPromoPayTermDetail>();

	public int getPromoPayTermId() {
		return PromoPayTermId;
	}

	public void setPromoPayTermId(int promoPayTermId) {
		PromoPayTermId = promoPayTermId;
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

	public List<JsonPromoPayTermDetail> getPromoPayTermDetails() {
		return PromoPayTermsDetail;
	}

	public void setPromoPayTermDetails(
			List<JsonPromoPayTermDetail> promoPayTermsDetail) {
		PromoPayTermsDetail = promoPayTermsDetail;
	}

}
