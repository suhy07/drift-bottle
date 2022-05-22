package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.databinding.ActivityAddMessageBinding;
import com.example.myhomework.fragment.MapFragment;


public class AddMessageActivity extends AppCompatActivity {
    ActivityAddMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(AddMessageActivity.this, MapFragment.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
