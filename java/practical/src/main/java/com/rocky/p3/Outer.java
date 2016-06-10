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
 * 此事例用于展示内部类的创建方法以及属性指向
 * 内部类可以访问外部类的所有属性（包含私有属性）
 */
class Test {

    public static void main(String[] args){
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.action();
    }

}