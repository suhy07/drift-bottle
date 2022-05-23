package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myhomework.databinding.ActivityMessageDetailsBinding;
import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.service.BottleService;
import com.example.myhomework.service.MessageService;
import com.example.myhomework.utils.MapUtil;
import com.example.myhomework.utils.UiUtil;

public class MessagedetailsActivity extends AppCompatActivity {
    ActivityMessageDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMessageDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UiUtil.hideActionBar(this);
        int id = getIntent().getIntExtra("message_id",-1);
        if(id == -1){
            Intent intent=new Intent(this, MessageActivity.class);
            UiUtil.ShowToast(this,"获取数据错误，请稍后再试");
            startActivity(intent);
            finish();
        }
        MapUtil.initLocationOption(this,binding.map);
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(this, MessageActivity.class);
            startActivity(intent);
            finish();
        });
        MessageService.showMessage(id,this,binding.title,binding.map.getMap(),binding.describe);
    }


}
