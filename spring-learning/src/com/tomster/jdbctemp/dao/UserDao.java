package com.tomster.jdbctemp.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author meihewang
 * @date 2019/11/13  22:40
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser() {
        String sql = "insert into t_user(username,password) values('cindy','456');";
        jdbcTemplate.update(sql);
    }
}
