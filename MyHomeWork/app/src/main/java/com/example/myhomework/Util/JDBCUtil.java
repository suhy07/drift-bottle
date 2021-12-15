package com.example.myhomework.Util;

import static com.example.myhomework.Global.GlobalMemory.*;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
    public static JDBCUtil jdbcUtil=new JDBCUtil();

    private JDBCUtil(){
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static Connection Connection() {
        Connection connection=null;
        try {
            String SDdriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(SDdriver);
            connection=DriverManager.getConnection(DateBaseurl, MysqlUser, MysqlPassword);
            Log.d(TAG, "连接成功"+DateBaseurl);
        } catch (Exception e) {
            Log.d(TAG, "连接失败"+e.getMessage()+"    url:"+DateBaseurl+"    id:"+MysqlUser+"    password:"+MysqlPassword);
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeAll(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection) {
        try {
            if(resultSet!=null) {
                resultSet.close();
            }
            if (preparedStatement!=null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
