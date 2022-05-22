package com.example.myhomework.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;

public class SDKReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Toast.makeText(context.getApplicationContext(), "KEY 开始验证"+action, Toast.LENGTH_SHORT).show();
        if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
            Log.d("MainActivity SDk ", "onReceive: 验证失败");
            Toast.makeText(context.getApplicationContext(), "KEY 验证失败！", Toast.LENGTH_SHORT).show();
        } else if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
            Log.d("MainActivity SDk ", "onReceive: 验证成功");
            Toast.makeText(context.getApplicationContext(), "KEY 验证成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
