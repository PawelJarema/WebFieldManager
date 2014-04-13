package web.field.model;

public enum ContactType {
	Phone(1), MobilePhone(2), Fax(3), Email(4);

	private int value;

	private ContactType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
