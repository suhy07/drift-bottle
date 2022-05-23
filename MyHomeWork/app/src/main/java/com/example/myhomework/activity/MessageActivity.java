package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.myhomework.adapter.MessageAdapter;
import com.example.myhomework.bean.Message;
import com.example.myhomework.databinding.ActivityMessageBinding;
import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.service.MessageService;
import com.example.myhomework.utils.MapUtil;
import com.example.myhomework.utils.UiUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    ActivityMessageBinding binding;
    MessageAdapter messageAdapter;
    public static List<Message> messageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UiUtil.hideActionBar(this);
        initRecycleView();
        MapUtil.initLocationOption(this,binding.map);

        int id = getIntent().getIntExtra("board_id",-1);
        if(id == -1){
            Intent intent=new Intent(MessageActivity.this, MainActivity.class);
            UiUtil.ShowToast(this,"数据异常，请稍后再试");
            startActivity(intent);
            finish();
        }
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(MessageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        MessageService.showBoard(id,this,binding.title,binding.map.getMap(),messageAdapter);

        binding.finish.setOnClickListener(v -> {
            UiUtil.onClickAnimator(this,binding.finish);
            String message = binding.message.getText().toString();
            String author = GlobalMemory.NickName;
            if(binding.switchAnon.isChecked()){
                author = "匿名";
            }
            if(TextUtils.isEmpty(message)){
                UiUtil.ShowToast(this,"留言不能为空");
                return;
            }
            MessageService.addMessage(message,author,id,this);
        });
    }

    private void initRecycleView(){
        messageAdapter = new MessageAdapter(messageList,this);
        binding.recyclerView.setAdapter(messageAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
