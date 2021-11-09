package com.example.myhomework.Util;

import android.util.Log;

import com.example.myhomework.Global.GlobalMemory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicReference;

public class JDBCUtil {
    public static JDBCUtil jdbcUtil=new JDBCUtil();
    private static  String url = "jdbc:mysql://47.98.173.217:3306/schoolphoto";
    private static String MysqlUser = "schoolphoto";
    private static String MysqlPassword = "Photo@123zxc";
    private JDBCUtil(){
        Connection();
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
                Log.d("ttt", url);
                connection=DriverManager.getConnection(url, MysqlUser, MysqlPassword);
            } catch (Exception e) {
                Log.d("ttt", e.toString());
                e.printStackTrace();
            }

        return connection;
        /*
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String ss = new String("SELECT * from employer;");
        try {
            preparedStatement = connection.prepareStatement(ss);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ename"));
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }*/
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
