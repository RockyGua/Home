package com.rocky.jdbc;

import java.sql.*;

public class Demo_transaction {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            connection.setAutoCommit(false);//设置为手动提交

            String sql = "insert into t_user(name,age,birthday) values(?,?,?) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "rocky1");
            preparedStatement.setObject(2, "20");
            preparedStatement.setObject(3, new Date(System.currentTimeMillis()));
            preparedStatement.execute();

            String sql1 = "insert into t_user(name,age,birthday) values(?,?) ";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setObject(1, "rocky2");
            preparedStatement.setObject(2, "22");
            preparedStatement.execute();

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
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
