package com.tomster.di.service;

import com.tomster.di.model.User;
import org.springframework.stereotype.Component;

/**
 * @author meihewang
 * @date 2019/11/04  23:10
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("add ...");
    }

    @Override
    public void add(User user) {
        System.out.println("add user ...");
    }
}
