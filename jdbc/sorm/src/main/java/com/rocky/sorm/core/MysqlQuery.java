package com.rocky.sorm.core;

import com.rocky.po.Emp;
import com.rocky.sorm.bean.ColumnInfo;
import com.rocky.sorm.bean.TableInfo;
import com.rocky.sorm.utils.JDBCUtils;
import com.rocky.sorm.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlQuery implements Query {

    public static void main(String[] args){
        Emp e = new Emp();
//        e.setId(2);//已设置主键id为自增长，不需要再赋值
        e.setEmpname("rocky");
        e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
        e.setAge(30);
        e.setSalary(3000.8);
//		new MysqlQuery().delete(e);
		new MysqlQuery().insert(e);
    }

    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        int count = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtils.handleParams(ps, params);
            count  = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(ps, conn);
        }
        return count;
    }

    public void insert(Object obj) {
        //obj-->表中。             insert into 表名  (id,uname,pwd) values (?,?,?)
        Class c = obj.getClass();
        //存储sql的参数对象
        List<Object> params = new ArrayList<Object>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);
        StringBuilder sql  = new StringBuilder("insert into "+tableInfo.getTname()+" (");

        //计算不为null的属性值
        int countNotNullField = 0;
        Field[] fs = c.getDeclaredFields();
        for(Field f:fs){
            String fieldName = f.getName();
            Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);

            if(fieldValue!=null){
                countNotNullField++;
                sql.append(fieldName+",");
                params.add(fieldValue);
            }
        }

        sql.setCharAt(sql.length()-1, ')');
        sql.append(" values (");
        for(int i=0;i<countNotNullField;i++){
            sql.append("?,");
        }
        sql.setCharAt(sql.length()-1, ')');

        executeDML(sql.toString(), params.toArray());
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
