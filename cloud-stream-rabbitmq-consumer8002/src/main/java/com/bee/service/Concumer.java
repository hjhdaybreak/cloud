package com.bee.service;

import com.bee.common.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
// 消费者
public class Concumer {


    // 消费广播消息
    @Bean
    public Consumer<MyMessage> myBroadcast() { // 方法名必须与生产消息时自定义的绑定名称一致

        return message -> {
            log.info("接收广播消息：{}", message.getPayload());
        };
    }

    // 消费分组消息
    @Bean
    public Consumer<MyMessage> myGroup() { // 方法名必须与生产消息时自定义的绑定名称一致

        return message -> {
            log.info("接收分组消息：{}", message.getPayload());
        };
    }
}

