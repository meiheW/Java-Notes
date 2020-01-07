package com.tomster.springcloud.user.mapper;

import com.tomster.springcloud.user.po.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author meihewang
 * @date 2020/01/05  15:32
 */
public interface UserMapper {

    User selectByPrimaryKey(int id);
}
