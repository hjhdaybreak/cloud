package com.bee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class ProviderMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8001.class, args);
        log.info("************ProviderMain8001启动成功**************");
    }
}
