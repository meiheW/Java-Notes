package com.tomster.backoffice.mapper;

import com.tomster.backoffice.po.User;

/**
 * @author meihewang
 * @date 2019/11/30  23:16
 */
public interface UserMapper {

    public User findUserById(int id);

}
