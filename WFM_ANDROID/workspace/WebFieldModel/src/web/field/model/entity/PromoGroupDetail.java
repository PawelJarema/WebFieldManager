package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class PromoGroupDetail {
	@DatabaseField(id = true, generatedId = false)
	private int PromoGroupsDetailId;
	
	@DatabaseField
	private int TenantId;

	@DatabaseField
	private int Qty;
	
	@DatabaseField
	private int FreeQty;
	
	@DatabaseField
	private int Discount;
	
	@DatabaseField
	private int CreateUserId;
	
	@DatabaseField
	private long CreateDate;
	
	@DatabaseField
	private int ModifiedUserId;
	
	@DatabaseField
	private long ModifiedDate;
	
	@DatabaseField(foreign = true)
	private PromoGroup PromoGroup;

	@DatabaseField(foreign = true)
	private Product Product;

	public PromoGroupDetail() {
	}

	public int getPromoGroupsDetailId() {
		return PromoGroupsDetailId;
	}

	public void setPromoGroupsDetailId(int promoGroupsDetailId) {
		PromoGroupsDetailId = promoGroupsDetailId;
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

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public int getFreeQty() {
		return FreeQty;
	}

	public void setFreeQty(int freeQty) {
		FreeQty = freeQty;
	}

	public int getDiscount() {
		return Discount;
	}

	public void setDiscount(int discount) {
		Discount = discount;
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

	public PromoGroup getPromoGroup() {
		return PromoGroup;
	}

	public void setPromoGroup(PromoGroup promoGroup) {
		PromoGroup = promoGroup;
	}

}
