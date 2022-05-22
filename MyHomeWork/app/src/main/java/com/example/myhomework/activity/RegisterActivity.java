package com.example.myhomework.activity;

import static com.example.myhomework.utils.UiUtil.hideActionBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhomework.R;
import com.example.myhomework.databinding.ActivityRegisterBinding;
import com.example.myhomework.service.UserService;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideActionBar(this);
        binding.gif.setMovieResource(R.drawable.there_is_a_bee);
        binding.finish.setOnClickListener(v -> {
           register(binding.id,binding.nickname,binding.password,binding.passwordQuery);

        });
    }
    private void register(EditText UID,EditText UserID1,EditText UserPassWord1,EditText UserPassWord2) {
        //TODO注册检查
        String uid=UID.getText().toString().trim();
        String name = UserID1.getText().toString().trim();
        String password1 = UserPassWord1.getText().toString().trim();
        String password2 = UserPassWord2.getText().toString().trim();
        String nickname = binding.nickname.getText().toString();
        if (TextUtils.isEmpty(uid) ||TextUtils.isEmpty(name) || TextUtils.isEmpty(password1)
                ||TextUtils.isEmpty(password2)||TextUtils.isEmpty(nickname)) {
            Toast.makeText(this,"邮箱、用户名、密码不能为空",Toast.LENGTH_LONG).show();
        } else if (!password1.equals(password2)) {
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_LONG).show();
        } else {
            UserService.Register(uid,password1,nickname,this);
        }
    }
}