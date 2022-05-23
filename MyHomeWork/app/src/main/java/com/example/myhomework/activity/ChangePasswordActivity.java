package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.myhomework.databinding.ActivityChangePasswordBinding;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.service.UserService;
import com.example.myhomework.utils.UiUtil;

import androidx.appcompat.app.AppCompatActivity;


public class ChangePasswordActivity extends AppCompatActivity {

    private ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nickname.setText(GlobalMemory.NickName);
        binding.topBar.setTopBarClickListener(()->{
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        binding.finish.setOnClickListener(v->{
            UiUtil.onClickAnimator(this,binding.finish);
            String nickname = binding.nickname.getText().toString();
            String password1 = binding.password.getText().toString();
            String password2 = binding.passwordQuery.getText().toString();

            if (TextUtils.isEmpty(nickname)||TextUtils.isEmpty(password1)||TextUtils.isEmpty(password2)){
                UiUtil.ShowToast(this,"您还有选项未填");
                return;
            }

            if (!password1.equals(password2)){
                UiUtil.ShowToast(this,"两次密码输入不一致");
                return;
            }

            UserService.ChangePassWord(nickname,password1,this);
        });
    }
}