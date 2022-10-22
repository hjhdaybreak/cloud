package com.bee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    /**
     * 测试服务调用
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        /* 测试慢调用熔断降级时打开
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return "payment  success";
    }

    /**
     * 测试超时机制
     *
     * @return
     */
    @GetMapping("timeout")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "payment success";
    }


}
