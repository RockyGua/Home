package com.rocky.p65;

import java.util.Date;

public class TestDate {

    public static void main(String[] args) {
        Date d = new Date();
        long t = System.currentTimeMillis();
        System.out.println(t);//1465984020263

        Date d2 = new Date(1000);
        System.out.println(d2);//Thu Jan 01 08:00:01 CST 1970
        System.out.println(d2.getTime());//1000
    }
}
