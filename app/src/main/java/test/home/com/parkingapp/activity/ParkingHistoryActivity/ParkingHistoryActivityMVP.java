package test.home.com.parkingapp.activity.ParkingHistoryActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.realm.Realm;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListActivityMVP;
import test.home.com.parkingapp.model.ParkingInfo;
import test.home.com.parkingapp.model.ParkingPlace;

public interface ParkingHistoryActivityMVP {

    public interface View {

        void updateData(ParkingInfo infoModel);
        void deleteParkingInfo(ParkingInfo parkingInfo);
        void showEmptyMessage();
    }

    public interface Presenter {

        void loadData();


        void setView(ParkingHistoryActivityMVP.View view);

        void delete(ParkingInfo parkingInfo, Realm realm);

    }

    public interface Model {
        List<ParkingInfo> getParkingInfo();
    }
}
