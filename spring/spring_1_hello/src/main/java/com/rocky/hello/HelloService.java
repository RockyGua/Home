package com.rocky.hello;

import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloService {

    public String sayHello(String param) {
        return "hello" + param;
    }
}
