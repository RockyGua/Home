package com.rocky.sorm.core;

import com.rocky.po.Emp;

public class Test {

    public static void main(String[] args) {
        Query query = QueryFactory.createQuery();
        Object o = query.queryUniqueRow("select * from emp where id=?", Emp.class, new Object[]{1});
        System.out.println(o);
        Number obj = (Number)query.queryValue("select count(*) from emp where salary>?",new Object[]{1000});
        System.out.println(obj.doubleValue());
    }

}
