package com.tomster.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

/**
 * @author meihewang
 * @date 2019/12/10  23:30
 */
@Controller
@ResponseBody
@RequestMapping("/springboot")
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
