package com.rocky.hello.action;

import com.opensymphony.xwork2.Action;

public class HelloAction {

    /*
    * http://localhost:8080/hello.action?name=rocky
    * �����Ϸ�ʽ���ʣ��������������Ĭ�ϵ�������ջ��name�ĸ�ֵ��ʧ�ܵġ�
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
