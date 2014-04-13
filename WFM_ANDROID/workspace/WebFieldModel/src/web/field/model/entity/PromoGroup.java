package web.field.model.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class PromoGroup {
	@DatabaseField(id = true, generatedId = false)
	private int PromoGroupId;
	@DatabaseField
	private int TenantId;
	@DatabaseField
	private String Description;
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
	private ForeignCollection<PromoGroupDetail> PromoGroupsDetail;
	
	public int getPromoGroupId() {
		return PromoGroupId;
	}
	public void setPromoGroupId(int promoGroupsId) {
		PromoGroupId = promoGroupsId;
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
	public ForeignCollection<PromoGroupDetail> getPromoGroupsDetail() {
		return PromoGroupsDetail;
	}
	public void setPromoGroupsDetail(
			ForeignCollection<PromoGroupDetail> promoGroupsDetail) {
		PromoGroupsDetail = promoGroupsDetail;
	}
	
	
}
