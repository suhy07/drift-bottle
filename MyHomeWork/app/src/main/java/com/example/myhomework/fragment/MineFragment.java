package com.example.myhomework.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework.R;
import com.example.myhomework.databinding.FragmentMapBinding;
import com.example.myhomework.databinding.FragmentMineBinding;


public class MineFragment extends Fragment {

    FragmentMineBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(getLayoutInflater());
        binding.topBar.setTopBarClickListener(()->{
            //TODO addOnclick
        });
        return binding.getRoot();
    }
}