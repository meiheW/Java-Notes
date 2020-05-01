package com.tomster.demo.redisdemo;

/**
 * @author meihewang
 * @date 2020/05/01  11:22
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String getName(){
        String name = (String)redisTemplate.opsForValue().get("name");
        String cname = (String)redisTemplate.opsForHash().get("ctrip", "mkt");
        System.out.println(name);
        return cname;
    }

}
