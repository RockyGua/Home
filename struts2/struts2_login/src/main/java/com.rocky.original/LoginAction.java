package com.rocky.original;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;

public class LoginAction {
    private String userName;
    private String pwd;

    public String execute() {
        if (userName.equals("admin") && pwd.equals("123456"))
        {
            HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.getSession().setAttribute("user", userName);
            //第二种获取request的方式：request = (HttpServletRequest)ServletActionContext.getRequest();
            //第三种获取方式：通过implements ServletRequestAware
            //      private HttpServletRequest request;
            //      @override
            //      public void setServletRequest(HttpServletRequest request) { this.request = request;}

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
