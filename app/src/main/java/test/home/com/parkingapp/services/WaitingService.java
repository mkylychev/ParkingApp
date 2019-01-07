package test.home.com.parkingapp.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.IBinder;

import test.home.com.parkingapp.Constants;

public class WaitingService extends Service {
    private BroadcastReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Location location = (Location) intent.getExtras().get(Constants.USER_LOCATION);

                if(location!=null){
                    Intent waitingService = new Intent(Constants.WAITING_SERVICE);
                    waitingService.putExtra(Constants.USER_LOCATION, location);
                    sendBroadcast(waitingService);
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(Constants.LOCATION_SERVICE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(receiver!=null){
            unregisterReceiver(receiver);
        }
    }

}
