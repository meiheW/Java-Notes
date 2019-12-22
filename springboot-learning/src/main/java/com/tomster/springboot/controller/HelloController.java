package com.tomster.springboot.controller;

import com.tomster.springboot.pojo.User;
import com.tomster.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author meihewang
 * @date 2019/12/10  23:30
 */
@Controller
@ResponseBody
@RequestMapping("/springboot")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/getUser")
    public User getUser(){
        return userService.queryById(1);
    }

}
