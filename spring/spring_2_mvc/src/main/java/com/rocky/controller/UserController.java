package com.rocky.controller;

import com.rocky.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    public ModelAndView list(Model model) {
        User user = new User();
        user.setUserName("uName");
        user.setPassword("uPwd");
        model.addAttribute(user);
        return new ModelAndView("/WEB-INF/views/user.jsp");
    }

//    http://localhost:8080/user/name?uName=name
    @RequestMapping("/name")
    public ModelAndView detail(String uName, Model model) {
        User user = new User();
        user.setUserName(uName);
        user.setPassword("uPwd");
        model.addAttribute(user);
        return new ModelAndView("/WEB-INF/views/user.jsp");
    }

    @RequestMapping("/add")
    public String toAdd(User user) {
        return "/WEB-INF/views/add.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(User user, Model model) {
        model.addAttribute(user);
        return new ModelAndView("/WEB-INF/views/user.jsp");
    }
}
