package com.tomster.springboot.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author meihewang
 * @date 2019/12/14  21:56
 */
@ConfigurationProperties
@Data
public class JdbcProperties {
    String driverClassName;
    String url;
    String username;
    String password;
}
