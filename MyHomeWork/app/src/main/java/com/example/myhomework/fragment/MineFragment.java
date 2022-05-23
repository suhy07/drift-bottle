package com.example.myhomework.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework.activity.ChangePasswordActivity;
import com.example.myhomework.activity.DraftActivity;
import com.example.myhomework.activity.LoginActivity;
import com.example.myhomework.databinding.FragmentMineBinding;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.utils.UiUtil;


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
        binding.userName.setText(GlobalMemory.NickName);
        binding.draft.setOnClickListener(v -> {
            UiUtil.onClickAnimator(getActivity(),binding.draft);
            UiUtil.ShowToast(getActivity(),"功能暂未开放，敬请期待");
        });
        binding.bottle.setOnClickListener(v -> {
            UiUtil.onClickAnimator(getActivity(),binding.bottle);
            UiUtil.ShowToast(getActivity(),"功能暂未开放，敬请期待");
        });
        binding.board.setOnClickListener(v -> {
            UiUtil.onClickAnimator(getActivity(),binding.board);
            UiUtil.ShowToast(getActivity(),"功能暂未开放，敬请期待");
        });
        binding.changePassword.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), ChangePasswordActivity.class);
            startActivity(intent);
        });
        binding.exit.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        return binding.getRoot();
    }
}