package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

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
    public void setView(ParkingPlaceListActivityMVP.View view) {
        this.view = view;
    }
}
