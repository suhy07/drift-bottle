package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.example.myhomework.databinding.ActivityBottleDetailsBinding;

import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.service.BottleService;
import com.example.myhomework.service.MapService;
import com.example.myhomework.utils.MapUtil;
import com.example.myhomework.utils.UiUtil;

public class BottledetailsActivity extends AppCompatActivity {
    ActivityBottleDetailsBinding binding;
    BaiduMap baiduMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityBottleDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UiUtil.hideActionBar(this);
        int id = getIntent().getIntExtra("bottle_id",-1);
        if(id == -1){
            Intent intent=new Intent(BottledetailsActivity.this, MainActivity.class);
            UiUtil.ShowToast(this,"获取数据错误，请稍后再试");
            startActivity(intent);
            finish();
        }
        baiduMap = binding.map.getMap();
        MapUtil.initLocationOption(this,binding.map);
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(BottledetailsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        BottleService.showBottle(id,this,binding.title,baiduMap,binding.describe);
    }
    @Override
    public void onResume(){
        super.onResume();
        binding.map.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        binding.map.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        binding.map.onDestroy();
    }

}
