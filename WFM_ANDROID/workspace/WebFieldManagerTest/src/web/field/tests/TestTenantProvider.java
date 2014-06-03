package web.field.tests;

import web.field.helpers.ITenantProvider;

public class TestTenantProvider implements ITenantProvider{

	@Override
	public int getTenant() {
		return 8;
	}

}
