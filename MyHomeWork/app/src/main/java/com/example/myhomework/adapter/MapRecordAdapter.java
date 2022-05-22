package com.example.myhomework.adapter;

import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Longitude;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomework.R;
import com.example.myhomework.bean.MapRecord;
import com.example.myhomework.databinding.ItemMaprecordBinding;

import java.util.List;

public class MapRecordAdapter extends RecyclerView.Adapter<MapRecordAdapter.ViewHolder>{
    private List<MapRecord> mapRecordList;
    ItemMaprecordBinding itemMaprecordBinding;
    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View view) {
            super(view);
            itemMaprecordBinding  = ItemMaprecordBinding.bind(itemView);
            itemMaprecordBinding.getRoot().setAlpha(0.75f);
        }
    }

    public MapRecordAdapter(List<MapRecord> mapRecordList) {
        this.mapRecordList = mapRecordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_maprecord,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MapRecord mapRecord = mapRecordList.get(position);
        itemMaprecordBinding.address.setText(mapRecord.getAddress());

        if(mapRecord.getRecordType() == MapRecord.RecordType.Board){
            itemMaprecordBinding.imageView.setImageResource(R.drawable.board);
        }else{
            itemMaprecordBinding.imageView.setImageResource(R.drawable.bottle);
        }
        double d,R = 6371;
        d=R*Math.acos(Math.cos(Longitude) * Math.cos(mapRecord.getY())*Math.cos(Latitude - mapRecord.getX())
                +Math.sin(Longitude) * Math.sin(mapRecord.getY()));
        if(d > 1){
            itemMaprecordBinding.distance.setText(d + "km");
        }else{
            itemMaprecordBinding.distance.setText(d + "m");
        }
    }
    @Override
    public int getItemCount(){
        return mapRecordList.size();
    }

}

