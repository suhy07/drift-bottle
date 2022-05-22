package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myhomework.databinding.ActivityMessageDetailsBinding;
import com.example.myhomework.fragment.MapFragment;

public class MessagedetailsActivity extends AppCompatActivity {
    ActivityMessageDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMessageDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topBar.setTopBarClickListener(() -> {
            Intent intent=new Intent(MessagedetailsActivity.this, MessageActivity.class);
            startActivity(intent);
            //TODO addOnclick
        });
    }
}
