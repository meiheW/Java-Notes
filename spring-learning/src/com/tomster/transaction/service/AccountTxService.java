package com.tomster.transaction.service;

import com.tomster.transaction.dao.AccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author meihewang
 * @date 2019/11/15  1:26
 */
public class AccountTxService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String outer, String inner, int num){
        //step 1
        accountDao.out(outer, num);
        //int i = 1/0;
        //step 2
        accountDao.in(inner, num);

    }
}
