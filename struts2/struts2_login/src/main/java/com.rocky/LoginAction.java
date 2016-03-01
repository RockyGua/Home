package com.rocky;

public class LoginAction {
    private String userName;
    private String pwd;

    public String execute() {
        if (userName.equals("admin") && pwd.equals("123456"))
        {
            return "success";
        }
        return "failed";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
