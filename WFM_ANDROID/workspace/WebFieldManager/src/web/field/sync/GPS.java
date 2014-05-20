package web.field.sync;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;


public class GPS implements LocationListener {
	
	private Context context;
	protected LocationManager locationManager;
	
	// settings for location updates, coarse location is enough
	private static final long UPDATE_IN_DISTANCE = 1000; // 1km
	private static final long UPDATE_IN_TIME = 1000 * 60 * 60; //1h
	
	private boolean theresNetwork = false;
	private boolean gpsOn = false;
	
	private Location location;
	double latitude;
	double longitude;
	
	public GPS(Context context) {
		this.context = context;
		getLocation();
	}
	
	public double getLatitude() {
		if (location == null)
			getLocation();
		return latitude;	
	}
	
	public double getLongitude() {
		if (location == null)
			getLocation();
		return longitude;	
	}
	
	public Location getLocation() {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		theresNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		gpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(theresNetwork) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 
					UPDATE_IN_DISTANCE, 
					UPDATE_IN_TIME, 
					this);
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			parseLocation();
		}
		if(gpsOn && location == null) {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
					UPDATE_IN_DISTANCE, 
					UPDATE_IN_TIME, 
					this);
			location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
			parseLocation();
		}
		if(location == null && !theresNetwork) {
			noSourceForLocalizationAlert(context.getResources().getString(web.field.R.string.no_network_error));
		} else if(location == null && !gpsOn) {
			noSourceForLocalizationAlert(context.getResources().getString(web.field.R.string.no_gps_error));
		}
		return location;
	}
	
	private void parseLocation() {
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
	}
	
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
	public void noSourceForLocalizationAlert(String message) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(context.getResources().getString(web.field.R.string.title_localization_service));
		dialog.setMessage(message);
		dialog.setPositiveButton(context.getResources().getString(web.field.R.string.action_settings), new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent gpsSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(gpsSettings);
			}
		});
		dialog.setNegativeButton(context.getResources().getString(web.field.R.string.cancel), new OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();	
			}
		});
		dialog.show();
	}
}
