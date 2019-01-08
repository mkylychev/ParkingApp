package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;



import butterknife.BindView;
import butterknife.ButterKnife;
import test.home.com.parkingapp.Constants;
import test.home.com.parkingapp.R;
import test.home.com.parkingapp.adapter.ParkingPlaceAdapter;
import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceListActivity extends AppCompatActivity implements ParkingPlaceListActivityMVP.View, ParkingPlaceAdapter.AdapterItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ParkingPlaceListPresenter presenter;

    private ParkingPlaceAdapter adapter;
    private List<ParkingPlace> resultList;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_recycler_view);
        ButterKnife.bind(this);
        resultList = new ArrayList<>();
        adapter = new ParkingPlaceAdapter(resultList, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void updateData(ParkingPlace placeModel) {
        resultList.add(placeModel);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clearData() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter =  new ParkingPlaceListPresenter(() -> ParkingPlace.parkingPlaces());
        presenter.setView(this);
        presenter.loadData();
        initReceiver();
    }

    @Override
    public void onClick(ParkingPlace place) {
        setResult(RESULT_OK, new Intent().putExtra(Constants.PARKING_PLACE, place.getLatLng()));
        finish();
    }

    private void initReceiver(){
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Location location = (Location) intent.getExtras().get(Constants.USER_LOCATION);
                if(location!=null){
                    presenter.sortData(new LatLng(location.getLatitude(), location.getLongitude()));
                }
            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(Constants.WAITING_SERVICE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
