package web.field.model.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JsonOrder {
	private int OrderId;
	private int TenantId;
	private int CustomerId;
	private int UserId;
	private long OrderDate;
	private long DeliveryDate;
	private int BillToId;
	private int ShipToId;
	private int CreateUserId;
	private long CreateDate;
	private int ModifiedUserId;
	private long ModifiedDate;
	private boolean FlagValid;
	private boolean FlagError;
	private String Comment;
	private String OrderTempId;
	private Integer OrderTemplateId;
	private List<JsonOrderDetail> OrdersDetail = new ArrayList<JsonOrderDetail>();
	
	private BigDecimal DiscountHeaderTemplate;
	private BigDecimal DiscountHeaderThreshold;
	private BigDecimal DiscountHeaderPayterms;

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public long getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(long orderDate) {
		OrderDate = orderDate;
	}

	public long getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(long deliveryDate) {
		DeliveryDate = deliveryDate;
	}

	public int getBillToId() {
		return BillToId;
	}

	public void setBillToId(int billToId) {
		BillToId = billToId;
	}

	public int getShipToId() {
		return ShipToId;
	}

	public void setShipToId(int shipToId) {
		ShipToId = shipToId;
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

	public boolean isFlagValid() {
		return FlagValid;
	}

	public void setFlagValid(boolean flagValid) {
		FlagValid = flagValid;
	}

	public boolean isFlagError() {
		return FlagError;
	}

	public void setFlagError(boolean flagError) {
		FlagError = flagError;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public List<JsonOrderDetail> getOrdersDetail() {
		return OrdersDetail;
	}

	public void setOrdersDetail(List<JsonOrderDetail> ordersDetail) {
		OrdersDetail = ordersDetail;
	}

	public String getOrderTempId() {
		return OrderTempId;
	}

	public void setOrderTempId(String orderTempId) {
		OrderTempId = orderTempId;
	}

	public Integer getOrderTemplateId() {
		return OrderTemplateId;
	}

	public void setOrderTemplateId(Integer orderTemplateId) {
		OrderTemplateId = orderTemplateId;
	}

	public BigDecimal getDiscountHeaderTemplate() {
		return DiscountHeaderTemplate;
	}

	public void setDiscountHeaderTemplate(BigDecimal discountHeaderTemplate) {
		DiscountHeaderTemplate = discountHeaderTemplate;
	}

	public BigDecimal getDiscountHeaderThreshold() {
		return DiscountHeaderThreshold;
	}

	public void setDiscountHeaderThreshold(BigDecimal discountHeaderThreshold) {
		DiscountHeaderThreshold = discountHeaderThreshold;
	}

	public BigDecimal getDiscountHeaderPayterms() {
		return DiscountHeaderPayterms;
	}

	public void setDiscountHeaderPayterms(BigDecimal discountHeaderPayterms) {
		DiscountHeaderPayterms = discountHeaderPayterms;
	}
	
	

}
