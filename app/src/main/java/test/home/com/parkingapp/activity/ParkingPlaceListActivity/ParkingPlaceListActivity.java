package test.home.com.parkingapp.activity.ParkingPlaceListActivity;

import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.home.com.parkingapp.R;
import test.home.com.parkingapp.adapter.ParkingPlaceAdapter;
import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_place_list);
        ButterKnife.bind(this);
        recyclerView.setAdapter(new ParkingPlaceAdapter(ParkingPlace.parkingPlaces(), this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
