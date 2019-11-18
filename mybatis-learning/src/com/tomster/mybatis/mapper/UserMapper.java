package com.tomster.mybatis.mapper;

import com.tomster.mybatis.po.User;

/**
 * @author meihewang
 * @date 2019/11/18  23:20
 */
public interface UserMapper {

    public int insertUser(User user);

    public User findUserById(int id);

}
