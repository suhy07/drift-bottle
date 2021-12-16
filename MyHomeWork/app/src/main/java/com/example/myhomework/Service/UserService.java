package com.example.myhomework.Service;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.myhomework.Activity.LoginActivity;
import com.example.myhomework.Activity.MainActivity;
import com.example.myhomework.Global.GlobalMemory;
import com.example.myhomework.Utils.HttpUtils;
import com.example.myhomework.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static String uid;
    private static String userNickName;
    private static String userType;
    private static String head;
    public static Bitmap userHeadBitmap;
    private static String TAG="UserService:";
    public static void Login(String id, String password, Activity activity){
        new Thread(() -> {
            Connection connection = JDBCUtils.Connection();
            String sql="SELECT * from user where uid='"+id+"' and password='"+password+"'";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.runOnUiThread(()->   Toast.makeText(activity,"登陆成功",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"登陆成功");
                    InitUserData(id);
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
    public static void Register(String uid,String userNickName,String password,Activity activity){
        new Thread(() -> {
            Connection connection = JDBCUtils.Connection();
            String sql="select * from user where uid='"+uid+"'";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    activity.runOnUiThread(()->Toast.makeText(activity,"注册失败，该账号已被注册",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"注册失败，该账号已被注册");
                    return;
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
            sql="insert into user values(?,?,'普通用户',?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,uid);
                preparedStatement.setString(2,userNickName);
                preparedStatement.setString(3,password);
                if(preparedStatement.executeUpdate()>=0){
                    activity.runOnUiThread(()->Toast.makeText(activity,"注册成功",Toast.LENGTH_LONG).show());
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    GlobalMemory.PrintLog(TAG+"注册成功");
                    activity.finish();
                }else{
                    activity.runOnUiThread(()->Toast.makeText(activity,"注册失败，服务端连接失败",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"注册失败");
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
        }).start();
    }
    private static void InitUserData(String _uid){
        new Thread(() -> {
            String muid=_uid;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            Connection connection = JDBCUtils.Connection();
            String sql = "SELECT * from user where uid='"+muid+"'";
            GlobalMemory.PrintLog(sql);
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    uid=resultSet.getString("uid");
                    userNickName=resultSet.getString("userNickName");
                    userType=resultSet.getString("userType");
                    head=resultSet.getString("head");
                    GlobalMemory.PrintLog("初始化\nuid："+uid+"\nname"+userNickName+"\nhead:"+head);
                }
            } catch (SQLException e) {
                GlobalMemory.PrintLog(TAG+e.getMessage());
            } finally {
                JDBCUtils.closeAll(resultSet,preparedStatement,connection);
            }
        }).start();
        GetUserHead();
    }
    public static void ResetUserData(){
        new Thread(() -> {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            Connection connection = JDBCUtils.Connection();
            String sql = "SELECT * from user where uid='"+uid+"'";
            GlobalMemory.PrintLog(sql);
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    uid=resultSet.getString("uid");
                    userNickName=resultSet.getString("userNickName");
                    userType=resultSet.getString("userType");
                    head=resultSet.getString("head");
                    GlobalMemory.PrintLog("初始化\nuid："+uid+"\nname"+userNickName+"\nhead:"+head);
                }
            } catch (SQLException e) {
                GlobalMemory.PrintLog(TAG+e.getMessage());
            } finally {
                JDBCUtils.closeAll(resultSet,preparedStatement,connection);
            }
        }).start();
        GetUserHead();

    }
    public static String GetUid(){
        return uid;
    }
    public static String GetUserNickName(){
        return userNickName;
    }
    public static String GetUserType(){
        return userType;
    }
    public static void GetUserHead(){
        userHeadBitmap= HttpUtils.getURLimage(GlobalMemory.FileServerUri+"/downloadFile/"+head);
    }
}
