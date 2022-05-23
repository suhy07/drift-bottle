package com.example.myhomework.activity;

import static com.example.myhomework.global.GlobalMemory.Address;
import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Longitude;
import static com.example.myhomework.global.GlobalMemory.NickName;
import static com.example.myhomework.global.GlobalMemory.PoiList;
import static com.example.myhomework.global.GlobalMemory.PoiNameList;
import static com.example.myhomework.utils.UiUtil.ShowToast;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.example.myhomework.R;
import com.example.myhomework.databinding.ActivityAddBottleBinding;
import com.example.myhomework.service.MapService;
import com.example.myhomework.utils.MapUtil;
import com.example.myhomework.utils.UiUtil;

public class AddBottleActivity extends AppCompatActivity {
    ActivityAddBottleBinding binding;
    BaiduMap baiduMap;
    private String[] starArray = {"水星","金星","地球","火星","木星","土星"};
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
        MapUtil.initLocationOption(this, binding.map);
        binding.finish.setOnClickListener(v -> {
            double x = Latitude;
            double y = Longitude;
            String title = binding.title.getText().toString();
            String address;
            String describe = binding.describe.getText().toString();
            String author = NickName;

            if(TextUtils.isEmpty(title)||TextUtils.isEmpty(describe)){
                ShowToast(this,"内容不能为空噢");
                return;
            }
            int position = binding.spinner.getSelectedItemPosition();
            address = PoiNameList.get(position);
            if(address == null)
                address = Address;
            if(binding.switchAnon.isChecked())
                author = "匿名";
            MapService.addBottle(x, y, title, address, describe, author, AddBottleActivity.this);
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
        binding.spinner.setPrompt("请选择投放位置");
        binding.spinner.setAdapter(spinnerAdapter);
        binding.spinner.setSelection(0);
    }
}
