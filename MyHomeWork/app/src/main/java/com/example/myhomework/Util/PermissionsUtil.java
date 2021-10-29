package com.example.myhomework.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionsUtil {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void verifyStoragePermissions(Activity activity) {
        for (String permissions:PERMISSIONS_STORAGE) {
            try {
                //检测是否有写的权限
                int permission = ActivityCompat.checkSelfPermission(activity,
                        permissions);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 没有写的权限，去申请写的权限，会弹出对话框
                    ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
