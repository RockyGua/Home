package com.rocky.p3;

public class Outer {

    String type = "Outer Type";
    String flag = "flag";

    class Inner{
        String type = "Inner Type";
        String innerFlag = "inner flag";

        public void action (){
            System.out.println(flag);
            System.out.println(Outer.this.type);
            System.out.println(this.type);
        }
    }
}

/**
 * ����������չʾ�ڲ���Ĵ��������Լ�����ָ��
 * �ڲ�����Է����ⲿ����������ԣ�����˽�����ԣ�
 */
class Test {

    public static void main(String[] args){
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.action();
    }

}