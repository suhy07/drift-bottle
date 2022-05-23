package com.example.myhomework.adapter;

import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Limit;
import static com.example.myhomework.global.GlobalMemory.Longitude;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomework.R;
import com.example.myhomework.activity.MainActivity;
import com.example.myhomework.bean.MapRecord;
import com.example.myhomework.databinding.ItemMaprecordBinding;
import com.example.myhomework.utils.UiUtil;

import java.util.List;

public class MapRecordAdapter extends RecyclerView.Adapter<MapRecordAdapter.ViewHolder>{
    private List<MapRecord> mapRecordList;
    ItemMaprecordBinding itemMaprecordBinding;
    Activity activity;
    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View view) {
            super(view);
            itemMaprecordBinding  = ItemMaprecordBinding.bind(itemView);
            itemMaprecordBinding.getRoot().setAlpha(0.75f);
        }
    }

    public MapRecordAdapter(List<MapRecord> mapRecordList, Activity activity) {
        this.mapRecordList = mapRecordList;
        this.activity = activity;
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
        String str;
        if(d > 1){
            str = String.format("%.2f",d);
            itemMaprecordBinding.distance.setText(str+"km");
        }else{
            str = String.format("%.2f",d*1000);
            itemMaprecordBinding.distance.setText(str+"m");
        }
        itemMaprecordBinding.getRoot().setOnClickListener(v -> {
            if( d * 1000 < Limit){
                UiUtil.ShowToast(activity,"");
            }else {
                UiUtil.ShowToast(activity, "当前距离过远，请靠近地点再拾取");
            }

        });
    }
    @Override
    public int getItemCount(){
        return mapRecordList.size();
    }

}

