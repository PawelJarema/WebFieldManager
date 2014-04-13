package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonPromoGroup {
	private int PromoGroupsId;
	private int TenantId;
	private String Description;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;
	private boolean FlagActive;

	private List<JsonPromoGroupDetail> PromoGroupsDetail = new ArrayList<JsonPromoGroupDetail>();

	public int getPromoGroupsId() {
		return PromoGroupsId;
	}

	public void setPromoGroupsId(int promoGroupsId) {
		PromoGroupsId = promoGroupsId;
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

	public List<JsonPromoGroupDetail> getPromoGroupsDetail() {
		return PromoGroupsDetail;
	}

	public void setPromoGroupsDetail(
			List<JsonPromoGroupDetail> promoGroupsDetail) {
		PromoGroupsDetail = promoGroupsDetail;
	}

}
