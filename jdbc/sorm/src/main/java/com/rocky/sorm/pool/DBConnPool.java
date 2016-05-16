package com.rocky.sorm.pool;

import com.rocky.sorm.core.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnPool {

    /**
     * 连接池对象
     */
    private List<Connection> pool;

    /**
     * 最小连接数
     */
    private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();

    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();

    public DBConnPool() {
        initPool();
    }

    /**
     * 初始化连接池
     */
    public void initPool() {
        if (pool == null){
            pool = new ArrayList<Connection>();
        }
        while (pool.size() < POOL_MIN_SIZE){
            pool.add(DBManager.createConnection());
        }
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        int lastIndex = pool.size() - 1;
        Connection conn = pool.get(lastIndex);
        pool.remove(conn);
        return conn;
    }

    /**
     * 关闭连接
     * @param conn
     */
    public void closeConnection(Connection conn) {
        if (pool.size() >= POOL_MAX_SIZE){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            pool.add(conn);
        }
    }
}
