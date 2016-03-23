package com.rocky.jdbc;

import java.io.*;
import java.sql.*;

public class Demo_04_CLOB {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reader r  = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

//            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//            ps = conn.prepareStatement("insert into t_user (name,myInfo) values (?,?) ");
//            ps.setString(1, "rocky");
//			ps.setClob(2, new FileReader(new File(path + "/a.txt")));  //将文本文件内容直接输入到数据库中
//			// 将程序中的字符串输入到数据库的CLOB字段中
////			ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("aaaabbbbbb".getBytes()))));
//
//            ps.execute();

            ps = conn.prepareStatement("select * from t_user where id=?");
            ps.setObject(1, 1);

            rs = ps.executeQuery();
            while(rs.next()){
                Clob c = rs.getClob("myInfo");
                r  = c.getCharacterStream();
                int temp = 0;
                while((temp=r.read())!=-1){
                    System.out.print((char) temp);
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(r!=null){
                    r.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
