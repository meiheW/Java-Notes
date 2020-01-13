package com.tomster.client;

import com.tomster.po.User;
import org.springframework.stereotype.Component;

/**
 * @author meihewang
 * @date 2020/01/12  20:03
 */
@Component
public class UserClientFallBack implements UserClient {
    @Override
    public User getUser(int id) {
        User user = new User();
        user.setName("USER UNKNOWN");
        return user;
    }
}
