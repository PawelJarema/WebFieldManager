package web.field.model;

import com.google.gson.Gson;

/***
 * Base entity
 * 
 * @author Adam
 * 
 */
public class BaseEntity {
	/***
	 * Identifier of the entity
	 */
	protected long id;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
