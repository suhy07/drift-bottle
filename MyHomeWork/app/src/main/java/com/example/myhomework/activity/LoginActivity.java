package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.R;
import com.example.myhomework.databinding.ActivityLoginBinding;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.service.UserService;
import com.example.myhomework.utils.SaveIdPasswordUtil;
import com.example.myhomework.utils.UiUtil;

import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UiUtil.hideActionBar(this);
        binding.gif.setMovieResource(R.drawable.there_is_a_bee);

        binding.id.setTranslationY(300);
        binding.password.setTranslationY(300);
        binding.login.setTranslationY(300);
        binding.register.setTranslationY(300);
        binding.checkbox.setTranslationY(300);
        binding.gif.setTranslationY(-300);

        binding.id.setAlpha(0);
        binding.password.setAlpha(0);
        binding.login.setAlpha(0);
        binding.register.setAlpha(0);
        binding.checkbox.setAlpha(0);
        binding.gif.setAlpha(0);

        binding.id.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.password.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.login.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.register.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.checkbox.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.gif.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.lottie.animate().translationY(0).alpha(0).setDuration(2000).setStartDelay(400).start();

        //??????????????????
        Map<String,String> userInfo= SaveIdPasswordUtil.getUserInfo(this);
        binding.id.setText(userInfo.get("account"));
        binding.password.setText(userInfo.get("password"));

        binding.login.setOnClickListener(v -> {
            loginCheck(binding.id,binding.password);
            UiUtil.onClickAnimator(this,binding.login);
        });
        binding.register.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }

    //????????????????????????
    private void loginCheck(EditText userID,EditText userPassWord){
        //??????????????????????????????
        String name = userID.getText().toString();
        String password = userPassWord.getText().toString();
        UserService.Login(name,password,this);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(this,"?????????????????????",Toast.LENGTH_LONG).show();
        } else{
            UserService.Login(name,password,this);
            if(binding.checkbox.isChecked()){
                if(SaveIdPasswordUtil.saveUserInfo(this,name,password)){
                    GlobalMemory.PrintLog("LoginActivity???????????????????????????");
                }
            }
            else{
                SaveIdPasswordUtil.delectUserInfo(this);
            }
        }
    }
}