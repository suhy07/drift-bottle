package com.example.myhomework.activity;


import static com.example.myhomework.global.GlobalMemory.Address;
import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Longitude;
import static com.example.myhomework.global.GlobalMemory.NickName;
import static com.example.myhomework.global.GlobalMemory.PoiNameList;
import static com.example.myhomework.utils.UiUtil.ShowToast;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.example.myhomework.R;
import com.example.myhomework.databinding.ActivityAddMessageBinding;
import com.example.myhomework.service.MapService;
import com.example.myhomework.utils.MapUtil;
import com.example.myhomework.utils.UiUtil;


public class AddMessageActivity extends AppCompatActivity {
    ActivityAddMessageBinding binding;
    BaiduMap baiduMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UiUtil.hideActionBar(this);
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(AddMessageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        baiduMap = binding.map.getMap();
        MapUtil.initLocationOption(this, binding.map);
        binding.finish.setOnClickListener(v -> {
            UiUtil.onClickAnimator(this,binding.finish);
            double x = Latitude;
            double y = Longitude;
            String title = binding.title.getText().toString();
            String address;
            String describe = binding.describe.getText().toString();
            String author = NickName;

            if(TextUtils.isEmpty(title)||TextUtils.isEmpty(describe)){
                ShowToast(this,"?????????????????????");
                return;
            }
            address = PoiNameList.get(binding.spinner.getSelectedItemPosition());
            if(address == null)
                address = Address;
            if(binding.switchAnon.isChecked())
                author = "??????";
            MapService.addBoard(x , y,title, address, describe, author, AddMessageActivity.this);
        });
        initSpinner();
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

    private void initSpinner(){
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.item_select, PoiNameList);
        binding.spinner.setPrompt("?????????????????????");
        binding.spinner.setAdapter(spinnerAdapter);
        binding.spinner.setSelection(0);
    }
}
