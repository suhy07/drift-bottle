package com.example.myhomework.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhomework.BottleApplication;

public class UiUtil {
    public static void hideActionBar(AppCompatActivity activity){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_NOTHING);
//        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    public static void ShowToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
