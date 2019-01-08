package test.home.com.parkingapp.model;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParkingPlace {

    int id;
    String title;
    String description;
    String imageUrl;
    PolygonOptions polygonOptions;
    LatLng latLng;
    double distance;



    public ParkingPlace(String title, String description, String imageUrl, PolygonOptions polygonOptions, LatLng latLng) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.polygonOptions = polygonOptions;
        this.latLng = latLng;
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

    public PolygonOptions getPolygonOptions() {
        return polygonOptions;
    }

    public void setPolygonOptions(PolygonOptions polygonOptions) {
        this.polygonOptions = polygonOptions;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static List<ParkingPlace> parkingPlaces(){
        ArrayList<ParkingPlace> parkingPlaces = new ArrayList<>();

        parkingPlaces.add(new ParkingPlace("Bishkek Park", "Shopping center with 500 parking places",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/03/52/16/caption.jpg",
                new PolygonOptions().add(new LatLng(42.875142, 74.589823))
                        .add(new LatLng(42.875087, 74.590984))
                        .add(new LatLng(42.874521, 74.591007))
                        .add(new LatLng(42.874694, 74.589764))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),
                        new LatLng(42.874805, 74.590289)));

        parkingPlaces.add(new ParkingPlace("Dordoi Plaza", "Shopping center with 2000 parking places",
                "https://static-2.akipress.org/st_runews/8/1472528.1.1538384273.jpg",
                new PolygonOptions().add(new LatLng(42.875300, 74.617805))
                        .add(new LatLng(42.875229, 74.619308))
                        .add(new LatLng(42.874192, 74.617808))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),
                        new LatLng(42.874806, 74.618420)));

        parkingPlaces.add(new ParkingPlace("Asia moll", "Shopping moll with 3000 parking places",
                "https://24.kg/files/media/30/30228.jpg",
                new PolygonOptions().add(new LatLng(42.855134, 74.584216))
                        .add(new LatLng(42.855079, 74.586234))
                        .add(new LatLng(42.855920, 74.586218))
                        .add(new LatLng(42.856456, 74.584238))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),
                        new LatLng(42.855668, 74.585087)));

        parkingPlaces.add(new ParkingPlace("Karavan", "Shopping center with 200 parking places",
                "https://gdb.rferl.org/72DFAF22-FF16-4355-B6C7-5FF5A581EC1C_cx0_cy3_cw0_w1023_r1_s.jpg",
                new PolygonOptions().add(new LatLng(42.874876, 74.593456))
                        .add(new LatLng(42.874873, 74.593970))
                        .add(new LatLng(42.873915, 74.593849))
                        .add(new LatLng(42.873895, 74.593387))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),
                        new LatLng(42.874684, 74.593645)));

        parkingPlaces.add(new ParkingPlace("Beta Stores", "Parking place with 300 places",
                "https://gdb.rferl.org/BF0F88FE-D804-4510-831F-27E155FC8353_w1023_r1_s.jpg",
                new PolygonOptions().add(new LatLng(42.875728, 74.592087))
                        .add(new LatLng(42.875686, 74.593145))
                        .add(new LatLng(42.875230, 74.593102))
                        .add(new LatLng(42.875292, 74.592117))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),
                        new LatLng(42.875608, 74.592804)));

        parkingPlaces.add(new ParkingPlace("Beta Stores 2", "Shopping center with 500 places",
                "https://i6.photo.2gis.com/images/branch/112/15762598707170776_6047.jpg",
                new PolygonOptions().add(new LatLng(42.831233, 74.621595))
                        .add(new LatLng(42.831630, 74.621616))
                        .add(new LatLng(42.831628, 74.620634))
                        .add(new LatLng(42.831256, 74.620582))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),
                        new LatLng(42.831432, 74.621445)));


        parkingPlaces.add(new ParkingPlace("Spalmalo", "Shopping center with 500 places",
                "https://i6.photo.2gis.com/images/branch/112/15762598707170776_6047.jpg",
                new PolygonOptions().add(new LatLng(42.866007, 74.600347))
                        .add(new LatLng(42.865913, 74.601212))
                        .add(new LatLng(42.865481, 74.601112))
                        .add(new LatLng(42.865292, 74.600328))
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.LTGRAY),new LatLng(42.875608, 74.592804)));


        return parkingPlaces;
    }
}
