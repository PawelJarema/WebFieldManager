package web.field.model.entity;


import com.j256.ormlite.field.DatabaseField;

public class OrderTemplateDetail{

	@DatabaseField(id = true, generatedId = false)
	private int OrdersTemplateDetailId;

	@DatabaseField
	private int TenantId;

	@DatabaseField(foreign = true)
	private OrderTemplate OrdersTemplate;

	@DatabaseField
	private int ProductId;

	@DatabaseField
	private int QtyMin;

	@DatabaseField
	private int QtyMax;

	@DatabaseField
	private int QtyProposal;

	@DatabaseField
	private int QtyMultiples;

	@DatabaseField
	private boolean FlagNoPromo;

	@DatabaseField
	private boolean FlagNoEdit;

	@DatabaseField(foreign = true)
	private Product product;

	public OrderTemplateDetail() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}

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

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderTemplate getOrdersTemplate() {
		return OrdersTemplate;
	}

	public void setOrdersTemplate(OrderTemplate ordersTemplate) {
		OrdersTemplate = ordersTemplate;
	}

}
