package com.tomster.transaction.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author meihewang
 * @date 2019/11/15  0:03
 */

public class AccountDao extends JdbcDaoSupport{


    public void out(String outer, Integer num){
        String sql = "update account set balance = balance-? where user = ?";
        getJdbcTemplate().update(sql, num, outer);
    }

    public void in(String inner, Integer num){
        String sql = "update account set balance = balance+? where user = ?";
        getJdbcTemplate().update(sql, num, inner);
    }
}
