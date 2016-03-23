package com.rocky.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class Demo_05_BLOB {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InputStream is  = null;
        OutputStream os = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			ps = conn.prepareStatement("insert into t_user (name,img) values (?,?)");
			ps.setString(1, "rocky");
			ps.setBlob(2, new FileInputStream(path + "/img.jpg"));
			ps.execute();

            ps = conn.prepareStatement("select * from t_user where id=?");
            ps.setObject(1, 1);

            rs = ps.executeQuery();
            while(rs.next()){
                Blob b = rs.getBlob("img");
                is  = b.getBinaryStream();
                os = new FileOutputStream("D:/a.jpg");
                int temp = 0;
                while((temp=is.read())!=-1){
                    os.write(temp);
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(is!=null){
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if(os!=null){
                    os.close();
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
