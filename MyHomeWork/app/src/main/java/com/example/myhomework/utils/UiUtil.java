package com.example.myhomework.utils;

import android.app.Activity;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UiUtil {
    public static void hideActionBar(AppCompatActivity activity){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_NOTHING);
//        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
