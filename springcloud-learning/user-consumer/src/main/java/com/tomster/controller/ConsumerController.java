package com.tomster.controller;

import com.tomster.po.User;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author meihewang
 * @date 2020/01/05  21:56
 */
@RestController
@RequestMapping("/user/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping()
    public User getUser() {
        return restTemplate.getForObject("http://localhost:8081/springcloud/getUser/" + 1, User.class);
    }

    /**
     * 动态构造调用链接
     * @param id
     * @return
     */
    @RequestMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") Long id){
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance instance = instances.get(0);
        return restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/springcloud/getUser/" + id, User.class);
    }

}
