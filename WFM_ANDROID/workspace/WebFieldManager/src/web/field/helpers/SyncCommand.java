package web.field.helpers;

import web.field.OrmDbHelper;

public class SyncCommand {
	private String serviceAddress;
	private String jsonRequest;
	private OrmDbHelper dbHelper;

	public SyncCommand(String serviceAddress, String jsonRequest,
			OrmDbHelper dbHelper) {
		super();
		this.serviceAddress = serviceAddress;
		this.jsonRequest = jsonRequest;
		this.dbHelper = dbHelper;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(String jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	public OrmDbHelper getDbHelper() {
		return dbHelper;
	}

	public void setDbHelper(OrmDbHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

}
