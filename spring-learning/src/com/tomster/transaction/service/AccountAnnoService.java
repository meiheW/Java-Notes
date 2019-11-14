package com.tomster.transaction.service;

import com.tomster.transaction.dao.AccountDao;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author meihewang
 * @date 2019/11/15  1:44
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AccountAnnoService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String outer, String inner, int num){
        //step 1
        accountDao.out(outer, num);
        int i = 1/0;
        //step 2
        accountDao.in(inner, num);

    }
}
