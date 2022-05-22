package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.ActivityDraftDetailsBinding;

import com.example.myhomework.fragment.MapFragment;

public class DraftDetailsActivity extends AppCompatActivity {
    ActivityDraftDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDraftDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(DraftDetailsActivity.this, DraftActivity.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
