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
import test.home.com.parkingapp.model.ParkingInfo;
import test.home.com.parkingapp.util.DateUtils;

public class ParkingHistoryAdapter extends RecyclerView.Adapter<ParkingHistoryAdapter.ViewHolder> {
    Context context;
    private List<ParkingInfo> list;
    AdapterItemClickListener listener;

    public ParkingHistoryAdapter(List<ParkingInfo> list, Context context, ParkingHistoryAdapter.AdapterItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ParkingHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_place_item, parent, false);
        return new ParkingHistoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ParkingHistoryAdapter.ViewHolder holder, final int position) {

        holder.itemTitle.setText(list.get(position).getTitle());
        holder.itemDescription.setText(list.get(position).getDescription());
        if(list.get(position).getParkingTime() == 0)
            holder.distance.setVisibility(View.GONE);
        else
            holder.distance.setText(DateUtils.elapsedTime( list.get(position).getParkingTime()));

        Glide.with(context)
                .load(list.get(position).getImageUrl())
                .into(holder.image);
        holder.itemView.setOnClickListener(view -> listener.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public void clear(){
        list.clear();
    }

    public void removeItem(ParkingInfo item) {
        list.remove(item);
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView itemTitle;
        @BindView(R.id.description)
        TextView itemDescription;
        @BindView(R.id.distance)
        TextView distance;
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
        void onClick(ParkingInfo place);
    }
}

