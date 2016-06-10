package com.rocky.p1;

public class HttpServlet {

    public void service() {
        System.out.println("This is HttpServlet.service()." + "----this:" + this + "--super:" + super.hashCode());
        doGet();
    }

    public void doGet() {
        System.out.println("This is HttpServlet.doGet()." + "----this:" + this + "--super:" + super.hashCode());
    }
}
