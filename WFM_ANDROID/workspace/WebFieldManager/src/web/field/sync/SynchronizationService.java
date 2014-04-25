package web.field.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import web.field.OrmDbHelper;
import web.field.SharedPreferencesKeys;
import web.field.WebFieldApplication;
import web.field.helpers.ISyncChainHandler;
import web.field.helpers.Observer;
import web.field.helpers.Subject;
import web.field.helpers.SyncCommand;
import web.field.helpers.SynchronizationResult;
import web.field.model.json.JsonRequest;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

public class SynchronizationService implements Subject {

	private List<Observer> observers = new ArrayList<Observer>();
	private ISyncChainHandler chainChandler;

	public void doSync(final OrmDbHelper dbHelper) {

	}

	public void doInitialDataPull(final OrmDbHelper dbHelper) {
		// get full data
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		String serviceAddress = preferences.getString(
				SharedPreferencesKeys.connection_service, null);
		String token = preferences.getString(SharedPreferencesKeys.user_token,
				null);
		JsonRequest jsonRequest = new JsonRequest();

		// TODO: use localization service!!
		Random r = new Random();
		jsonRequest.setLatitude(r.nextDouble() * 1000000000);
		jsonRequest.setLongitude(r.nextDouble() * 1000000000);

		jsonRequest.setUserToken(token);

		String json = new Gson().toJson(jsonRequest);
		// get all user customers
		if (!serviceAddress.endsWith("/")) {
			serviceAddress += "/";
		}

		// create chain
		// 1. customers
		// 2. products
		// 3. promo group
		// 4. promo pay term
		// 5. promo threshold
		// 6. order template threshold
		// 7. order templates
		// 8. orders

		chainChandler = new CustomersSyncChainHandler(
				new UsersSyncChainHandler(
						new ProductsSyncChainHandler(
								new PromoGroupSyncChainHandler(
										new PromoPayTermSyncChainHanler(
												new PromoThresholdSyncChainHandler(
														new OrderTemplateThresholdSyncHandler(
																new OrderTemplatesSyncChainHandler(
																		new OrdersSyncChainHanlder(
																				new EmptySyncChainHanlder() {
																					@Override
																					public void end() {
																						result = new SynchronizationResult();
																						result.setSucceed(true);
																						notifyObservers();
																					}
																				})))))))));
		// create command
		SyncCommand command = new SyncCommand(serviceAddress, json, dbHelper);
		chainChandler.handle(command);
	}

	private SynchronizationResult result = null;

	public static void doSynchronize() {
		// get data to synchronize
	}

	@Override
	public void register(Observer obj) {
		if (!observers.contains(obj)) {
			observers.add(obj);
		}
	}

	@Override
	public void unregister(Observer obj) {
		observers.remove(obj);
	}

	@Override
	public void notifyObservers() {
		for (Observer obs : observers) {
			obs.update(result);
		}
	}

	@Override
	public Object getUpdate(Observer obj) {
		return result;
	}
}
