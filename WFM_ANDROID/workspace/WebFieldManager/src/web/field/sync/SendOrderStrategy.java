package web.field.sync;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import web.field.SharedPreferencesKeys;
import web.field.WebFieldApplication;
import web.field.WebfieldFragmentActivityInner;
import web.field.helpers.LongRunningHttpRequest;
import web.field.model.entity.Order;
import web.field.model.json.JsonOrder;
import web.field.model.json.JsonSaveOrderRequest;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SendOrderStrategy implements ISendOrderStrategy {

	private String serviceAddress;
	private String userToken;
	private ISendOrderCallback callback;
	
	public SendOrderStrategy(ISendOrderCallback callback) {
		this.callback = callback;
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		serviceAddress = preferences.getString(
				SharedPreferencesKeys.connection_service, null);
		
		if(!serviceAddress.endsWith("/")){
			serviceAddress += "/";
		}
		
		userToken = preferences.getString(
				SharedPreferencesKeys.user_token, null);
	}

	@Override
	public void sendOrder(Order order) {
		// map to JsonOrder
		JsonOrder jsonOrder = new JsonOrder(order);
		
		JsonSaveOrderRequest request = new JsonSaveOrderRequest();
		request.setOrder(jsonOrder);
		request.setUserToken(userToken);

		// serialize json order
		String json = new Gson().toJson(request);

		// load customers
		HttpPost saveOrderRequest = new HttpPost(serviceAddress + "/SaveOrder/");

		saveOrderRequest.setHeader("Accept", "application/json");
		saveOrderRequest.setHeader("Content-type", "application/json");
		try {
			saveOrderRequest.setEntity(new StringEntity(json));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		LongRunningHttpRequest getCustomers = new LongRunningHttpRequest(json,
				saveOrderRequest) {
			@Override
			protected void onPostExecute(String results) {
				if (results != null && "true".equals(results)) {
					callback.orderSend(true);
				} else {
					callback.orderSend(false);
				}
			}
		};

		getCustomers.execute((Void) null);
	}

}
