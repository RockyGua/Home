package com.rocky.jdbc;

import java.sql.*;

public class Demo_batch {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            connection.setAutoCommit(false);//设置为手动提交
            //对于大量数据的批处理，建议使用statement,因为preparedStatement的预编译空间有限，当数据量过大时会发生异常
            statement = connection.createStatement();
            for (int i=0; i<10000; i++) {
                statement.addBatch("INSERT INTO t_user(name, age, birthday) VALUES ('rocky" + i + "','20', now() )");
            }
            statement.executeBatch();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
