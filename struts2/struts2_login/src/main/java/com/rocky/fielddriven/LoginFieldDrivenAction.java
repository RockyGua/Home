package com.rocky.fielddriven;

import com.opensymphony.xwork2.Action;

public class LoginFieldDrivenAction {
    private User user;

    public String signIn() {
        if (user.getName().equals("admin") && user.getPwd().equals("123456"))
        {
            return Action.SUCCESS;
        }
        return Action.ERROR;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
