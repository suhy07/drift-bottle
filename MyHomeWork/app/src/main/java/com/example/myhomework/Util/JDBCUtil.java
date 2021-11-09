package com.example.myhomework.Util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
    public static JDBCUtil jdbcUtil=new JDBCUtil();
    private static  String url = "jdbc:mysql://47.98.173.217:3307/schoolphoto";
    private static String MysqlUser = "suhy";
    private static String MysqlPassword = "shy123321";
    private JDBCUtil(){
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static Connection Connection() {
        Connection connection=null;
       // String url = "jdbc:mysql://47.98.173.217:3306/schoolphoto";
        try {
            String SDdriver = "com.mysql.jdbc.Driver";
            Class.forName(SDdriver);
            connection=DriverManager.getConnection(url, MysqlUser, MysqlPassword);
            Log.d("ttt", url+"连接成功");
        } catch (Exception e) {
            Log.d("ttt1", e.toString());
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
