package com.rocky;

public class HelloAction {

    //默认执行execute方法，public的，无参数，返回值为String
    public String execute() {
        System.out.println("hello struts");
        return "success";
    }
}
