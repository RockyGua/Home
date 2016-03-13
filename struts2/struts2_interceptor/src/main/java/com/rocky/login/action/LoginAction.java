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
     * �˷�����Ϊ����֤ȫ�ֵ�������������
     */
    public String list() {
        return Action.SUCCESS;
    }

    /**
     * �˷�����Ϊ����֤����������������
     */
    public String update() {
        return Action.SUCCESS;
    }

    /**
     * �˷�����Ϊ����֤����������������
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
