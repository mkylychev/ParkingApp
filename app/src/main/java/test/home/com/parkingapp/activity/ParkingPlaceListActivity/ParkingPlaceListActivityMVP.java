package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceListActivityMVP {

    public interface View {

        void updateData(ParkingPlace placeModel);
        void clearData();

    }

    public interface Presenter {

        void loadData();

        void sortData(LatLng latLng);

        void setView(ParkingPlaceListActivityMVP.View view);


    }

    public interface Model {
        List<ParkingPlace> getParkingPlaces();
    }
}
