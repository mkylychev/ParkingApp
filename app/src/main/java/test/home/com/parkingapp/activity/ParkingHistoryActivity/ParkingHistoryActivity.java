package test.home.com.parkingapp.activity.ParkingHistoryActivity;

import android.content.DialogInterface;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import test.home.com.parkingapp.R;
import test.home.com.parkingapp.activity.ParkingPlaceListActivity.ParkingPlaceListPresenter;
import test.home.com.parkingapp.adapter.ParkingHistoryAdapter;
import test.home.com.parkingapp.model.ParkingInfo;
import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingHistoryActivity extends AppCompatActivity implements ParkingHistoryActivityMVP.View, ParkingHistoryAdapter.AdapterItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ParkingHistoryAdapter adapter;
    private List<ParkingInfo> resultList;
    ParkingHistoryPresenter presenter;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_recycler_view);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        resultList = new ArrayList<>();
        adapter = new ParkingHistoryAdapter(resultList, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter =  new ParkingHistoryPresenter(() -> mRealm.where(ParkingInfo.class).findAll());
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(ParkingInfo infoModel) {
        resultList.add(infoModel);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteParkingInfo(ParkingInfo parkingInfo) {
        adapter.removeItem(parkingInfo);
    }

    @Override
    public void showEmptyMessage() {
        Toast.makeText(this,"Parking history is empty", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(ParkingInfo place) {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete from history")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", (dialogInterface, i) -> presenter.delete(place, mRealm)).create().show();
    }
}
