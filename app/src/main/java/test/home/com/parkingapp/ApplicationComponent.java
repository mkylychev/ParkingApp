package test.home.com.parkingapp;


import javax.inject.Singleton;

import dagger.Component;
import test.home.com.parkingapp.MapsActivity.MapsActivity;

@Singleton
@Component(modules = {ParkingPlacesModule.class})
public interface ApplicationComponent {

    void inject(MapsActivity mapsActivity);
}
