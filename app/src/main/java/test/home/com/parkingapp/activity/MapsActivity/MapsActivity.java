package test.home.com.parkingapp.activity.MapsActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import test.home.com.parkingapp.App;
import test.home.com.parkingapp.Constants;
import test.home.com.parkingapp.R;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListActivity;
import test.home.com.parkingapp.model.ParkingPlace;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsActivityMVP.View {

    private GoogleMap mMap;
    BroadcastReceiver receiver;
    boolean isAttached = false;
    @Inject
    MapsActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        ((App) getApplication()).getApplicationComponent().inject(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        presenter.loadData();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.877278, 74.603985), 15f));
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Location location = (Location) intent.getExtras().get(Constants.USER_LOCATION);
                if(location!=null && isAttached ){
                    Log.e("test_log","distance = " +SphericalUtil.computeDistanceBetween(new LatLng(location.getLatitude(), location.getLongitude()),new LatLng(42.875462, 74.540486)));

                    if(PolyUtil.containsLocation(location.getLatitude(), location.getLongitude(), ParkingPlace.parkingPlaces().get(6).getPolygonOptions().getPoints(),true)){
                        Toast.makeText(getApplicationContext(), "Location is in polygon", Toast.LENGTH_LONG).show();
                    }
                    LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(sydney).title("My location"));

                }
                else {
                    Toast.makeText(getApplicationContext(), "Location is null", Toast.LENGTH_LONG).show();
                }
            }
        };
        registerReceiver(receiver, new IntentFilter(Constants.WAITING_SERVICE));

    }

    @Override
    protected void onResume() {
        isAttached = true;
        presenter.setView(MapsActivity.this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        isAttached = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void updateData(ParkingPlace placeModel) {
        mMap.addPolygon(placeModel.getPolygonOptions());
        mMap.addMarker(new MarkerOptions()
                .position(placeModel.getLatLng())
                .title(placeModel.getTitle())
                .snippet(placeModel.getDescription()));
    }

    @OnClick(R.id.choose_parking)
    public void onClick(){
        startActivity(new Intent(this, ParkingPlaceListActivity.class));
    }
}
