package com.example.myhomework.Util;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myhomework.Activity.MainActivity;
import com.example.myhomework.Global.GlobalMemory;

import org.w3c.dom.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;


public class InitUserDataUtil {
    public static InitUserDataUtil initUserDataUtil=new InitUserDataUtil();
    private static String uid;
    private static String userNickName;
    private static UserType userType;
    public static Bitmap userHeadBitmap;
    private InitUserDataUtil() {
    }
    private static InitUserDataUtil GetInitUserDataUtil(){
        return initUserDataUtil;
    }
    public static void ResetUserData(){

        new Thread(() -> {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            Connection connection =JDBCUtil.Connection();
            String sql = "SELECT * from user;";
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    uid=resultSet.getString("uid");
                    userNickName=resultSet.getString("userNickName");
                    userType=UserType.InitFromStr(resultSet.getString("userType"));
                }
            } catch (SQLException e) {
            } finally {
                JDBCUtil.closeAll(resultSet,preparedStatement,connection);
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
        return userType.typeStr;
    }
    public static void GetUserHead(){
        userHeadBitmap=HttpUtil.getURLimage(GlobalMemory.FileServerUri+"/downloadFile/app_icon.png");
    }
    protected enum UserType{
        Normal("普通用户",1),Admin("管理员",2);
        private String typeStr;
        private int index;
        private UserType(String typeStr,int index){
            this.typeStr=typeStr;this.index=index;}
        public static UserType InitFromStr(String str){
            switch (str){
                case "普通用户":return Normal;
                case "管理员":return Admin;
            }
            return null;
        }
    }
}

