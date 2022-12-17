package com.bee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class ConsumerMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain8002.class,args);
        log.info("***********ConsumerMain8002启动成功***********");
    }
}
