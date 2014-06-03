package web.field.sync;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import web.field.db.DBAdapter;
import web.field.db.IDBAdapter;
import web.field.helpers.ISyncChainHandler;
import web.field.helpers.LongRunningHttpRequest;
import web.field.helpers.SyncCommand;
import web.field.model.json.JsonPromoGroup;

public class PromoGroupSyncChainHandler implements ISyncChainHandler {

	private ISyncChainHandler nextHandler;

	public PromoGroupSyncChainHandler(ISyncChainHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public void setNext(ISyncChainHandler handler) {
		this.nextHandler = handler;
	}

	@Override
	public void handle(final SyncCommand command) {
		HttpPost templatesRequest = new HttpPost(command.getServiceAddress()
				+ "GetPromoGroups/");

		templatesRequest.setHeader("Accept", "application/json");
		templatesRequest.setHeader("Content-type", "application/json");
		try {
			templatesRequest.setEntity(new StringEntity(command
					.getJsonRequest()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final IDBAdapter db = new DBAdapter(command.getDbHelper(), command.getTenantProvider());
		LongRunningHttpRequest getTemplates = new LongRunningHttpRequest(
				command.getJsonRequest(), templatesRequest) {
			@Override
			protected void onPostExecute(String results) {
				if (results != null && !"false".equals(results)) {

					Gson gson = new GsonBuilder().create();

					List<JsonPromoGroup> answer = gson.fromJson(results,
							new TypeToken<List<JsonPromoGroup>>() {
							}.getType());

					db.loadPromoGroups(answer);

					nextHandler.handle(command);
				}
			}
		};

		getTemplates.execute((Void) null);

	}

}
