package com.bee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class UserMain6500 {
    public static void main(String[] args) {
        SpringApplication.run(UserMain6500.class, args);
        log.info("****************UserMain6500启动成功***************");
    }
}
