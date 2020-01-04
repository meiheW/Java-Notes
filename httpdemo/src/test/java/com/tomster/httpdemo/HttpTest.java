package com.tomster.httpdemo;

import com.tomster.httpdemo.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author meihewang
 * @date 2020/01/04  16:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HttpdemoApplication.class)
public class HttpTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testHttp() {
        User user = this.restTemplate.getForObject("http://localhost:8080/springboot/getUser", User.class);
        System.out.println(user);
    }

}
