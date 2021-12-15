package com.example.myhomework.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myhomework.R;
import com.example.myhomework.Service.UserService;
import com.example.myhomework.Util.SaveIdPasswordUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    Button login,register;
    EditText userID,userPassWord;
    CheckBox chBOX;
    String errorString;
    ImageView bg_login;
    LottieAnimationView lottie;
    static final Boolean LOGINSUCCESS=true,LOGINFAULT=true;
    //Map<String,String> userInfo=getUserInfo(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.button_Login_LoginActivity);
        register=findViewById(R.id.button_Register_LoginActivity);
        userID=findViewById(R.id.editText_UserID_LoginActivity);
        userPassWord=findViewById(R.id.editText_UserPassWord_LoginActivity);
        chBOX = findViewById(R.id.checkBox_LoginActivity);
        bg_login = findViewById(R.id.bg_login_background_LoginActivity);
        lottie = findViewById(R.id.lottieAnimationView);

        userID.setTranslationY(300);
        userPassWord.setTranslationY(300);
        login.setTranslationY(300);
        register.setTranslationY(300);
        chBOX.setTranslationY(300);
        bg_login.setTranslationY(-1000);

        userID.setAlpha(0);
        userPassWord.setAlpha(0);
        login.setAlpha(0);
        register.setAlpha(0);
        chBOX.setAlpha(0);
        bg_login.setAlpha(1000);

        userID.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        userPassWord.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        login.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        register.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        chBOX.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        bg_login.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        lottie.animate().translationY(0).alpha(0).setDuration(2000).setStartDelay(400).start();

        ////////////读取账户密码
        Map<String,String> userInfo= SaveIdPasswordUtil.getUserInfo(this);
        userID.setText(userInfo.get("account"));
        userPassWord.setText(userInfo.get("password"));
        Toast.makeText(this,"读取成功",Toast.LENGTH_LONG).show();

        login.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            if(loginCheck(userID,userPassWord)){
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(LoginActivity.this,errorString,Toast.LENGTH_LONG).show();
            }
        });

        register.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }

    //对账号密码做检验
    private boolean loginCheck(EditText userID,EditText userPassWord){

        //是否保存账号密码选项
        chBOX = findViewById(R.id.checkBox_LoginActivity);
        String name = userID.getText().toString();
        String password = userPassWord.getText().toString();
        boolean loginFlag= true;

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            errorString = "账户或密码为空";
            return LOGINFAULT;
        } else if (loginFlag) {
            if(chBOX.isChecked()){
                boolean isSaveSuccess=SaveIdPasswordUtil.saveUserInfo(this,name,password);
                Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
            }
            else{
                SaveIdPasswordUtil.delectUserInfo(this);
                Toast.makeText(this,"账号密码未保存",Toast.LENGTH_LONG).show();
            }
            return LOGINSUCCESS;
        }  else {
            errorString = "账户或密码错误";
            return LOGINFAULT;
        }
    }
}