package com.tomster.transaction.service;

import com.tomster.transaction.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author meihewang
 * @date 2019/11/15  0:15
 */
//@Service("accountService")
public class AccountService {

    //@Autowired
    private AccountDao accountDao;

    private TransactionTemplate transactionTemplate;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void transfer(String outer, String inner, int num){

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                //step 1
                accountDao.out(outer, num);

                //int i = 1/0;
                //step 2
                accountDao.in(inner, num);
            }
        });
    }
}
