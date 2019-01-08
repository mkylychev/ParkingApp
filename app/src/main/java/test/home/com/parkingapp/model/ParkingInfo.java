package test.home.com.parkingapp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ParkingInfo extends RealmObject implements Serializable {

    @PrimaryKey
    int id;

    int parkingTime;
    String title;
    String description;
    String imageUrl;
    double latitude;
    double longitute;

    public ParkingInfo() {

    }

    public ParkingInfo(int parkingTime, String title, String description, String imageUrl, double latitude, double longitute) {
        this.parkingTime = parkingTime;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitute = longitute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ParkingInfo)
            return getId() == ((ParkingInfo) obj).getId();
        return super.equals(obj);
    }
}
