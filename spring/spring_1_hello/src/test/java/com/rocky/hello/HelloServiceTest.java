package com.rocky.hello;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HelloServiceTest {

    @Resource
    private HelloService helloService;

    @Test
    public void testHello() {
        String str = helloService.sayHello("world");
        assertNotNull(str);
        System.out.print(str);
    }

    @Test
    public void testAnnotationHello() {
        String str = helloService.sayHello2Annotation();
        assertNotNull(str);
        System.out.print(str);
    }
}
