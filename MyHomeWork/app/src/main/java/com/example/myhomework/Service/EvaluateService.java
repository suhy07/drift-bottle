package com.example.myhomework.Service;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.myhomework.Activity.LoginActivity;
import com.example.myhomework.Bean.Evaluate;
import com.example.myhomework.Global.GlobalMemory;
import com.example.myhomework.Utils.HttpUtils;
import com.example.myhomework.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EvaluateService {
    static String TAG="EvaluateService";
    private EvaluateService(){}
    public static void addEvaluate(Evaluate evaluate, Activity activity){
        new Thread(()->{
            String eid;
            int eid_i = 0;
            Connection connection = JDBCUtils.Connection();
            String sql="SELECT * from evaluate order by eid DESC";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try {
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    eid=resultSet.getString("eid");
                    eid_i=Integer.valueOf(eid);
                }
            }catch (Exception e){
                GlobalMemory.PrintLog(TAG+e.getMessage());
                GlobalMemory.PrintLog(TAG+sql);
            }
            sql="insert into evaluate values(?,?,?,?,?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,(eid_i+1)+"");
                preparedStatement.setString(2,evaluate.getUid());
                preparedStatement.setString(3,evaluate.getHid());
                preparedStatement.setString(4,evaluate.getMsg());
                preparedStatement.setString(5,evaluate.getStar());;
                if(preparedStatement.executeUpdate()>=0){
                    activity.runOnUiThread(()-> Toast.makeText(activity,"提交成功",Toast.LENGTH_LONG).show());
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
}
