package com.rocky.sorm.core;

import com.rocky.po.Emp;
import com.rocky.sorm.bean.ColumnInfo;
import com.rocky.sorm.bean.TableInfo;
import com.rocky.sorm.utils.ReflectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MysqlQuery implements Query {

    public static void main(String[] args){
        Emp e = new Emp();
        e.setId(0);
        new MysqlQuery().delete(e);
    }


    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i=0; i<params.length; i++){
                ps.setObject(i+1, params[i]);
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(ps, conn);
        }
        return 0;
    }

    public void insert(Object obj) {

    }

    public void delete(Class clazz, Object id) {
        //delete from emp where id = ?
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo columnInfo = tableInfo.getOnlyPriKey();

        String sql = "delete from " + tableInfo.getTname() + " where " + columnInfo.getName() + "=?";
        executeDML(sql, new Object[]{id});
    }

    public void delete(Object obj) {
        Class clazz = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo columnInfo = tableInfo.getOnlyPriKey();

        //通过反射机制，调用属性对应的get方法或set方法
        Object param = ReflectUtils.invokeGet(columnInfo.getName(), obj);
        delete(clazz, param);
    }

    public int update(Object obj, String[] fieldNames) {
        return 0;
    }

    public List queryRows(String sql, Class clazz, String[] params) {
        return null;
    }

    public Object queryUniqueRow(String sql, Class clazz, String[] params) {
        return null;
    }

    public Object queryValue(String sql, String[] params) {
        return null;
    }

    public Number queryNumber(String sql, String[] params) {
        return null;
    }
}
