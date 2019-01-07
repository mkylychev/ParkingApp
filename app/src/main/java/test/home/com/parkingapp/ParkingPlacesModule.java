package test.home.com.parkingapp;

import dagger.Module;
import dagger.Provides;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivityMVP;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivityPresenter;
import test.home.com.parkingapp.activity.MapsActivity.ParkingPlaceModel;

@Module
public class ParkingPlacesModule {

    @Provides
    public MapsActivityMVP.Presenter provideMapsActivityPresenter(MapsActivityMVP.Model model){
        return new MapsActivityPresenter(model);
    }

    @Provides
    public MapsActivityMVP.Model provideParkingPlaceModel(){
        return new ParkingPlaceModel();
    }

}
