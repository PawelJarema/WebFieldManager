package web.field.model;

public class Contact extends BaseEntity {
	/***
	 * Contact type
	 */
	protected ContactType type;

	/***
	 * Contact value
	 */
	protected String value;

	/***
	 * Contact status
	 */
	protected CommonStatus status;

	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CommonStatus getStatus() {
		return status;
	}

	public void setStatus(CommonStatus status) {
		this.status = status;
	}
}
