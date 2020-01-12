package com.tomster.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tomster.client.UserClient;
import com.tomster.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author meihewang
 * @date 2020/01/05  21:56
 */
@RestController
@RequestMapping("/user/consumer")
//@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserClient userClient;

    @RequestMapping("/default")
    public User getUser() {
        return restTemplate.getForObject("http://localhost:8081/springcloud/getUser/" + 1, User.class);
    }

    /**
     * 动态构造调用链接
     * @param id
     * @return
     */
    @RequestMapping("/getUser/{id}")
    @HystrixCommand(fallbackMethod = "queryUserByIdFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="800")
    })
    public User getUser(@PathVariable("id") int id){
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance instance = instances.get(0);
        long begin = System.currentTimeMillis();
        User user = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/springcloud/getUser/" + id, User.class);
        long end = System.currentTimeMillis();
        logger.info("访问用时：{}", end - begin);
        return user;
    }

    /**
     * fallback
     * 默认超时时间1000ms
     * @param id
     * @return
     */
    public User queryUserByIdFallback(int id){
        User user = new User();
        user.setId(id);
        user.setName("用户信息查询出现异常！");
        return user;
    }

    /**
     * defaultFallback
     * @param id
     * @return
     */
    public User defaultFallback(int id){
        User user = new User();
        user.setId(id);
        user.setName("用户信息查询出现异常！");
        return user;
    }


    /**
     * 测试feign
     * @return
     */
    @RequestMapping("/feign")
    public User getUserByClient() {
        return userClient.getUser(1);
    }
}
