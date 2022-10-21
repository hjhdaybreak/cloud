package com.bee.controller;

import com.bee.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制层
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private PaymentFeignService paymentFeignService;


    /**
     * 测试 OpenFeign 接口调用
     *
     * @return
     */

    @GetMapping("/index")
    public String get() {
        return paymentFeignService.index();
    }

    /**
     * 测试超时机制
     * @return
     */
    @GetMapping("timeout")
    public String timeout() {
        return paymentFeignService.timeout();
    }

}
