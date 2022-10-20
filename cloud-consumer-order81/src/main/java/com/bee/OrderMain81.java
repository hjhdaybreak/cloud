package com.bee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author daybreak
 * 服务消费者
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j

public class OrderMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain81.class, args);
        log.info("*************** 订单服务消费者启动成功 *************");
    }
}
