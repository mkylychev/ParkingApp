package test.home.com.parkingapp.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.maps.android.PolyUtil;

import io.realm.Realm;
import io.realm.RealmResults;
import test.home.com.parkingapp.Constants;
import test.home.com.parkingapp.model.ParkingInfo;
import test.home.com.parkingapp.model.ParkingPlace;

public class WaitingService extends Service {
    private BroadcastReceiver receiver;
    Location location;
    ParkingPlace place;
    private int parkingTimer = 3000 * 60 * 1;
    private int parkingTime = 0;
    private boolean isTimerRunning = false;
    private boolean isParked = false;
    Handler timerForAutoParkingHandler;
    Runnable timerForAutoParkingRunnable;
    Handler timerParkingHandler;
    Runnable timerParkingRunnable;
    int parkingInfoId;

    private Realm mRealm;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRealm = Realm.getDefaultInstance();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                location = (Location) intent.getExtras().get(Constants.USER_LOCATION);

                if (location != null) {
                    Intent waitingService = new Intent(Constants.WAITING_SERVICE);
                    waitingService.putExtra(Constants.USER_LOCATION, location);
                    sendBroadcast(waitingService);
                    if (checkForParking()) {
                        if (!isTimerRunning && !isParked)
                            startTimer();
                    } else if (isParked) {
                            stopParking();
                            place = null;
                        }
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(Constants.LOCATION_SERVICE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    private boolean checkForParking() {
        for (ParkingPlace parkingPlace : ParkingPlace.parkingPlaces()) {
            if (PolyUtil.containsLocation(location.getLatitude(), location.getLongitude(), parkingPlace.getPolygonOptions().getPoints(), true)) {
                place = parkingPlace;
                return true;
            }
        }
        return false;
    }

    public void startTimer() {
        timerForAutoParkingHandler = new Handler();
        timerForAutoParkingRunnable = () -> startAutoParking();
        timerForAutoParkingHandler.postDelayed(timerForAutoParkingRunnable, parkingTimer);
        isTimerRunning = true;
    }

    public void startAutoParking() {
        Toast.makeText(this, "Parking is started", Toast.LENGTH_LONG).show();
        isTimerRunning = false;
        isParked = true;
        parkingTime = 0;
        timerForAutoParkingHandler.removeCallbacks(timerForAutoParkingRunnable);
        mRealm.executeTransaction(realm -> {
            Number currentIdNum = realm.where(ParkingInfo.class).max("id");
            int nextId;
            if (currentIdNum == null) {
                nextId = 1;
            } else {
                nextId = currentIdNum.intValue() + 1;
            }
            ParkingInfo parkingInfo = new ParkingInfo(0, place.getTitle(), place.getDescription(), place.getImageUrl(),
                    place.getLatLng().latitude, place.getLatLng().longitude);
            parkingInfo.setId(nextId);
            mRealm.insertOrUpdate(parkingInfo);
            parkingInfoId = nextId;
        });


        timerParkingHandler = new Handler();
        timerParkingRunnable = new Runnable() {
            @Override
            public void run() {
                timerParkingHandler.postDelayed(this, 1000);
                parkingTime++;
            }
        };
        timerParkingHandler.postDelayed(timerParkingRunnable, 1000);
    }

    public void stopParking() {
        Toast.makeText(this, "Parking is stopped", Toast.LENGTH_LONG).show();
        if (timerParkingHandler != null && timerParkingRunnable != null)
            timerParkingHandler.removeCallbacks(timerParkingRunnable);
        mRealm.executeTransaction(realm -> {
            ParkingInfo parkingInfo = mRealm.where(ParkingInfo.class).equalTo("id", parkingInfoId).findFirst();
            if (parkingInfo != null)
                parkingInfo.setParkingTime(parkingTime);
        });
        isParked = false;
        parkingTime = 0;
    }
}
