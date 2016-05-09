package com.rocky.sorm.core;

import com.rocky.po.Emp;
import com.rocky.sorm.bean.ColumnInfo;
import com.rocky.sorm.bean.TableInfo;
import com.rocky.sorm.utils.JDBCUtils;
import com.rocky.sorm.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlQuery implements Query {

    public static void main(String[] args){
        /**
            Emp e = new Emp();
            //已设置主键id为自增长，insert操作不需要再赋值
            e.setId(1);
            e.setEmpname("rocky");
            e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
            e.setAge(31);
            e.setSalary(5000.55);
    //		new MysqlQuery().delete(e);
    //		new MysqlQuery().insert(e);
            new MysqlQuery().update(e, new String[]{"empname", "age", "salary"});
         */

        List<Emp> list = new MysqlQuery().queryRows("select id,empname,age from emp where age>? and salary<?",
                Emp.class, new Object[]{10, 5000});

        for(Emp e:list){
            System.out.println(e.getEmpname());
        }

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

    /**
     * @param obj 所要更新的对象
     * @param fieldNames 要更新的字段
     * @return 受影响的数量
     */
    public int update(Object obj, String[] fieldNames) {
        //obj{"empname","age"}-->update emp  set empname=?,age=? where id=?
        Class c = obj.getClass();
        List<Object> params = new ArrayList<Object>();   //存储sql的参数对象
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);
        ColumnInfo  priKey = tableInfo.getOnlyPriKey();   //获得唯一的主键
        StringBuilder sql  = new StringBuilder("update "+tableInfo.getTname()+" set ");

        for(String fname:fieldNames){
            Object fvalue = ReflectUtils.invokeGet(fname,obj);
            params.add(fvalue);
            sql.append(fname+"=?,");
        }
        sql.setCharAt(sql.length()-1, ' ');
        sql.append(" where ");
        sql.append(priKey.getName()+"=? ");

        params.add(ReflectUtils.invokeGet(priKey.getName(), obj));    //主键的值

        return executeDML(sql.toString(), params.toArray());
    }

    public List queryRows(String sql, Class clazz, Object[] params) {
        Connection conn = DBManager.getConnection();
        List list = null;    //存储查询结果的容器
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            //给sql设参
            JDBCUtils.handleParams(ps, params);
            System.out.println(ps);
            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            //多行
            while(rs.next()){
                if(list==null){
                    list = new ArrayList();
                }
                Object rowObj = clazz.newInstance();   //调用javabean的无参构造器

                //多列       select username ,pwd,age from user where id>? and age>18
                for(int i=0;i<metaData.getColumnCount();i++){
                    String columnName = metaData.getColumnLabel(i+1);  //username
                    Object columnValue = rs.getObject(i+1);

                    //调用rowObj对象的setUsername(String uname)方法，将columnValue的值设置进去
                    ReflectUtils.invokeSet(rowObj, columnName, columnValue);
                }

                list.add(rowObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBManager.close(ps, conn);
        }

        return list;
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
