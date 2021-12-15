package com.example.myhomework.Service;

import android.graphics.Bitmap;

import com.example.myhomework.Global.GlobalMemory;
import com.example.myhomework.Util.HttpUtil;
import com.example.myhomework.Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static String uid;
    private static String userNickName;
    private static UserType userType;
    public static Bitmap userHeadBitmap;
    public static boolean Login(String id, String password){
        boolean success=true;
        boolean fail=false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = JDBCUtil.Connection();
        String sql="SELECT * from user where uid="+id+"password="+password;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                GlobalMemory.PrintLog(resultSet.getString(0));
                return success;
            }
        }catch (Exception e){
            GlobalMemory.PrintLog(e.getMessage());
        }
        return fail;
    }
    public static void ResetUserData(){

        new Thread(() -> {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            Connection connection = JDBCUtil.Connection();
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
        userHeadBitmap= HttpUtil.getURLimage(GlobalMemory.FileServerUri+"/downloadFile/app_icon.png");
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
