package com.bee;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//开启服务调用
@EnableFeignClients
@SpringBootApplication
@Slf4j
public class OrderFeignMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain81.class, args);
        log.info("************** OrderFeignMain81 服务启动成功  **********");
    }
}
