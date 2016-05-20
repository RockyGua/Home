package com.rocky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/welcome")
    public String hello() {
        return "/WEB-INF/views/hello.jsp";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/show")
    public ModelAndView helloMessage(Model model) {
        model.addAttribute("message","world");
        return new ModelAndView("/WEB-INF/views/hello.jsp");
    }
}
