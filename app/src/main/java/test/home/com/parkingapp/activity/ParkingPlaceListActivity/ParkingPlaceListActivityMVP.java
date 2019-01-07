package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

import java.util.List;

import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceListActivityMVP {

    public interface View {

        void updateData(ParkingPlace placeModel);


    }

    public interface Presenter {

        void loadData();


        void setView(ParkingPlaceListActivityMVP.View view);


    }

    public interface Model {
        List<ParkingPlace> getParkingPlaces();
    }
}
