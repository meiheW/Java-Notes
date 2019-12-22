package com.tomster.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author meihewang
 * @date 2019/12/10  23:26
 */
@SpringBootApplication
@MapperScan("com.tomster.springboot.mapper")
public class BootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootDemoApplication.class, args);
    }

}
