package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.home.com.parkingapp.App;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_place_list);
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
        adapter.notifyItemInserted(resultList.size() - 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter =  new ParkingPlaceListPresenter(() -> ParkingPlace.parkingPlaces());
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void onClick(ParkingPlace place) {
        setResult(RESULT_OK, new Intent().putExtra(Constants.PARKING_PLACE, place.getLatLng()));
        finish();
    }
}
