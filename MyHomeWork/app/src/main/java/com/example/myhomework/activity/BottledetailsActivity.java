package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.ActivityBottleDetailsBinding;

import com.example.myhomework.fragment.MapFragment;

public class BottledetailsActivity extends AppCompatActivity {
    ActivityBottleDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityBottleDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(BottledetailsActivity.this, MapFragment.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
