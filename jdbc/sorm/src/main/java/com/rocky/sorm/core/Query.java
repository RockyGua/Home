package com.rocky.sorm.core;

import com.rocky.sorm.bean.ColumnInfo;
import com.rocky.sorm.bean.TableInfo;
import com.rocky.sorm.utils.JDBCUtils;
import com.rocky.sorm.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Query implements Cloneable{

    /**
     * 采用模板方法模式将JDBC操作封装成模板，便于重用
     * @param sql sql语句
     * @param params sql的参数
     * @param clazz 记录要封装到的java类
     * @param back CallBack的实现类，实现回调
     * @return 查询结果
     */
    public Object executeQueryTemplate(String sql,Object[] params,Class clazz,Callback back){
        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //给sql设参
            JDBCUtils.handleParams(ps, params);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            return back.doExecute(conn,ps,rs);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBManager.close(ps, conn);
        }
    }

    /**
     *直接执行一个DML语句
     * @param sql sql语句
     * @param params 参数
     * @return 操作影响的行数
     */
    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        int count = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtils.handleParams(ps, params);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(ps, conn);
        }
        return count;
    }

    /**
     * 讲一个对象存储在数据库中
     * @param obj 存储对象
     */
    public void insert(Object obj) {
        //obj-->表中。insert into 表名  (id,uname,pwd) values (?,?,?)
        Class c = obj.getClass();
        //存储sql的参数对象
        List<Object> params = new ArrayList<Object>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);
        StringBuilder sql = new StringBuilder("insert into " + tableInfo.getTname() + " (");

        //计算不为null的属性值
        int countNotNullField = 0;
        Field[] fs = c.getDeclaredFields();
        for (Field f : fs) {
            String fieldName = f.getName();
            Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);

            if (fieldValue != null) {
                countNotNullField++;
                sql.append(fieldName + ",");
                params.add(fieldValue);
            }
        }

        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values (");
        for (int i = 0; i < countNotNullField; i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');

        executeDML(sql.toString(), params.toArray());
    }

    /**
     * 删除clazz对应的表中的主键为id的记录
     * @param clazz 跟表对应的class类
     * @param id 主键的值
     */
    public void delete(Class clazz, Object id) {
        //delete from emp where id = ?
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo columnInfo = tableInfo.getOnlyPriKey();

        String sql = "delete from " + tableInfo.getTname() + " where " + columnInfo.getName() + "=?";
        executeDML(sql, new Object[]{id});
    }

    /**
     * 删除对象在数据中对应的记录
     * @param obj 对象所在的类对应到数据库表，对象的主键值对应到记录
     */
    public void delete(Object obj) {
        Class clazz = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo columnInfo = tableInfo.getOnlyPriKey();

        //通过反射机制，调用属性对应的get方法或set方法
        Object param = ReflectUtils.invokeGet(columnInfo.getName(), obj);
        delete(clazz, param);
    }

    /**
     *  更新对象对应的数据库记录，并且只更新对应字段的值
     * @param obj 所要更新的对象
     * @param fieldNames 要更新的字段
     * @return 影响的行数
     */
    public int update(Object obj, String[] fieldNames) {
        //obj{"empname","age"}-->update emp  set empname=?,age=? where id=?
        Class c = obj.getClass();
        List<Object> params = new ArrayList<Object>();   //存储sql的参数对象
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);
        ColumnInfo priKey = tableInfo.getOnlyPriKey();   //获得唯一的主键
        StringBuilder sql = new StringBuilder("update " + tableInfo.getTname() + " set ");

        for (String fname : fieldNames) {
            Object fvalue = ReflectUtils.invokeGet(fname, obj);
            params.add(fvalue);
            sql.append(fname + "=?,");
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append(" where ");
        sql.append(priKey.getName() + "=? ");

        params.add(ReflectUtils.invokeGet(priKey.getName(), obj));    //主键的值

        return executeDML(sql.toString(), params.toArray());
    }

    /**
     * 查询返回多条记录，并且每条记录封装到对应的clazz类对象中
     * @param sql 执行的sql语句
     * @param clazz 操作的表对应的类
     * @param params sql参数
     * @return 查询到的结果
     */
    public List queryRows(String sql, final Class clazz, Object[] params) {

           return (List)executeQueryTemplate(sql, params, clazz, new Callback() {

                public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs) {
                    List list = null;
                    try {
                        ResultSetMetaData metaData = rs.getMetaData();

                        //多行
                        while (rs.next()) {
                            if (list == null) {
                                list = new ArrayList();
                            }
                            Object rowObj = clazz.newInstance();   //调用javabean的无参构造器

                            //多列       select username ,pwd,age from user where id>? and age>18
                            for (int i = 0; i < metaData.getColumnCount(); i++) {
                                String columnName = metaData.getColumnLabel(i + 1);  //username
                                Object columnValue = rs.getObject(i + 1);

                                //调用rowObj对象的setUsername(String uname)方法，将columnValue的值设置进去
                                ReflectUtils.invokeSet(rowObj, columnName, columnValue);
                            }

                            list.add(rowObj);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return list;
                }
            });
    }

    /**
     * 查询返回单条记录，并且封装到对应的clazz类对象中
     * @param sql 执行的sql语句
     * @param clazz 操作的表对应的类
     * @param params sql参数
     * @return 查询到的结果
     */
    public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);
        return (list == null && list.size() <= 0) ? null : list.get(0);
    }

    /**
     * 查询返回一个值（一行一列）
     * @param sql 执行的sql语句
     * @param params sql参数
     * @return 查询到的结果
     */
    public Object queryValue(String sql, Object[] params) {

        return executeQueryTemplate(sql, params, null, new Callback() {
            public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs) {
                Object value = null;
                try {
                    while (rs.next()) {
                        value = rs.getObject(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
    }

    /**
     * 查询返回一个数字（一行一列）
     * @param sql 执行的sql语句
     * @param params sql参数
     * @return 查询到的数字
     */
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
