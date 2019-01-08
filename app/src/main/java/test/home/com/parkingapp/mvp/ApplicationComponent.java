package test.home.com.parkingapp.mvp;


import javax.inject.Singleton;

import dagger.Component;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivity;

@Singleton
@Component(modules = {ParkingPlacesModule.class})
public interface ApplicationComponent {

    void inject(MapsActivity mapsActivity);
}
