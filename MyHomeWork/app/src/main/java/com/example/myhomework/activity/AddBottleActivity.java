package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.AddBottleBinding;
import com.example.myhomework.databinding.AddMessageBinding;
import com.example.myhomework.fragment.MapFragment;

public class AddBottleActivity extends AppCompatActivity {
    AddBottleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = AddBottleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(AddBottleActivity.this, MapFragment.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
