package web.field.model.simple;

public class PromoGroupSimple {
	private int PromoGroupId;
	private String Description;
	private boolean FlagActive;

	public int getPromoGroupId() {
		return PromoGroupId;
	}

	public void setPromoGroupId(int promoGroupsId) {
		PromoGroupId = promoGroupsId;
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

}
