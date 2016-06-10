package com.rocky.p1;

public class Test {

    /**
     * 这个测试主要用于测试this,super的指向地址
     *
     * @param args
     */
    public static void main(String[] args) {
        HttpServlet servlet = new MyServlet();
        servlet.service();
    }
}
