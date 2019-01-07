package test.home.com.parkingapp;


import javax.inject.Singleton;

import dagger.Component;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivity;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListActivity;

@Singleton
@Component(modules = {ParkingPlacesModule.class})
public interface ApplicationComponent {

    void inject(MapsActivity mapsActivity);
}
