package com.example.myhomework.activity;

import static com.example.myhomework.global.GlobalMemory.NickName;
import static com.example.myhomework.utils.UiUtil.ShowToast;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.example.myhomework.BottleApplication;
import com.example.myhomework.databinding.ActivityAddBottleBinding;
import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.service.MapService;
import com.example.myhomework.utils.MapUtil;
import com.example.myhomework.utils.UiUtil;

public class AddBottleActivity extends AppCompatActivity {
    ActivityAddBottleBinding binding;
    BaiduMap baiduMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddBottleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UiUtil.hideActionBar(this);
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(AddBottleActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            //TODO addOnclick
        });
        baiduMap = binding.map.getMap();
        MapUtil.initLocationOption(baiduMap,this, binding.map);
        binding.finish.setOnClickListener(v -> {
            String title = binding.title.getText().toString();
            String address;
            String describe = binding.describe.getText().toString();
            String author = NickName;

            if(TextUtils.isEmpty(title)||TextUtils.isEmpty(describe)){
                ShowToast(this,"内容不能为空噢");
                return;
            }

            if(binding.switchAnon.isChecked())
                author = "匿名";
            MapService.addBottle(title, describe, author);
        });
    }
}
