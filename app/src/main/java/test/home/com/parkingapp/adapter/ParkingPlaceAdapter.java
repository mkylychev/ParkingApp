package test.home.com.parkingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.home.com.parkingapp.R;
import test.home.com.parkingapp.model.ParkingPlace;

public class ParkingPlaceAdapter extends RecyclerView.Adapter<ParkingPlaceAdapter.ViewHolder> {
    Context context;
    private List<ParkingPlace> list;
    AdapterItemClickListener listener;

    public ParkingPlaceAdapter(List<ParkingPlace> list, Context context, AdapterItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_place_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemTitle.setText(list.get(position).getTitle());
        holder.itemdescription.setText(list.get(position).getDescription());
        Glide.with(context)
                .load(list.get(position).getImageUrl())
                .into(holder.image);
        holder.itemView.setOnClickListener(view -> listener.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView itemTitle;
        @BindView(R.id.description)
        TextView itemdescription;
        @BindView(R.id.image)
        ImageView image;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

    public interface AdapterItemClickListener{
        void onClick(ParkingPlace place);
    }
}

