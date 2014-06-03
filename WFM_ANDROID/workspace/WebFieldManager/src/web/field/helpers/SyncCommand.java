package web.field.helpers;

import web.field.OrmDbHelper;

public class SyncCommand {
	private String serviceAddress;
	private String jsonRequest;
	private OrmDbHelper dbHelper;
	private ITenantProvider tenantProvider;

	public SyncCommand(String serviceAddress, String jsonRequest,
			OrmDbHelper dbHelper, ITenantProvider tenantProvider) {
		super();
		this.serviceAddress = serviceAddress;
		this.jsonRequest = jsonRequest;
		this.dbHelper = dbHelper;
		this.tenantProvider = tenantProvider;
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

	public ITenantProvider getTenantProvider() {
		return tenantProvider;
	}

	public void setTenantProvider(ITenantProvider tenantProvider) {
		this.tenantProvider = tenantProvider;
	}

}
