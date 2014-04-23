package web.field.sync;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;


import com.google.gson.Gson;

import web.field.helpers.LongRunningHttpRequest;
import web.field.model.entity.Order;

public class SendOrderStrategy implements ISendOrderStrategy {

	private String serviceAddress;
	private ISendOrderCallback callback;

	public SendOrderStrategy(ISendOrderCallback callback) {
		this.callback = callback;
	}

	@Override
	public void sendOrder(Order order) {

		String json = new Gson().toJson(order);

		// load customers
		HttpPost customersRequest = new HttpPost(serviceAddress + "SaveOrder/");

		customersRequest.setHeader("Accept", "application/json");
		customersRequest.setHeader("Content-type", "application/json");
		try {
			customersRequest.setEntity(new StringEntity(json));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		LongRunningHttpRequest getCustomers = new LongRunningHttpRequest(json,
				customersRequest) {
			@Override
			protected void onPostExecute(String results) {
				if (results != null && "true".equals(results)) {
					callback.orderSend(true);
				} else {
					callback.orderSend(true);
				}
			}
		};

		getCustomers.execute((Void) null);
	}

}
