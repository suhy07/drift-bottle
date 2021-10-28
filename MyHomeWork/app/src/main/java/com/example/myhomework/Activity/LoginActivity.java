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
        Map<String,String> userInfo=Save_Id_Password.getUserInfo(this);
        userID.setText(userInfo.get("account"));
        userPassWord.setText(userInfo.get("password"));
        Toast.makeText(this,"读取成功",Toast.LENGTH_LONG).show();


        login.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                if(loginCheck(userID,userPassWord)){
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,errorString,Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //对账号密码做检验
    private boolean loginCheck(EditText userID,EditText userPassWord){

        //是否保存账号密码选项
        chBOX = findViewById(R.id.checkBox_LoginActivity);
        int code=0;
        String name = userID.getText().toString();
        String password = userPassWord.getText().toString();

        try
        {
            String str="http://49.235.134.191:8080/user/login?account="+name+"&password="+password;
            URL url = new URL(str);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为Get请求
            conn.setRequestMethod("GET");
            conn.connect();//获取连接
            InputStream inputStream=conn.getInputStream();//获取输入流
            StringBuilder sb1 = new StringBuilder();//创建读取
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb1.append(line);
            }
            String s = sb1.toString();
            JSONObject jsonObj  =  new JSONObject(s);
            code= jsonObj.optInt("code");


        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            errorString = "账户或密码为空";
            return LOGINFAULT;
        } else if (code==200) {
            if(chBOX.isChecked()){
                boolean isSaveSuccess=Save_Id_Password.saveUserInfo(this,name,password);
                Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
            }
            else{
                Save_Id_Password.delectUserInfo(this);
                Toast.makeText(this,"账号密码未保存",Toast.LENGTH_LONG).show();
            }
            return LOGINSUCCESS;
        }  else {
            errorString = "账户或密码错误";
            return LOGINFAULT;
        }
    }
}