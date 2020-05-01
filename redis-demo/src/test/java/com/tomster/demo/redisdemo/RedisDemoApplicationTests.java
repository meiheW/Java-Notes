package com.tomster.demo.redisdemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private StringRedisTemplate srt;


    @Test
    void contextLoads() {
        srt.opsForValue().set("key", "value");

        BoundHashOperations<String, Object, Object> user = srt.boundHashOps("user");
        user.put("name", "wmh");
        user.put("age", "21");

    }

}
