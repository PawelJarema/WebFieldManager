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
import web.field.model.json.JsonProduct;

public class ProductsSyncChainHandler implements ISyncChainHandler {

	private ISyncChainHandler nextHandler;

	public ProductsSyncChainHandler(ISyncChainHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public void setNext(ISyncChainHandler handler) {
		this.nextHandler = handler;
	}

	@Override
	public void handle(final SyncCommand command) {
		// load products
		HttpPost productsRequest = new HttpPost(command.getServiceAddress()
				+ "GetProducts/");

		productsRequest.setHeader("Accept", "application/json");
		productsRequest.setHeader("Content-type", "application/json");
		try {
			productsRequest
					.setEntity(new StringEntity(command.getJsonRequest()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final IDBAdapter db = new DBAdapter(command.getDbHelper());
		LongRunningHttpRequest getProducts = new LongRunningHttpRequest(
				command.getJsonRequest(), productsRequest) {
			@Override
			protected void onPostExecute(String results) {
				if (results != null && !"false".equals(results)) {

					Gson gson = new GsonBuilder().create();

					List<JsonProduct> answer = gson.fromJson(results,
							new TypeToken<List<JsonProduct>>() {
							}.getType());

					db.loadProducts(answer);

					// when finished, call order templates load
					nextHandler.handle(command);
				}
			}
		};
		getProducts.execute((Void) null);
	}

}
