package test.home.com.parkingapp.activity.ParkingHistoryActivity;


import io.realm.Realm;
import test.home.com.parkingapp.model.ParkingInfo;

public class ParkingHistoryPresenter  implements ParkingHistoryActivityMVP.Presenter{

    ParkingHistoryActivityMVP.View view;
    ParkingHistoryActivityMVP.Model model;


    public ParkingHistoryPresenter(ParkingHistoryActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        for(ParkingInfo parkingInfo: model.getParkingInfo()){
            view.updateData(parkingInfo);
        }
        if(model.getParkingInfo().size() == 0){
            view.showEmptyMessage();
        }
    }



    @Override
    public void setView(ParkingHistoryActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void delete(ParkingInfo parkingInfo, Realm mRealm) {
        int id = parkingInfo.getId();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ParkingInfo parkingInfo = mRealm.where(ParkingInfo.class).equalTo("id", id ).findFirst();
                if (parkingInfo != null) {
                    view.deleteParkingInfo(parkingInfo);
                    parkingInfo.deleteFromRealm();
                }
            }
        });
    }

}
