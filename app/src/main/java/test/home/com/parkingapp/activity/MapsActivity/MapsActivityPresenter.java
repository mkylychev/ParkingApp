package test.home.com.parkingapp.activity.MapsActivity;

import test.home.com.parkingapp.model.ParkingPlace;

public class MapsActivityPresenter implements MapsActivityMVP.Presenter {

    private MapsActivityMVP.Model model;
    private MapsActivityMVP.View view;


    public MapsActivityPresenter(MapsActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {

        for(ParkingPlace parkingPlace: model.getParkingPlaces()){
            view.updateData(parkingPlace);
        }
    }

    @Override
    public void setView(MapsActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void isUserInParkingZone() {

    }


}
