package com.example.myhomework.Util;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;

public class PhotoUtil {
    public static void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        //startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }
}
