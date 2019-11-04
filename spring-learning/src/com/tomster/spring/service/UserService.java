package com.tomster.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author meihewang
 * @date 2019/10/31  0:17
 */
public class UserService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set: " + name);
        this.name = name;
    }

    public void addUser() {
        System.out.println("创建用户: " + name);
    }
}
