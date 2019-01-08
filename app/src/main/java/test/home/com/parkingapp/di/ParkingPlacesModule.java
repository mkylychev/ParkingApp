package test.home.com.parkingapp.di;

import dagger.Module;
import dagger.Provides;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivityMVP;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivityPresenter;
import test.home.com.parkingapp.activity.MapsActivity.ParkingPlaceModel;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListActivityMVP;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListPresenter;

@Module
public class ParkingPlacesModule {

    @Provides
    public MapsActivityMVP.Presenter provideMapsActivityPresenter(MapsActivityMVP.Model model){
        return new MapsActivityPresenter(model);
    }

    @Provides
    public ParkingPlaceListActivityMVP.Presenter provideParkingPlaceListActivityPresenter(ParkingPlaceListActivityMVP.Model model){
        return new ParkingPlaceListPresenter(model);
    }

    @Provides
    public MapsActivityMVP.Model provideParkingPlaceModel(){
        return new ParkingPlaceModel();
    }

}
