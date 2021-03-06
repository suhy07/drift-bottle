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
import com.example.myhomework.activity.MessageActivity;
import com.example.myhomework.adapter.MessageAdapter;
import com.example.myhomework.bean.MapRecord;
import com.example.myhomework.bean.Message;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.utils.JDBCUtil;
import com.example.myhomework.utils.UiUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void showBoard(int id, Activity activity, TextView title, BaiduMap baiduMap, MessageAdapter adapter){
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql = "SELECT * from point where id =?";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,id);
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
                        title.setText("'"+address+"'????????????---"+titleStr);

                    } catch (SQLException e) {
                        GlobalMemory.PrintLog(TAG+e.getMessage());
                        UiUtil.ShowToast(activity,e.getMessage());
                    }
                });
                sql = "SELECT * from message where board =?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,id);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                MessageActivity.messageList.clear();
                while (resultSet1.next()){
                    int id1 = resultSet1.getInt("id");
                    String author = resultSet1.getString("author");
                    String messageStr = resultSet1.getString("message");
                    Message message = new Message(id1, author, messageStr);
                    MessageActivity.messageList.add(message);
                }
                activity.runOnUiThread(()->{
                    adapter.notifyDataSetChanged();
                });
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
        }).start();
    }

    public static void addMessage(String message, String author, int board, Activity activity) {
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql = "INSERT into message values(null,?,?,?)";
            GlobalMemory.PrintLog(TAG+sql);
            PreparedStatement preparedStatement;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,message);
                preparedStatement.setString(2,author);
                preparedStatement.setInt(3,board);
                preparedStatement.executeUpdate();
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
            UiUtil.ShowToast(activity,"??????????????????");
            activity.finish();
        }).start();
    }

    public static void showMessage(int id, Activity activity, TextView title, BaiduMap baiduMap, EditText describe){
        new Thread(() -> {
            Connection connection = JDBCUtil.Connection();
            String sql = "SELECT * from message where id =?";
            GlobalMemory.PrintLog(TAG+sql);
            PreparedStatement preparedStatement,mapPre;
            ResultSet resultSet, mapResultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,id);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                String mapSql = "SELECT * from point where id = ?";
                mapPre = connection.prepareStatement(mapSql);
                mapPre.setInt(1,resultSet.getInt("board"));
                mapResultSet = mapPre.executeQuery();
                mapResultSet.next();
                activity.runOnUiThread(()->{
                    try {
                        String message = resultSet.getString("message");

                        double x = mapResultSet.getDouble("x");
                        double y = mapResultSet.getDouble("y");
                        String type = mapResultSet.getString("type");
                        String address = mapResultSet.getString("address");
                        String titleStr = mapResultSet.getString("title");
                        String describeStr = mapResultSet.getString("describe");

                        MapRecord mapRecord = new MapRecord(type,address,x,y);
                        LatLng latLng = new LatLng(mapRecord.getX(), mapRecord.getY());
                        MapStatusUpdate status1 = MapStatusUpdateFactory.newLatLng(latLng);
                        baiduMap.setMapStatus(status1);
                        MapService.addPoint(baiduMap,mapRecord);

                        title.setText("'"+address+"'????????????---"+titleStr);
                        describe.setText(message);

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
