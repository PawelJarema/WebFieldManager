package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

import web.field.model.entity.*;

public class JsonOrder {
	private int OrderId;
	private int TenantId;
	private int CustomerId;
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
	private int Status;
	private List<JsonOrderDetail> OrdersDetail = new ArrayList<JsonOrderDetail>();
	
	private double DiscountHeaderTemplate;
	private double DiscountHeaderThreshold;
	private double DiscountHeaderPayterms;
	
	public JsonOrder(){}
	
	public JsonOrder(Order order){
		
		OrderId = order.getOrderId();
		TenantId = order.getTenantId();
		CustomerId = order.getCustomer().getCustomerId();
		OrderDate = order.getOrderDate();
		DeliveryDate = order.getDeliveryDate();
		BillToId = order.getBillTo().getCustomerAddressId();
		ShipToId = order.getShipTo().getCustomerAddressId();
		CreateUserId = order.getCreateUserId();
		CreateDate = order.getCreateDate();
		ModifiedUserId = order.getModifiedUserId();
		ModifiedDate = order.getModifiedDate();
		FlagValid = order.isFlagValid();
		FlagError = order.isFlagError();
		Comment = order.getComment();
		OrderTempId = order.getOrderTempId();
		OrderTemplateId = order.getOrderTemplate().getOrdersTemplateId();
		Status = order.getStatus();
		DiscountHeaderTemplate = order.getDiscountHeaderTemplate();
		DiscountHeaderThreshold = order.getDiscountHeaderThreshold();
		DiscountHeaderPayterms = order.getDiscountHeaderPayterms();
		
		for(OrderDetail od : order.OrdersDetail){
			OrdersDetail.add(new JsonOrderDetail(od));
		}
		
		
	}
	

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
		return CreateUserId;
	}

	public void setUserId(int userId) {
		CreateUserId = userId;
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

	public double getDiscountHeaderTemplate() {
		return DiscountHeaderTemplate;
	}

	public void setDiscountHeaderTemplate(double discountHeaderTemplate) {
		DiscountHeaderTemplate = discountHeaderTemplate;
	}

	public double getDiscountHeaderThreshold() {
		return DiscountHeaderThreshold;
	}

	public void setDiscountHeaderThreshold(double discountHeaderThreshold) {
		DiscountHeaderThreshold = discountHeaderThreshold;
	}

	public double getDiscountHeaderPayterms() {
		return DiscountHeaderPayterms;
	}

	public void setDiscountHeaderPayterms(double discountHeaderPayterms) {
		DiscountHeaderPayterms = discountHeaderPayterms;
	}
	
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}
	
	

}
