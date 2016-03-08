package com.rocky.action;

import com.opensymphony.xwork2.Action;
import com.rocky.entity.Authorization;

public class LoginAction {

    private Authorization auth;

    public String login()
    {
        return Action.SUCCESS;
    }

    public String authorize()
    {
        if (auth.getUserName().equals("admin")
                && auth.getPassword().equals("123456"))
        {
            return Action.SUCCESS;
        }
        return Action.ERROR;
    }

    public Authorization getAuth() {
        return auth;
    }

    public void setAuth(Authorization auth) {
        this.auth = auth;
    }
}
