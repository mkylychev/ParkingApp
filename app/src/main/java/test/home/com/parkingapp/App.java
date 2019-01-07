package test.home.com.parkingapp;

import android.app.Application;

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
    }
}
