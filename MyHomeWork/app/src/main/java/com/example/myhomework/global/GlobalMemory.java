package com.example.myhomework.global;

import android.util.Log;

public class GlobalMemory {
    public static String TAG="$%SchoolPhoto%$";
    public static String FileServerUri="http://47.98.173.217:8080";
    public static String FileServerDownloadFileUri=FileServerUri+"/downloadFile";
    public static String FileServerUploadFileUri="http://47.98.173.217:8080/uploadFile";
    public static String DateBaseurl = "jdbc:mysql://47.98.173.217:3307/schoolphoto";
    public static String MysqlUser = "schoolphoto";
    public static String MysqlPassword = "Photo@123zxc";
    public static void PrintLog(String msg){
        Log.d(TAG,msg);
    }
}
