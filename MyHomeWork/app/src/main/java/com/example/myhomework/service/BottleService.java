package com.example.myhomework.service;

import static com.example.myhomework.global.GlobalMemory.TAG;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.example.myhomework.bean.MapRecord;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.utils.JDBCUtil;
import com.example.myhomework.utils.UiUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BottleService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void showBottle(int id, Activity activity, TextView title, BaiduMap baiduMap, EditText describe){
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql = "SELECT * from point where id =" + id;
            GlobalMemory.PrintLog(TAG+sql);
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                activity.runOnUiThread(()->{
                    try {
                        double x = resultSet.getDouble("x");
                        double y = resultSet.getDouble("y");
                        String type = resultSet.getString("type");
                        String address = resultSet.getString("address");
                        String titleStr = resultSet.getString("title");
                        String describeStr = resultSet.getString("describe");
                        MapRecord mapRecord = new MapRecord(type,address,x,y);
                        LatLng latLng = new LatLng(mapRecord.getX(), mapRecord.getY());
                        MapStatusUpdate status1 = MapStatusUpdateFactory.newLatLng(latLng);
                        baiduMap.setMapStatus(status1);
                        MapService.addPoint(baiduMap,mapRecord);

                        title.setText("'"+address+"'的漂流瓶---"+titleStr);
                        describe.setText(describeStr);

                    } catch (SQLException e) {
                        GlobalMemory.PrintLog(TAG+e.getMessage());
                        GlobalMemory.PrintLog(TAG+sql);
                        UiUtil.ShowToast(activity,e.getMessage());
                    }
                });
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
        }).start();
    }
}
