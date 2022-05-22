package com.example.myhomework.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework.databinding.FragmentMineBinding;
import com.example.myhomework.global.GlobalMemory;


public class MineFragment extends Fragment {

    FragmentMineBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        binding.userName.setText(GlobalMemory.NickName);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(getLayoutInflater());
        binding.topBar.setTopBarClickListener(()->{
            //TODO addOnclick
        });
        binding.userName.setText(GlobalMemory.NickName);
        return binding.getRoot();
    }
}