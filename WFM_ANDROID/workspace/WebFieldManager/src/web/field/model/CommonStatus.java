package web.field.model;

public enum CommonStatus {
	Active(1), Inactive(0);

	private int value;

	private CommonStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
