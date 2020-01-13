package com.tomster.client;

import com.tomster.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author meihewang
 * @date 2020/01/12  18:13
 */

@FeignClient(value = "user-service", fallback = UserClientFallBack.class)
public interface UserClient {

    @GetMapping("/springcloud/getUser/{id}")
    User getUser(@PathVariable("id") int id);
}
