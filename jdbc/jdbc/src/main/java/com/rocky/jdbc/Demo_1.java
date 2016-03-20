package com.rocky.jdbc;

import java.sql.*;

public class Demo_1 {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            String sql = "INSERT INTO t_user(id, name, age, birthday) VALUES (?,?,?,?)";//占位符从1开始，而不是0

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, "rocky");
            preparedStatement.setString(3, "20");
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));

            //可以使用Object处理参数
            //        preparedStatement.setObject(1, "1");
            //        preparedStatement.setObject(2, "rocky");
            //        preparedStatement.setObject(3, "20");
            //        preparedStatement.setObject(4, new Date(System.currentTimeMillis()));

            int count = preparedStatement.executeUpdate();
            System.out.println(count);

            String querySql = "select * from t_user";

            resultSet = preparedStatement.executeQuery(querySql);
            while (resultSet.next())
            {
                String result =  resultSet.getString(1) + "-" + resultSet.getString(2) + "-"
                        + resultSet.getString(3) + "-" + resultSet.getString(4);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
