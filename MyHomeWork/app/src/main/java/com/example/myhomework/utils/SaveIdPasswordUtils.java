package com.example.myhomework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SaveIdPasswordUtils {

    //保存账号密码到data.xml
    public static boolean saveUserInfo(Context context, String account, String password){
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("UserId",account);
        edit.putString("UserPassWord",password);
        edit.commit();
        return true;
    }
    public static void delectUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    //从data.xml读取账号密码
    public static Map<String,String> getUserInfo(Context context){
        SharedPreferences sp= context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String account =sp.getString("UserId",null);
        String password =sp.getString("UserPassWord",null);
        Map<String,String> UserMap=new HashMap<String,String>();
        UserMap.put("account",account);
        UserMap.put("password",password);
        return  UserMap;
    }
}
