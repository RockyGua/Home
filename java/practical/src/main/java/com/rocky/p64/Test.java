package com.rocky.p64;

public class Test {
    public static void main(String[] args) {

        //jdk1.5之后，自动装箱，编译器帮我们改进代码
        Integer a = 1000;
        //自动拆箱， 编译器改进：new Integer(1200).intValue();
        int c = new Integer(1200);

        //******************************************************************

        Integer d = 1234;
        Integer d2 = 1234;

        System.out.println(d == d2);//false
        System.out.println(d.equals(d2));//true

        //********************************************************

        Integer e1 = -128; //[-128,127]之间的数仍然当做基本数据类型来处理
        Integer e2 = -128;
        System.out.println(e1 == e2);//true

        Integer e3 = -129;
        Integer e4 = -129;
        System.out.println(e3 == e4);//false




    }
}
