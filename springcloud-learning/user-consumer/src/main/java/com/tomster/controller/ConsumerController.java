package com.tomster.controller;

import com.tomster.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author meihewang
 * @date 2020/01/05  21:56
 */
@RestController
@RequestMapping("/user/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getUser")
    public User getUser(){
        User user = restTemplate.getForObject("http://localhost:8081/springcloud/getUser", User.class);
        return user;
    }
}
