package com.tomster.springboot.service;

import com.tomster.springboot.mapper.UserMapper;
import com.tomster.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author meihewang
 * @date 2019/12/22  23:27
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Integer id){
        return this.userMapper.queryUserById(id);
    }

}
