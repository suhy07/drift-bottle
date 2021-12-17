package com.example.myhomework.Service;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.myhomework.Activity.HistoricalRecordsActivity;
import com.example.myhomework.Activity.LoginActivity;
import com.example.myhomework.Bean.HistoryRecord;
import com.example.myhomework.Global.GlobalMemory;
import com.example.myhomework.Utils.HttpUtils;
import com.example.myhomework.Utils.JDBCUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryRecordService {
    private static String TAG="HistoryRecordService:";
    private HistoryRecordService(){}
    public static void addRecord(HistoryRecord historyRecord, Activity activity,String path){
        HttpUtils.Post_file(GlobalMemory.FileServerUploadFileUri,historyRecord.getImg(),path);
        new Thread(()->{
            String hid;
            int hid_i = 0;
            Connection connection = JDBCUtils.Connection();
            String sql="SELECT * from historyrecord order by hid DESC";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    hid=resultSet.getString("hid");
                    hid_i=Integer.valueOf(hid);
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
            sql="insert into historyrecord values(?,?,?,?,?,?,?,?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,(hid_i+1)+"");
                preparedStatement.setString(2,historyRecord.getTitle());
                preparedStatement.setString(3,historyRecord.getMsg());
                preparedStatement.setString(4,historyRecord.getImg());
                preparedStatement.setString(5,historyRecord.getQuestion());
                preparedStatement.setString(6,historyRecord.getLevel());
                preparedStatement.setString(7,historyRecord.getState());
                preparedStatement.setString(8,historyRecord.getUid());
                if(preparedStatement.executeUpdate()>=0){
                    activity.runOnUiThread(()->Toast.makeText(activity,"提交成功",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"提交成功");
                    activity.finish();
                }else{
                    activity.runOnUiThread(()->Toast.makeText(activity,"提交失败，服务端连接失败",Toast.LENGTH_LONG).show());
                    GlobalMemory.PrintLog(TAG+"提交失败");
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
        }).start();
    }
    public static void deleteRecord(){}
    public static void checkHistoryById(String id){
        new Thread(()->{
            ArrayList<HistoryRecord> arrayList=new ArrayList<>();
            Connection connection = JDBCUtils.Connection();
            String sql="SELECT * from historyrecord where uid='"+id+"'";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                while(resultSet.next()){
                    HistoryRecord historyRecord=new HistoryRecord();
                    historyRecord.setHid(resultSet.getString("hid"));
                    historyRecord.setTitle(resultSet.getString("title"));
                    historyRecord.setMsg(resultSet.getString("msg"));
                    historyRecord.setImg(resultSet.getString("img"));
                    historyRecord.setQuestion(resultSet.getString("question"));
                    historyRecord.setLevel(resultSet.getString("level"));
                    historyRecord.setState(resultSet.getString("state"));
                    historyRecord.setUid(resultSet.getString("uid"));
                    arrayList.add(historyRecord);
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
            HistoricalRecordsActivity.recall(arrayList);
        }).start();
    }
}
