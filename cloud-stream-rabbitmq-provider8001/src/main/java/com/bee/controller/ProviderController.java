package com.bee.controller;

import com.bee.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired
    private IMessageProvider iMessageProvider;

    @GetMapping("send")
    public String send(String msg) {
        return iMessageProvider.send(msg);
    }

    @GetMapping("groupSend")
    public String groupSend(String msg) {
        return iMessageProvider.groupSend(msg);
    }
}
