package web.field.model.entity;

import java.math.BigDecimal;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class OrderTemplate {

	@DatabaseField(id = true, generatedId = false)
	private int OrdersTemplateId;

	@DatabaseField
	private int TenantId;

	@DatabaseField
	private String Name;

	@DatabaseField
	private String Description;

	@DatabaseField
	private boolean FlagActive;

	@DatabaseField
	private boolean FlagCanceled;

	@DatabaseField
	private boolean FlagQtyVisible;

	@DatabaseField
	private boolean FlagFreeQtyVisible;

	@DatabaseField
	private boolean FlagDiscountVisible;

	@DatabaseField
	private int SplitsMin;

	@DatabaseField
	private int SplitsMax;

	@DatabaseField
	private int SplitsInBetweenDays;

	@DatabaseField
	private long DeliveriesDateStart;

	@DatabaseField
	private long DeliveriesDateEnd;

	@DatabaseField
	private long VisibleDateStart;

	@DatabaseField
	private long VisibleDateEnd;

	@DatabaseField
	private double TotalMinValue;

	@DatabaseField
	private double Discount;

	@DatabaseField(foreign = true)
	private OrderTemplateThreshold OrderTemplateThreshold;

	@DatabaseField(foreign = true)
	private PromoThreshold PromoThreshold;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<OrderTemplateDetail> OrdersTemplateDetails;
	
	

	public OrderTemplateThreshold getOrderTemplateThreshold() {
		return OrderTemplateThreshold;
	}

	public void setOrderTemplateThreshold(
			OrderTemplateThreshold orderTemplateThreshold) {
		OrderTemplateThreshold = orderTemplateThreshold;
	}

	public PromoThreshold getPromoThreshold() {
		return PromoThreshold;
	}

	public void setPromoThreshold(PromoThreshold promoThreshold) {
		PromoThreshold = promoThreshold;
	}

	public ForeignCollection<OrderTemplateDetail> getOrdersTemplateDetails() {
		return OrdersTemplateDetails;
	}

	public void setOrdersTemplateDetails(
			ForeignCollection<OrderTemplateDetail> ordersTemplateDetails) {
		OrdersTemplateDetails = ordersTemplateDetails;
	}

	public int getOrdersTemplateId() {
		return OrdersTemplateId;
	}

	public void setOrdersTemplateId(int ordersTemplateId) {
		OrdersTemplateId = ordersTemplateId;
	}

	public int getTenantId() {
		return TenantId;
	}

	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public boolean isFlagActive() {
		return FlagActive;
	}

	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
	}

	public boolean isFlagCanceled() {
		return FlagCanceled;
	}

	public void setFlagCanceled(boolean flagCanceled) {
		FlagCanceled = flagCanceled;
	}

	public boolean isFlagQtyVisible() {
		return FlagQtyVisible;
	}

	public void setFlagQtyVisible(boolean flagQtyVisible) {
		FlagQtyVisible = flagQtyVisible;
	}

	public boolean isFlagFreeQtyVisible() {
		return FlagFreeQtyVisible;
	}

	public void setFlagFreeQtyVisible(boolean flagFreeQtyVisible) {
		FlagFreeQtyVisible = flagFreeQtyVisible;
	}

	public boolean isFlagDiscountVisible() {
		return FlagDiscountVisible;
	}

	public void setFlagDiscountVisible(boolean flagDiscountVisible) {
		FlagDiscountVisible = flagDiscountVisible;
	}

	public int getSplitsMin() {
		return SplitsMin;
	}

	public void setSplitsMin(int splitsMin) {
		SplitsMin = splitsMin;
	}

	public int getSplitsMax() {
		return SplitsMax;
	}

	public void setSplitsMax(int splitsMax) {
		SplitsMax = splitsMax;
	}

	public int getSplitsInBetweenDays() {
		return SplitsInBetweenDays;
	}

	public void setSplitsInBetweenDays(int splitsInBetweenDays) {
		SplitsInBetweenDays = splitsInBetweenDays;
	}

	public long getDeliveriesDateStart() {
		return DeliveriesDateStart;
	}

	public void setDeliveriesDateStart(long deliveriesDateStart) {
		DeliveriesDateStart = deliveriesDateStart;
	}

	public long getDeliveriesDateEnd() {
		return DeliveriesDateEnd;
	}

	public void setDeliveriesDateEnd(long deliveriesDateEnd) {
		DeliveriesDateEnd = deliveriesDateEnd;
	}

	public long getVisibleDateStart() {
		return VisibleDateStart;
	}

	public void setVisibleDateStart(long visibleDateStart) {
		VisibleDateStart = visibleDateStart;
	}

	public long getVisibleDateEnd() {
		return VisibleDateEnd;
	}

	public void setVisibleDateEnd(long visibleDateEnd) {
		VisibleDateEnd = visibleDateEnd;
	}

	public double getTotalMinValue() {
		return TotalMinValue;
	}

	public void setTotalMinValue(double totalMinValue) {
		TotalMinValue = totalMinValue;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	// TODO: replace with relations
	// private int OrdersTemplateThresholdId;
	// private Integer PromoThresholdId;

}
