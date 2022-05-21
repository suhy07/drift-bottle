package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.R;
import com.example.myhomework.databinding.FragmentMineBinding;
import com.example.myhomework.databinding.MyMessageBinding;
import com.example.myhomework.fragment.MapFragment;

public class MessageActivity extends AppCompatActivity {

    MyMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = MyMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(MessageActivity.this, MapFragment.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }


}
