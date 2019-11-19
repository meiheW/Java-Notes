package com.tomster.mybatis.mapper;

import com.tomster.mybatis.po.User;
import com.tomster.mybatis.vo.QueryVo;

import java.util.List;

/**
 * @author meihewang
 * @date 2019/11/18  23:20
 */
public interface UserMapper {

    public int insertUser(User user);

    public User findUserById(int id);

    public int countUser(User user);

    public List<User> userList(User user);

    public List<User> userListByIds(QueryVo queryVo);

}
