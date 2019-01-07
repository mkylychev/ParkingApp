package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Comparator;

import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceListPresenter implements ParkingPlaceListActivityMVP.Presenter {
    ParkingPlaceListActivityMVP.Model model;
    ParkingPlaceListActivityMVP.View view;


    public ParkingPlaceListPresenter(ParkingPlaceListActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        for(ParkingPlace parkingPlace: model.getParkingPlaces()){
            view.updateData(parkingPlace);
        }
    }

    @Override
    public void sortData(LatLng latLng) {
        ArrayList<ParkingPlace> parkingPlaces = new ArrayList<>();
        for(ParkingPlace parkingPlace: model.getParkingPlaces()){
            parkingPlace.setDistance(SphericalUtil.computeDistanceBetween(latLng, parkingPlace.getLatLng()));
            parkingPlaces.add(parkingPlace);
        }
        parkingPlaces.sort((parkingPlace, t1) -> (int) (parkingPlace.getDistance() - t1.getDistance()));
        view.clearData();
        for(ParkingPlace parkingPlace: parkingPlaces){
            view.updateData(parkingPlace);
        }

    }


    @Override
    public void setView(ParkingPlaceListActivityMVP.View view) {
        this.view = view;
    }
}
