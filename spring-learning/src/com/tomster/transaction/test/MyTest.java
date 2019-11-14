package com.tomster.transaction.test;

import com.tomster.transaction.service.AccountAnnoService;
import com.tomster.transaction.service.AccountService;
import com.tomster.transaction.service.AccountTxService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author meihewang
 * @date 2019/11/15  0:09
 */
public class MyTest {



    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-tx.xml");
        AccountService accountService = (AccountService)ac.getBean("accountService");
        accountService.transfer("tom", "jerry", 1000);
    }

    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-tx-advice.xml");
        AccountTxService accountService = (AccountTxService)ac.getBean("accountService");
        accountService.transfer("tom", "jerry", 1000);
    }

    @Test
    public void test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-tx-anno.xml");
        AccountAnnoService accountService = (AccountAnnoService)ac.getBean("accountService");
        accountService.transfer("tom", "jerry", 1000);
    }
}
