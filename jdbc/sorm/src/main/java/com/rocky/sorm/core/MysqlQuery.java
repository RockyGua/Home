package com.rocky.sorm.core;

public class MysqlQuery extends Query{

    public static void main(String[] args) {
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

        /*
         List<Emp> list = new MysqlQuery().queryRows("select id,empname,age from emp where age>? and salary<?",
         Emp.class, new Object[]{10, 5000});

         for(Emp e:list){
         System.out.println(e.getEmpname());
         }
         */

        /*
        Object o = new MysqlQuery().queryUniqueRow("select * from emp where id=?", Emp.class, new Object[]{1});
        System.out.println(o);
        Number obj = (Number)new MysqlQuery().queryValue("select count(*) from emp where salary>?",new Object[]{1000});
        Number obj = new MysqlQuery().queryNumber("select count(*) from emp where salary>?",new Object[]{1000});
        System.out.println(obj.doubleValue());
        */
    }

}
