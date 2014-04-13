package web.field.model.simple;

public class OrdersTemplateThresholdSimple {
	private int OrdersTemplateThresholdId;
	private String Name;
	private boolean FlagActive;
	private boolean FlagCanceled;
	public int getOrdersTemplateThresholdId() {
		return OrdersTemplateThresholdId;
	}
	public void setOrdersTemplateThresholdId(int ordersTemplateThresholdId) {
		OrdersTemplateThresholdId = ordersTemplateThresholdId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	
}
