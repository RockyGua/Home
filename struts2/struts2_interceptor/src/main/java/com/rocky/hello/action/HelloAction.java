package com.rocky.hello.action;

import com.opensymphony.xwork2.Action;

public class HelloAction {

    /*
    * http://localhost:8080/hello.action?name=rocky
    * 用以上方式访问，发现如果不引入默认的拦截器栈，name的赋值是失败的。
    * */
    private String name;

    public String execute() {
        System.out.println("action execute...");
        return Action.SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
