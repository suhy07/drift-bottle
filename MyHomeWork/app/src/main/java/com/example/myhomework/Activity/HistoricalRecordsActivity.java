package com.example.myhomework.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework.Bean.HistoryRecord;
import com.example.myhomework.R;
import com.example.myhomework.Service.HistoryRecordService;
import com.example.myhomework.Service.UserService;
import com.example.myhomework.Utils.HttpUtils;
import com.example.myhomework.databinding.ActivityHistoricalRecordsBinding;
import com.example.myhomework.databinding.HistoryrecordItemBinding;
import     com.example.myhomework.databinding.AppbarBinding;

import java.util.ArrayList;


public class HistoricalRecordsActivity extends AppCompatActivity {

    ActivityHistoricalRecordsBinding binding;
    public static HistoryRecord temhistory;
    static ArrayList<HistoryRecord> historyRecordArrayList=new ArrayList<>();
    HistoryrecordItemBinding itemBinding;
    AppbarBinding appbarBinding;
    static RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        binding=ActivityHistoricalRecordsBinding.inflate(getLayoutInflater());
        appbarBinding=AppbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.include.textviewToolbar.setText("提交历史");
        setRecy();
        HistoryRecordService.checkHistoryById(UserService.GetUid());
    }
    private void setRecy(){
        adapter = new RecyclerView.Adapter<HistoricalRecordsActivity.MyHolder>() {
            @NonNull
            @Override
            public HistoricalRecordsActivity.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(HistoricalRecordsActivity.this).inflate(R.layout.historyrecord_item,parent,false);
                return new HistoricalRecordsActivity.MyHolder(view);
            }
            @Override
            public void onBindViewHolder(@NonNull HistoricalRecordsActivity.MyHolder holder, int position) {
                holder.setIsRecyclable(false);
                itemBinding.title.setText(historyRecordArrayList.get(position).getTitle());
                itemBinding.msg.setText(historyRecordArrayList.get(position).getMsg());
                itemBinding.process.setText(historyRecordArrayList.get(position).getState());
                itemBinding.evalute.setOnClickListener(v->{
                    startActivity(new Intent(HistoricalRecordsActivity.this,EvaluteActivity.class));
                    temhistory=historyRecordArrayList.get(position);
                });
               // itemBinding.img.setImageBitmap(HttpUtils.getURLimage("http://47.98.173.217:8080/downloadFile/app_icon.png"));
            }
            @Override
            public int getItemCount() {
                return historyRecordArrayList.size();
            }
            @Override
            public int getItemViewType(int position) {
                return position;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
        };
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(HistoricalRecordsActivity.this){
            @Override
            public boolean isAutoMeasureEnabled() {
                return true;
            }
        });
        binding.recyclerView.setNestedScrollingEnabled(false);
    }
    class MyHolder extends RecyclerView.ViewHolder{
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = HistoryrecordItemBinding.bind(itemView);
        }
    }
    static Activity activity;
    public static void recall(ArrayList<HistoryRecord> arrayList){
        historyRecordArrayList.clear();
        historyRecordArrayList=arrayList;
        activity.runOnUiThread(()->adapter.notifyDataSetChanged());
    }
}