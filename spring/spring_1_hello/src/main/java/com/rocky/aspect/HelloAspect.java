package com.rocky.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect @Component
public class HelloAspect {

    @Before("execution(* com.rocky.hello.HelloService.sayHello(..))")
    public void cutSayHello() {
        System.out.println("cut say hello");
    }

    /**
     * 通过自定义的标签标记需要拦截的方法
     */
    @Before("@annotation(com.rocky.annotation.Hello)")
    public void cutAnnotationSayHello() {
        System.out.println("cut annotation say hello");
    }
}


