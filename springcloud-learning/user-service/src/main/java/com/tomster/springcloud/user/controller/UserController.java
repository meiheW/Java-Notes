package com.tomster.springcloud.user.controller;

import com.tomster.springcloud.user.po.User;
import com.tomster.springcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author meihewang
 * @date 2020/01/05  15:35
 */
@RestController
@RequestMapping("/springcloud")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/getUser/{i}")
    public User getUser(@PathVariable int i){
        return userService.queryById(i);
    }
}
