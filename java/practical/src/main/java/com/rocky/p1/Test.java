package com.rocky.p1;

public class Test {

    /**
     * ���������Ҫ���ڲ���this,super��ָ���ַ
     *
     * @param args
     */
    public static void main(String[] args) {
        HttpServlet servlet = new MyServlet();
        servlet.service();
    }
}
