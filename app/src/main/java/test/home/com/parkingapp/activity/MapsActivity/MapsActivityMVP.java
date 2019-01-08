package test.home.com.parkingapp.activity.MapsActivity;

import java.util.List;

import test.home.com.parkingapp.model.ParkingPlace;

public class MapsActivityMVP  {

    public interface View {

        void updateData(ParkingPlace placeModel);


    }

    public interface Presenter {

        void loadData();


        void setView(MapsActivityMVP.View view);


    }

    public interface Model {
        List<ParkingPlace> getParkingPlaces();
    }
}
