package com.rocky.p1;

public class MyServlet extends HttpServlet {

    @Override
    public void doGet() {
        System.out.println("This is MyServlet.Hashcode:" + "----" + this.hashCode() + "--16���ƣ�" + Integer.toHexString(this.hashCode()));
        System.out.println("This is MyServlet.doGet()." + "----" + this + "--super:" + super.hashCode());
    }
}
