package com.rocky.login.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class MethodInterceptor extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        System.out.println("method interceptor is invoking.");
        return invocation.invoke();
    }
}
