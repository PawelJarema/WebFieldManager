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
import web.field.model.json.*;

public class UsersSyncChainHandler implements ISyncChainHandler {
	private ISyncChainHandler nextHandler;

	public UsersSyncChainHandler(ISyncChainHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public void setNext(ISyncChainHandler handler) {
		nextHandler = handler;
	}

	@Override
	public void handle(final SyncCommand command) {
		// load customers
		HttpPost usersRequest = new HttpPost(command.getServiceAddress()
				+ "GetUsers/");

		usersRequest.setHeader("Accept", "application/json");
		usersRequest.setHeader("Content-type", "application/json");
		try {
			usersRequest.setEntity(new StringEntity(command
					.getJsonRequest()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		final IDBAdapter db = new DBAdapter(command.getDbHelper());
		LongRunningHttpRequest getCustomers = new LongRunningHttpRequest(
				command.getJsonRequest(), usersRequest) {
			@Override
			protected void onPostExecute(String results) {
				if (results != null && !"false".equals(results)) {

					Gson gson = new GsonBuilder().create();

					List<JsonUser> answer = gson.fromJson(results,
							new TypeToken<List<JsonUser>>() {
							}.getType());

					db.loadUsers(answer);

					// when finish, call next
					nextHandler.handle(command);
				}
			}
		};

		getCustomers.execute((Void) null);
	}
}
