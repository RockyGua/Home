package com.rocky.sorm.core;

public interface TypeConverter {

    /**
     * 将数据库数据类型转换为java数据类型
     * @param columnType 数据库字段类型
     * @return java数据类型
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将java数据类型转换为数据库数据类型
     * @param javaType java数据类型
     * @return 数据库类型
     */
    public String javaType2DatabaseType(String javaType);
}
