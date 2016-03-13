package com.rocky.login.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("action interceptor is invoking.");
        String actionName = invocation.getProxy().getActionName();
        if (actionName.equals("login"))
        {
           return invocation.invoke();
        }
        Object user = invocation.getInvocationContext().getSession().get("user");
        if (null == user)
        {
            return Action.LOGIN;
        }
        return invocation.invoke();
    }
}
