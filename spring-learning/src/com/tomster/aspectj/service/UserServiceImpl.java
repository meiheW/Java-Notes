package com.tomster.aspectj.service;

import org.springframework.stereotype.Service;

/**
 * @author meihewang
 * @date 2019/11/05  23:50
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Override
    public void addUser() {
        System.out.println("add user");
    }

    @Override
    public void updateUser() {
        System.out.println("update user");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete user");
    }
}
