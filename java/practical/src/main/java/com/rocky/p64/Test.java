package com.rocky.p64;

public class Test {
    public static void main(String[] args) {

        //jdk1.5֮���Զ�װ�䣬�����������ǸĽ�����
        Integer a = 1000;
        //�Զ����䣬 �������Ľ���new Integer(1200).intValue();
        int c = new Integer(1200);

        //******************************************************************

        Integer d = 1234;
        Integer d2 = 1234;

        System.out.println(d == d2);//false
        System.out.println(d.equals(d2));//true

        //********************************************************

        Integer e1 = -128; //[-128,127]֮�������Ȼ����������������������
        Integer e2 = -128;
        System.out.println(e1 == e2);//true

        Integer e3 = -129;
        Integer e4 = -129;
        System.out.println(e3 == e4);//false




    }
}
