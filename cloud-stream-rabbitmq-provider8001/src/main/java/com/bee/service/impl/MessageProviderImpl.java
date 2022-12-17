package com.bee.service.impl;

import com.bee.common.MyMessage;
import com.bee.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 用于连接 rabbitmq 或者 kafka
     */
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public String send(String msg) {
        MyMessage myMessage = new MyMessage();
        myMessage.setPayload(msg);
// 生产消息
// 第一个参数是绑定名称，格式为：自定义的绑定名称-out-0，myBroadcast是自定义的绑定名称，out代表生产者，0是固定写法
        streamBridge.send("myBroadcast-out-0", myMessage);

        return "SUCCESS";
    }

    @Override
    public String groupSend(String msg) {
        MyMessage myMessage = new MyMessage();
        myMessage.setPayload(msg);
// 生产消息
// 第一个参数是绑定名称，格式为：自定义的绑定名称-out-0，myBroadcast是自定义的绑定名称，out代表生产者，0是固定写法
        streamBridge.send("myGroup-out-0", myMessage);

        return "SUCCESS";
    }
}
