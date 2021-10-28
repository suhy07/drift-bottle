package com.example.myhomework.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhomework.R;

public class RegisterActivity extends AppCompatActivity {
    Button register1;
    EditText UserID1,UserPassWord1,UserPassWord2;
    String errorString;
    static final Boolean REGISTERSUCCESS=true,REGISTERFAULT=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserID1 = findViewById(R.id.editText2);
        UserPassWord1=findViewById(R.id.editText1);
        UserPassWord2=findViewById(R.id.editText3);
        register1=findViewById(R.id.button123);

        register1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                if(register1(UserID1,UserPassWord1,UserPassWord2)){
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this,errorString,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean register1(EditText UserID1,EditText UserPassWord1,EditText UserPassWord2) {
        //TODO注册检查

        String name = UserID1.getText().toString().trim();
        String password1 = UserPassWord1.getText().toString().trim();
        String password2 = UserPassWord2.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password1)||TextUtils.isEmpty(password2)) {
            errorString = "账户或密码为空";
            return REGISTERFAULT;
        } else if (name.equals("abc") && password1.equals("123")&& password2.equals("123")) {
            return REGISTERSUCCESS;
        } else if (name.equals("def") && password1.equals("456")&& password2.equals("456")) {
            return REGISTERSUCCESS;
        } else {
            errorString = "注册失败";
            return REGISTERFAULT;
        }

    }

}