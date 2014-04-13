package web.field.model.json;

public class JsonOrderTemplateDetail {
	private int OrdersTemplateDetailId;
	private int TenantId;
	private int OrdersTemplateId;
	private int ProductId;
	private int QtyMin;
	private int QtyMax;
	private int QtyProposal;
	private int QtyMultiples;
	private boolean FlagNoPromo;
	private boolean FlagNoEdit;
	private long CreateDate;
	private int CreateUserId;
	private long ModifiedDate;
	private int ModifiedUserId;

	public int getOrdersTemplateDetailId() {
		return OrdersTemplateDetailId;
	}

	public void setOrdersTemplateDetailId(int ordersTemplateDetailId) {
		OrdersTemplateDetailId = ordersTemplateDetailId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public int getOrdersTemplateId() {
		return OrdersTemplateId;
	}

	public void setOrdersTemplateId(int ordersTemplateId) {
		OrdersTemplateId = ordersTemplateId;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public int getQtyMin() {
		return QtyMin;
	}

	public void setQtyMin(int qtyMin) {
		QtyMin = qtyMin;
	}

	public int getQtyMax() {
		return QtyMax;
	}

	public void setQtyMax(int qtyMax) {
		QtyMax = qtyMax;
	}

	public int getQtyProposal() {
		return QtyProposal;
	}

	public void setQtyProposal(int qtyProposal) {
		QtyProposal = qtyProposal;
	}

	public int getQtyMultiples() {
		return QtyMultiples;
	}

	public void setQtyMultiples(int qtyMultiples) {
		QtyMultiples = qtyMultiples;
	}

	public boolean isFlagNoPromo() {
		return FlagNoPromo;
	}

	public void setFlagNoPromo(boolean flagNoPromo) {
		FlagNoPromo = flagNoPromo;
	}

	public boolean isFlagNoEdit() {
		return FlagNoEdit;
	}

	public void setFlagNoEdit(boolean flagNoEdit) {
		FlagNoEdit = flagNoEdit;
	}

	public long getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}

	public int getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(int createUserId) {
		CreateUserId = createUserId;
	}

	public long getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public int getModifiedUserId() {
		return ModifiedUserId;
	}

	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}

}
