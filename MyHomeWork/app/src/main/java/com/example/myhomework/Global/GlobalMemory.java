package com.example.myhomework.Global;

import android.util.Log;

import java.util.Stack;

public class GlobalMemory {
    public static String TAG="$%SchoolPhoto%$";
    public static Stack<Integer> PageStack;
    public static String FileServerUri="http://47.98.173.217:8080";
    public static String DateBaseurl = "jdbc:mysql://47.98.173.217:3307/schoolphoto";
    public static String MysqlUser = "schoolphoto";
    public static String MysqlPassword = "Photo@123zxc";

    public static void PrintLog(String msg){
        Log.d(TAG,msg);
    }
}
