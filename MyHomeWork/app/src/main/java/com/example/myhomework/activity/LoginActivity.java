package com.example.myhomework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.R;
import com.example.myhomework.service.UserService;
import com.example.myhomework.utils.SaveIdPasswordUtils;

import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    Button login,register;
    EditText userID,userPassWord;
    CheckBox chBOX;
    String errorString;
    ImageView bg_login;
    LottieAnimationView lottie;
    static final Boolean LOGINSUCCESS=true,LOGINFAULT=false;
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

        //读取账户密码
        Map<String,String> userInfo= SaveIdPasswordUtils.getUserInfo(this);
        userID.setText(userInfo.get("account"));
        userPassWord.setText(userInfo.get("password"));
        int t=userPassWord.getInputType();
        userPassWord.setOnTouchListener((v, event) -> {
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                userPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }else if(event.getAction()==MotionEvent.ACTION_UP){
                userPassWord.setInputType(t);
            }
            return false;
        });
        login.setOnClickListener(v -> {
            loginCheck(userID,userPassWord);
        });
        register.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }

    //对账号密码做检验
    private void loginCheck(EditText userID,EditText userPassWord){
        //是否保存账号密码选项
        chBOX = findViewById(R.id.checkBox_LoginActivity);
        String name = userID.getText().toString();
        String password = userPassWord.getText().toString();
        UserService.Login(name,password,this);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(this,"账户或密码为空",Toast.LENGTH_LONG).show();
        } else{
            UserService.Login(name,password,this);
            if(chBOX.isChecked()){
                if(SaveIdPasswordUtils.saveUserInfo(this,name,password)){
                    GlobalMemory.PrintLog("LoginActivity：账号密码保存成功");
                }
            }
            else{
                SaveIdPasswordUtils.delectUserInfo(this);
            }
        }
    }
}