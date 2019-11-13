package com.tomster.jdbctemp.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author meihewang
 * @date 2019/11/13  22:40
 */
public class UserSupportDao extends JdbcDaoSupport{

    public void addUser() {
        String sql = "insert into t_user(username,password) values('lily','111');";
        getJdbcTemplate().update(sql);
    }

}
