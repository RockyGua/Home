package com.rocky.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo_06_jdbc {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getMysqlConn();

            ps = conn.prepareStatement("insert into t_user (name) values (?)");
            ps.setString(1, "rocky");
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs, ps, conn);
        }
    }
}
