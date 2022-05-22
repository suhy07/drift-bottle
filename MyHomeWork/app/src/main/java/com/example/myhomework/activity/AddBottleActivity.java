package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.ActivityAddBottleBinding;
import com.example.myhomework.fragment.MapFragment;

public class AddBottleActivity extends AppCompatActivity {
    ActivityAddBottleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddBottleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(AddBottleActivity.this, MapFragment.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
