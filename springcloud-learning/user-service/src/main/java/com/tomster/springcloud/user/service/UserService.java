package com.tomster.springcloud.user.service;

import com.tomster.springcloud.user.mapper.UserMapper;
import com.tomster.springcloud.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author meihewang
 * @date 2020/01/05  15:34
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Integer id){
        return this.userMapper.selectByPrimaryKey(id);
    }


}
