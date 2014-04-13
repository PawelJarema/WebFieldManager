package web.field.model.simple;

public class PromoThresholdSimple {
	private int PromoThresholdId;
	private String ThresholdDescription;
	private boolean FlagActive;

	public int getPromoThresholdId() {
		return PromoThresholdId;
	}

	public void setPromoThresholdId(int promoThresholdId) {
		PromoThresholdId = promoThresholdId;
	}

	public String getThresholdDescription() {
		return ThresholdDescription;
	}

	public void setThresholdDescription(String thresholdDescription) {
		ThresholdDescription = thresholdDescription;
	}

	public boolean isFlagActive() {
		return FlagActive;
	}

	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
	}
}
