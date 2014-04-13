package web.field.model.entity;

import java.math.BigDecimal;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Order {

	@DatabaseField(id = true, generatedId = false)
	private String OrderTempId;

	@DatabaseField()
	private int OrderId;

	@DatabaseField
	private int Status;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private int UserId;

	@DatabaseField
	private long OrderDate;

	@DatabaseField
	private long DeliveryDate;

	@DatabaseField
	private boolean FlagValid;

	@DatabaseField
	private boolean FlagError;

	@DatabaseField
	private String Comment;

	@ForeignCollectionField(eager = true)
	private ForeignCollection<OrderDetail> OrdersDetail;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Customer Customer;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private CustomerAddress BillTo;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private CustomerAddress ShipTo;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private OrderTemplate OrderTemplate;
	
	@DatabaseField
	private BigDecimal DiscountHeaderTemplate;
	
	@DatabaseField
	private BigDecimal DiscountHeaderThreshold;
	
	@DatabaseField
	private BigDecimal DiscountHeaderPayterms;

	public CustomerAddress getBillTo() {
		return BillTo;
	}

	public void setBillTo(CustomerAddress billTo) {
		BillTo = billTo;
	}

	public CustomerAddress getShipTo() {
		return ShipTo;
	}

	public void setShipTo(CustomerAddress shipTo) {
		ShipTo = shipTo;
	}

	public Order() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
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

	public String getOrderTempId() {
		return OrderTempId;
	}

	public void setOrderTempId(String orderTempId) {
		OrderTempId = orderTempId;
	}

	public ForeignCollection<OrderDetail> getOrderDetails() {
		return OrdersDetail;
	}

	public void setOrderDetails(ForeignCollection<OrderDetail> ordersDetail) {
		OrdersDetail = ordersDetail;
	}

	public Customer getCustomer() {
		return Customer;
	}

	public void setCustomer(Customer customer) {
		Customer = customer;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public OrderTemplate getOrderTemplate() {
		return OrderTemplate;
	}

	public void setOrderTemplate(OrderTemplate orderTemplate) {
		OrderTemplate = orderTemplate;
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
