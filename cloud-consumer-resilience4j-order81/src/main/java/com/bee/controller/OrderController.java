package com.bee.controller;

import com.bee.service.PaymentFeignService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
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
        return CompletableFuture.completedFuture("超时啦");
    }

    /**
     * 重试机制
     */
    @GetMapping("retry")
    @Retry(name = "backendA")
    public CompletableFuture<String> retry() {
        log.info("***************进入方法********************");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> paymentFeignService.index());
        log.info("***************离开方法********************");
        return stringCompletableFuture;
    }


    /**
     * 熔断 降级
     *
     * @return
     */
    @GetMapping("/citcuitBackend")
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public String citcuitBackend() {

        log.info("************ 进入方法 ***********");
        String index = paymentFeignService.index();
        log.info("************ 离开方法 ***********");

        return index;
    }

    public String fallback(Throwable e) {
        e.printStackTrace();
        return "客官服务繁忙，稍等一会。。。。";
    }

    /**
     * 慢调用比例熔断降级
     *
     * @return
     */
    @GetMapping("/slowcircuitbackend")
    @CircuitBreaker(name = "backendB", fallbackMethod = "slowfallback")
    public String slowcircuitbackend() {
        log.info("************ 进入方法 ***********");
        String index = paymentFeignService.index();
        log.info("************ 离开方法 ***********");

        return index;
    }

    public String slowfallback(Exception e) {
        e.printStackTrace();
        return "太慢了";
    }

    /**
     * 信号量隔离
     *
     * @return
     */
    @GetMapping("bulkhead")
    @Bulkhead(name = "backendA", type = Bulkhead.Type.SEMAPHORE)
    public String bulkhead() throws InterruptedException {
        log.info("*********进入了方法*********");
        TimeUnit.SECONDS.sleep(10);
        String index = paymentFeignService.index();
        log.info("*********离开了方法*********");
        return index;
    }

    /**
     * 测试线程池服务隔离
     *
     * @return
     */
    @Bulkhead(name = "backendA", type = Bulkhead.Type.THREADPOOL)
    @GetMapping("thread")
    public CompletableFuture<String> future() throws InterruptedException {
        log.info("*********进入了方法*********");
        TimeUnit.SECONDS.sleep(10);
        log.info("*********离开了方法*********");
        return CompletableFuture.supplyAsync(() -> "线程池隔离");
    }

    /**
     * 测试限流
     *
     * @return
     */
    @GetMapping("limiter")
    @RateLimiter(name = "backendA")
    public CompletableFuture<String> limiter() throws InterruptedException {
        log.info("*********进入了方法*********");
        TimeUnit.SECONDS.sleep(5);
        log.info("*********离开了方法*********");
        return CompletableFuture.supplyAsync(() -> "限流");
    }

}
