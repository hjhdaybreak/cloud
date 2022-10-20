package com.bee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return "payment  success";
    }
}
