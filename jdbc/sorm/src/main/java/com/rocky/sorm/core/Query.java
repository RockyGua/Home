package com.rocky.sorm.core;

import java.util.List;

public interface Query {

    /**
     *直接执行一个DML语句
     * @param sql sql语句
     * @param params 参数
     * @return 操作影响的行数
     */
    public int executeDML(String sql, String[] params);

    /**
     * 讲一个对象存储在数据库中
     * @param obj 存储对象
     */
    public void insert(Object obj);

    /**
     * 删除clazz对应的表中的主键为id的记录
     * @param clazz 跟表对应的class类
     * @param id 主键的值
     */
    public void delete(Class clazz, int id);

    /**
     * 删除对象在数据中对应的记录
     * @param obj 对象所在的类对应到数据库表，对象的主键值对应到记录
     */
    public void delete(Object obj);

    /**
     *  更新对象对应的数据库记录，并且只更新对应字段的值
     * @param obj 所要更新的对象
     * @param fieldNames 要更新的字段
     * @return 影响的行数
     */
    public int update(Object obj, String[] fieldNames);

    /**
     * 查询返回多条记录，并且每条记录封装到对应的clazz类对象中
     * @param sql 执行的sql语句
     * @param clazz 操作的表对应的类
     * @param params sql参数
     * @return 查询到的结果
     */
    public List queryRows(String sql, Class clazz, String[] params);

    /**
     * 查询返回单条记录，并且封装到对应的clazz类对象中
     * @param sql 执行的sql语句
     * @param clazz 操作的表对应的类
     * @param params sql参数
     * @return 查询到的结果
     */
    public Object queryUniqueRow(String sql, Class clazz, String[] params);

    /**
     * 查询返回一个值（一行一列）
     * @param sql 执行的sql语句
     * @param params sql参数
     * @return 查询到的结果
     */
    public Object queryValue(String sql, String[] params);

    /**
     * 查询返回一个数字（一行一列）
     * @param sql 执行的sql语句
     * @param params sql参数
     * @return 查询到的数字
     */
    public Number queryNumber(String sql, String[] params);
}
