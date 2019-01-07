package test.home.com.parkingapp.model;

import com.google.android.gms.maps.model.LatLng;

public class UserPlace {

    LatLng latLng;

    public UserPlace(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
