package web.field.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonOrderTemplate {
	private int OrdersTemplateId;
	private int TenantId;
	private String Name;
	private String Description;
	private boolean FlagActive;
	private boolean FlagCanceled;
	private boolean FlagQtyVisible;
	private boolean FlagFreeQtyVisible;
	private boolean FlagDiscountVisible;
	private int SplitsMin;
	private int SplitsMax;
	private int SplitsInBetweenDays;
	private long DeliveriesDateStart;
	private long DeliveriesDateEnd;
	private long VisibleDateStart;
	private long VisibleDateEnd;
	private double TotalMinValue;
	private double Discount;
	private long CreateDate;
	private int CreateUserId;
	private long ModifiedDate;
	private int ModifiedUserId;
	private int OrdersTemplateThresholdId;
	private Integer PromoThresholdId;

	private JsonOrderTemplateThreshold OrdersTemplateThresholds;
	private JsonPromoThreshold PromoThreshold;

	private List<JsonOrderTemplateDetail> OrdersTemplateDetails = new ArrayList<JsonOrderTemplateDetail>();

	public JsonOrderTemplateThreshold getOrdersTemplateThresholds() {
		return OrdersTemplateThresholds;
	}

	public void setOrdersTemplateThresholds(
			JsonOrderTemplateThreshold ordersTemplateThresholds) {
		OrdersTemplateThresholds = ordersTemplateThresholds;
	}

	public JsonPromoThreshold getPromoThreshold() {
		return PromoThreshold;
	}

	public void setPromoThreshold(JsonPromoThreshold promoThreshold) {
		PromoThreshold = promoThreshold;
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

	public int getOrdersTemplateThresholdId() {
		return OrdersTemplateThresholdId;
	}

	public void setOrdersTemplateThresholdId(int ordersTemplateThresholdId) {
		OrdersTemplateThresholdId = ordersTemplateThresholdId;
	}

	public Integer getPromoThresholdId() {
		return PromoThresholdId;
	}

	public void setPromoThresholdId(Integer promoThresholdId) {
		PromoThresholdId = promoThresholdId;
	}

	public List<JsonOrderTemplateDetail> getOrdersTemplateDetails() {
		return OrdersTemplateDetails;
	}

	public void setOrdersTemplateDetails(
			List<JsonOrderTemplateDetail> ordersTemplateDetails) {
		OrdersTemplateDetails = ordersTemplateDetails;
	}

}
