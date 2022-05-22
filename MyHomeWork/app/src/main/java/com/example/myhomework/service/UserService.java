package com.example.myhomework.service;

import static com.example.myhomework.global.GlobalMemory.TAG;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.activity.LoginActivity;
import com.example.myhomework.activity.MainActivity;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void Login(String id, String password, AppCompatActivity activity) {
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql="SELECT * from user where uid='"+id+"' and password='"+password+"'";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    GlobalMemory.NickName = resultSet.getString("nickname");
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.runOnUiThread(()->   Toast.makeText(activity,"登陆成功",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"登陆成功");
                    activity.finish();
                }else{
                    activity.runOnUiThread(()->   Toast.makeText(activity,"账号或密码错误",Toast.LENGTH_LONG).show());
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
                activity.runOnUiThread(()->   Toast.makeText(activity,e.getMessage(),Toast.LENGTH_LONG).show());
            }
        }).start();
    }

    public static void Register(String id, String password, String nickname, AppCompatActivity activity) {
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql="SELECT * from user where uid='"+id+"'";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    activity.runOnUiThread(()->   Toast.makeText(activity,"邮箱已被注册",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"邮箱已被注册");
                }else{
                    sql = "INSERT into user values('"+id+"','"+password+"','"+nickname+"')";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.executeUpdate();
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    activity.runOnUiThread(()->   Toast.makeText(activity,"注册成功",Toast.LENGTH_LONG).show());
                    activity.finish();
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
                activity.runOnUiThread(()->   Toast.makeText(activity,e.getMessage(),Toast.LENGTH_LONG).show());
            }
        }).start();
    }

}
