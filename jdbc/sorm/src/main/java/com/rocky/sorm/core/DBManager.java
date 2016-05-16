package com.rocky.sorm.core;

import com.rocky.sorm.bean.Configuration;
import com.rocky.sorm.pool.DBConnPool;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBManager {
    /**
     * 配置信息
     */
    private static Configuration conf;
    /**
     * 连接池对象
     */
    private static DBConnPool pool;

    static {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        conf = new Configuration();
        conf.setDriver(props.getProperty("driver"));
        conf.setUrl(props.getProperty("url"));
        conf.setUser(props.getProperty("user"));
        conf.setPwd(props.getProperty("pwd"));
        conf.setUsingDB(props.getProperty("usingDB"));
        conf.setPoPackage(props.getProperty("poPackage"));
        conf.setSrcPath(props.getProperty("srcPath"));
        conf.setQueryClass(props.getProperty("queryClass"));
        conf.setPoolMinSize(Integer.parseInt(props.getProperty("poolMinSize")));
        conf.setPoolMaxSize(Integer.parseInt(props.getProperty("poolMaxSize")));
    }

    public static Connection getConnection(){
        if (pool == null){
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }

    public static Connection createConnection() {

        try {
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(),
                    conf.getUser(), conf.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(ResultSet rs,Statement ps,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
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

    public static void close(Statement ps,Connection conn){
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
    public static void close(Connection conn){
        pool.closeConnection(conn);
    }
    public static Configuration getConf() {
        return conf;
    }
}
