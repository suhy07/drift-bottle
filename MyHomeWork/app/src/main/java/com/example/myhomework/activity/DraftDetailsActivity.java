package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.BottleDetailsBinding;
import com.example.myhomework.databinding.DraftDetailsBinding;
import com.example.myhomework.fragment.MapFragment;

public class DraftDetailsActivity extends AppCompatActivity {
    DraftDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DraftDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(DraftDetailsActivity.this, DraftActivity.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
