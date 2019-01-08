package test.home.com.parkingapp.activity.MapsActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import test.home.com.parkingapp.App;
import test.home.com.parkingapp.Constants;
import test.home.com.parkingapp.R;
import test.home.com.parkingapp.activity.ParkingHistoryActivity.ParkingHistoryActivity;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListActivity;
import test.home.com.parkingapp.model.ParkingPlace;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsActivityMVP.View {

    private GoogleMap mMap;
    private Marker myLocationMarker;
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        presenter.loadData();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.877278, 74.603985), 12f));
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Location location = (Location) intent.getExtras().get(Constants.USER_LOCATION);
                if(location!=null && isAttached ){
                    if(myLocationMarker!=null)
                        myLocationMarker.remove();

                    LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    myLocationMarker = mMap.addMarker(new MarkerOptions().position(myLocation).title("My location"));
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
    public void onChooseClick(){
        startActivityForResult(new Intent(this, ParkingPlaceListActivity.class), Constants.CHOOSE_PARKING_PLACE_CODE);
    }

    @OnClick(R.id.show_parking_history)
    public void onHistoryClick(){
        startActivity(new Intent(this, ParkingHistoryActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == Constants.CHOOSE_PARKING_PLACE_CODE && resultCode == RESULT_OK){
            if (data != null) {
                LatLng parkingPlace =  data.getParcelableExtra(Constants.PARKING_PLACE);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(parkingPlace, 12f));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
