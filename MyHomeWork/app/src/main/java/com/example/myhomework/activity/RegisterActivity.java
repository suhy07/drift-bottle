package com.example.myhomework.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhomework.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText UserName,UserPassWord1,UserPassWord2,UID;
    String errorString;
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UID=binding.uid;
        UserName=binding.username;
        UserPassWord1=binding.password;
        UserPassWord2=binding.password2;
        register=binding.button;

        register.setOnClickListener(v -> {
           register(UID,UserName,UserPassWord1,UserPassWord2);

        });
    }
    private void register(EditText UID,EditText UserID1,EditText UserPassWord1,EditText UserPassWord2) {
        //TODO注册检查
        String uid=UID.getText().toString().trim();
        String name = UserID1.getText().toString().trim();
        String password1 = UserPassWord1.getText().toString().trim();
        String password2 = UserPassWord2.getText().toString().trim();
        if (TextUtils.isEmpty(uid) ||TextUtils.isEmpty(name) || TextUtils.isEmpty(password1)||TextUtils.isEmpty(password2)) {
            Toast.makeText(this,"账号、用户名、密码不能为空",Toast.LENGTH_LONG).show();
        } else if (!password1.equals(password2)) {
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_LONG).show();
        } else {

        }
    }
}