package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.MyDraftBinding;
import com.example.myhomework.databinding.MyMessageBinding;
import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.fragment.MineFragment;

public class DraftActivity extends AppCompatActivity {
    MyDraftBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = MyDraftBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(DraftActivity.this, MineFragment.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
