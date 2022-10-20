package com.bee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    //服务发现接口
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 获取所有服务列表清单
     *
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("server:={}", service);
        }
        return this.discoveryClient;
    }


    /**
     * 测试服务调用
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        String hostName = "cloud-payment-provider";
        String url = "/payment/index";

        List<ServiceInstance> instances = discoveryClient.getInstances(hostName);
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getInstanceId());
        System.out.println(serviceInstance.getUri());
        System.out.println(serviceInstance.getPort());
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getServiceId());

        String res = restTemplate.getForObject(serviceInstance.getUri() + url, String.class);
        return res;
    }

}
