package com.rocky.sorm.utils;

import java.lang.reflect.Method;

public class ReflectUtils {

    /**
     * 调用obj对象对应属性fieldName的get方法
     * @param fieldName
     * @param obj
     * @return
     */
    public static Object invokeGet(String fieldName, Object obj){
        try {
            Class c = obj.getClass();
            Method m = c.getDeclaredMethod("get"+StringUtils.firstChar2UpperCase(fieldName), null);
            return m.invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 调用obj对象对应属性fieldName的set方法
     * @param obj
     * @param columnName
     * @param columnValue
     */
    public static void invokeSet(Object obj,String columnName,Object columnValue){
        try {
            Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
                    columnValue.getClass());
            m.invoke(obj, columnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
