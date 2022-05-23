package com.example.myhomework.global;

import android.util.Log;

import com.baidu.location.Poi;
import com.example.myhomework.bean.MapRecord;

import java.util.ArrayList;
import java.util.List;

public class GlobalMemory {
    public static String TAG="$%Bottle%$";
    public static String FileServerUri="http://47.98.173.217:8080";
    public static String FileServerDownloadFileUri=FileServerUri+"/downloadFile";
    public static String FileServerUploadFileUri="http://47.98.173.217:8080/uploadFile";
    public static String DateBaseurl = "jdbc:mysql://47.98.173.217:3307/bottle";
    public static String MysqlUser = "bottle";
    public static String MysqlPassword = "Bottle@123zxc";
    public static String NickName = "我的超人";
    public static String UserId = "";
    public static List<MapRecord> MapRecordList = new ArrayList<>();
    public static double Longitude = 1;
    public static double Latitude = 1;
    public static String Address = "未知地点";
    public static List<String> PoiNameList = new ArrayList<>();
    public static List<Poi> PoiList = new ArrayList<>();
    public static double Limit = 500;
    public static void PrintLog(String msg){
        Log.d(TAG,msg);
    }
}
