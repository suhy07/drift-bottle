package com.example.myhomework.service;

import static com.example.myhomework.global.GlobalMemory.TAG;
import static com.example.myhomework.global.GlobalMemory.UserId;

import android.app.Activity;
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
import com.example.myhomework.utils.UiUtil;

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
                    GlobalMemory.UserId = resultSet.getString("uid");
                    activity.startActivity(new Intent(activity, MainActivity.class));
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

    public static void ChangePassWord(String nickname, String password, Activity activity){
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql="UPDATE user set nickname='"+nickname+"', password = '"+password +"' where uid ='"+UserId+"'";
            PreparedStatement preparedStatement;
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                UiUtil.ShowToast(activity,"修改成功");
                Intent intent=new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
                UiUtil.ShowToast(activity,e.getMessage());
            }
        }).start();
    }

}
