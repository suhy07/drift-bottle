package com.example.myhomework.service;

import static com.example.myhomework.global.GlobalMemory.Address;
import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Longitude;
import static com.example.myhomework.global.GlobalMemory.TAG;
import static com.example.myhomework.global.GlobalMemory.MapRecordList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.myhomework.R;
import com.example.myhomework.activity.LoginActivity;
import com.example.myhomework.activity.MainActivity;
import com.example.myhomework.bean.MapRecord;
import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MapService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void refreshPointList(BaiduMap baiduMap, RecyclerView.Adapter adapter){
        new Thread(() -> {
            List<MapRecord> mapRecords = new ArrayList<>();
            Connection connection = JDBCUtil.Connection();
            String sql="SELECT * from point";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    MapRecord mapRecord = new MapRecord(
                            resultSet.getString("type"),
                            resultSet.getString("address"),
                            resultSet.getDouble("x"),
                            resultSet.getDouble("y")
                    );
                    mapRecords.add(mapRecord);
                    //定义Maker坐标点
                    LatLng point = new LatLng(mapRecord.getX(), mapRecord.getY());
                    //构建Marker图标
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.bottle);;
                    switch (mapRecord.getRecordType()){
                        case Board:
                            bitmap= BitmapDescriptorFactory
                                    .fromResource(R.drawable.board); break;
                        case Bottle:
                            bitmap= BitmapDescriptorFactory
                                    .fromResource(R.drawable.bottle); break;
                    }
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .animateType(MarkerOptions.MarkerAnimateType.jump)
                            .position(point)
                            .icon(bitmap)
                            .alpha(0.9f)
                            .scaleX(0.1f)
                            .scaleY(0.1f)
                            .perspective(true);
                    //在地图上添加Marker，并显示
                    baiduMap.addOverlay(option);
                }
                MapRecordList.clear();
                for(MapRecord mapRecord: mapRecords){
                    MapRecordList.add(mapRecord);
                }
                new MainActivity().runOnUiThread(()->{
                    adapter.notifyDataSetChanged();
                });
//
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
        }).start();
    }

    public static void addBottle(String title,String describe,String author){
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql = "INSERT into user values('null',"+Longitude+","+Latitude+",'"+title+"','"
                    +Address+"','"+describe+"','Bottle','"+author+"')";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
        }).start();
    }
}
