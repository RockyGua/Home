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
}
