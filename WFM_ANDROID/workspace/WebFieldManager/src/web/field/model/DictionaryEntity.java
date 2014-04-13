package web.field.model;

/***
 * Basic dictionary class
 * 
 * @author Adam
 * 
 */
public class DictionaryEntity extends BaseEntity {

	/***
	 * Dictionary code
	 */
	protected String code;

	/***
	 * Dictionary value
	 */
	protected String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
