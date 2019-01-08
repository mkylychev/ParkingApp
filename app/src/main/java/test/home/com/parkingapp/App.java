package test.home.com.parkingapp;

import android.app.Application;

import io.realm.Realm;
import test.home.com.parkingapp.di.ApplicationComponent;
import test.home.com.parkingapp.di.DaggerApplicationComponent;
import test.home.com.parkingapp.di.ParkingPlacesModule;

public class App extends Application {
    private ApplicationComponent component;

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .parkingPlacesModule(new ParkingPlacesModule())
                .build();

        Realm.init(this);
    }
}
