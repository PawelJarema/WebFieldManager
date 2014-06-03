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
import web.field.helpers.TenantProvider;
import web.field.model.json.JsonRequest;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;

import com.google.gson.Gson;

public class SynchronizationService implements Subject {

	private Context context;
	private List<Observer> observers = new ArrayList<Observer>();
	private ISyncChainHandler chainChandler;

	
	public SynchronizationService(Context context) {
		this.context = context;
	}
	
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

		// check for Network/GPS localization
		GPS gps = new GPS(context);
		if (gps.getLocation() != null) {
			jsonRequest.setLatitude(gps.getLatitude());
			jsonRequest.setLongitude(gps.getLongitude());
		}

		jsonRequest.setUserToken(token);

		String json = new Gson().toJson(jsonRequest);
		// get all user customers
		if (!serviceAddress.endsWith("/")) {
			serviceAddress += "/";
		}

		// create chain

		chainChandler = new UsersSyncChainHandler(
				new CustomersSyncChainHandler(
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
		SyncCommand command = new SyncCommand(serviceAddress, json, dbHelper, new TenantProvider(dbHelper));
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
