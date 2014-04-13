package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class Outbound {

	@DatabaseField(id = true, generatedId = true)
	private long Id;
	@DatabaseField
	private String JsonData;

	@DatabaseField
	private int EntityType;

	@DatabaseField
	private String EntityId;

	@DatabaseField
	private long CreateDate;

	@DatabaseField
	private long ModifyDate;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getJsonData() {
		return JsonData;
	}

	public void setJsonData(String jsonData) {
		JsonData = jsonData;
	}

	public int getEntityType() {
		return EntityType;
	}

	public void setEntityType(int entityType) {
		EntityType = entityType;
	}

	public String getEntityId() {
		return EntityId;
	}

	public void setEntityId(String entityId) {
		EntityId = entityId;
	}

	public long getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}

	public long getModifyDate() {
		return ModifyDate;
	}

	public void setModifyDate(long modifyDate) {
		ModifyDate = modifyDate;
	}
}
