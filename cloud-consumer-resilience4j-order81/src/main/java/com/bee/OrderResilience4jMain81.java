package com.bee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@Slf4j
public class OrderResilience4jMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderResilience4jMain81.class, args);
        log.info("*************OrderResilience4jMain81 启动成功***************");
    }
}
