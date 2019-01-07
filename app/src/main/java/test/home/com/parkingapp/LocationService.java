package test.home.com.parkingapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class LocationService extends Service {

    private LocationListener listener;
    private LocationManager locationManager;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        Log.e("test_log"," location service start");
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e("test_log","updated location = "+ location);
                startService(new Intent(getApplicationContext(), WaitingService.class));
                Intent locationIntent = new Intent(Constants.LOCATION_SERVICE);
                locationIntent.putExtra(Constants.USER_LOCATION, location);
                sendBroadcast(locationIntent);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent locationSettingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                locationSettingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(locationSettingsIntent);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("test_log"," location service stop");
        if(locationManager!=null){
            locationManager.removeUpdates(listener);
        }

        stopService(new Intent(getApplicationContext(), WaitingService.class));
    }
}
