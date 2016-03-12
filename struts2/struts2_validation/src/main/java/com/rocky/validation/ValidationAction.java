package com.rocky.validation;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction extends ActionSupport {

    private String name;
    private String password;

    @Override
    public String execute() throws Exception {
        System.out.println("method execute.");
        return Action.SUCCESS;
    }

    public String login() {
        System.out.println("method login.");
        return Action.SUCCESS;
    }

    @Override
    public void validate() {
        System.out.println("run before the method 'execute' is invoked");
    }

    //以validation***命名的方法将会对***方法进行针对性校验过滤
    public void validateLogin() {
        System.out.println("run before the method 'login' is invoked");
        if (!name.equals("admin"))
        {
            //<s:actionerror/>JSP相应的应该加上此标签才能显示错误信息
            this.addActionError("name is invalid");
        }
        if (!password.equals("123456"))
        {
            this.addActionError("password is invalid");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
