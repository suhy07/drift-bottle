package com.example.myhomework.utils;

import android.app.Activity;
import android.view.View;
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
    public static void ShowToast(Activity activity, String msg){
        activity.runOnUiThread(()->Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show());
    }

    public static void onClickAnimator(Activity activity,View view){
        view.animate().alpha(0.5f).setDuration(200).setStartDelay(0).start();
        new Thread(()->{
            try {
                Thread.sleep(200);
                activity.runOnUiThread(()->{
                    view.animate().alpha(1).setDuration(200).setStartDelay(0).start();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
