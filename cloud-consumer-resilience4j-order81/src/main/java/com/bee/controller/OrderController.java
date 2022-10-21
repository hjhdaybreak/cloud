package com.bee.controller;

import com.bee.service.PaymentFeignService;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * 订单控制层
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private PaymentFeignService paymentFeignService;

    /**
     * 测试超时降级
     *
     * @return
     */
    @GetMapping("timeout")
    @TimeLimiter(name = "delay", fallbackMethod = "timeOutFallback")
    public CompletableFuture<String> timeout() {
        log.info("******请求进入了方法******");
        //必须是异步操作
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> (paymentFeignService.timeout()));
        log.info("********* 离开方法 ******");
        return stringCompletableFuture;
    }

    /**
     * 超时降级方法
     *
     * @param e
     * @return
     */
    private CompletableFuture<String> timeOutFallback(Exception e) {
        e.printStackTrace();
        return CompletableFuture.completedFuture("超时了");
    }

}
