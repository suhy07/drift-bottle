package com.example.myhomework.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
    public static JDBCUtil jdbcUtil=new JDBCUtil();
    public static Connection connection;
    private JDBCUtil(){
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeAll();
    }

    private void Connection() {
        Connection connection = null;
        try {
            String SDdriver = "com.mysql.jdbc.Driver";
            String SDurl = "jdbc:mysql://localhost:3306/emsdb?characterEncoding=utf8&useSSL=true";
            //emsdb替换成自己的数据库名称
            String SDuser = "root";
            String SDpassword = "123456";
            Class.forName(SDdriver);
            connection = DriverManager.getConnection(SDurl, SDuser, SDpassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }
    }

    public static void closeAll(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet) {
        try {
    //按顺序关闭
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
