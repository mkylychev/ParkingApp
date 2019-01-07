package test.home.com.parkingapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

public class WaitingService extends Service {
    private BroadcastReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("test_log","Waiting service start");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Location location = (Location) intent.getExtras().get(Constants.USER_LOCATION);

                if(location!=null){
                    Log.e("test_log","waiting  service location recived");
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
