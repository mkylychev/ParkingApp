package test.home.com.parkingapp.MapsActivity;


import java.util.List;

import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceModel implements MapsActivityMVP.Model {


    public ParkingPlaceModel() {

    }

    @Override
    public List<ParkingPlace> getParkingPlaces() {
        return ParkingPlace.parkingPlaces();
    }
}
