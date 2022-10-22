package com.bee.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component

/**
 * 声明一个 OpenFeign 客户端
 * value = 调用服务生产者名字 UI 界面中的 Application
 */
@FeignClient(value = "cloud-payment-provider")
public interface PaymentFeignService {

    @GetMapping("/payment/timeout")
    String timeout();


    @GetMapping("/payment/index")
     String index();
}

