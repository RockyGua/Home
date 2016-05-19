package com.rocky.hello;

import com.rocky.annotation.Hello;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloService {

    public String sayHello(String param) {
        return "hello" + param;
    }

    @Hello
    public String sayHello2Annotation() {
        return "i have been signed by @Hello";
    }
}
