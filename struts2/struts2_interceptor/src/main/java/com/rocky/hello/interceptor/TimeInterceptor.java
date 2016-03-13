package com.rocky.hello.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TimeInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        long start = System.currentTimeMillis();
        String result = invocation.invoke();
        long end = System.currentTimeMillis();
        System.out.println("该Action执行所用时间为：" + (end - start) + "ms");
        return result;
    }
}
