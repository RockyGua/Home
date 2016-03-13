package com.rocky.login.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {

    private String name;
    private String password;

    public String execute() {
        if (name.equals("admin") && password.equals("123456"))
        {
            ActionContext.getContext().getSession().put("user", name);
            return Action.SUCCESS;
        }

        return Action.LOGIN;
    }

    /**
     * 此方法是为了验证全局的拦截器起到作用
     */
    public String list() {
        return Action.SUCCESS;
    }

    /**
     * 此方法是为了验证方法拦截器起到作用
     */
    public String update() {
        return Action.SUCCESS;
    }

    /**
     * 此方法是为了验证方法拦截器起到作用
     */
    public String delete() {
        return Action.SUCCESS;
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
