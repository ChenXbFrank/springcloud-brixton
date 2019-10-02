package com.didispace.web;

import com.didispace.rabbitmq.SendService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @Autowired
    SendService sendService;

    //往rabbitmq发送消息
    @RequestMapping("sendMember")
    public String sendMember(){
        Message build = MessageBuilder.withPayload("hello,rabbitmq".getBytes()).build();

        Boolean send = sendService.sendMember().send(build);

        return send.toString();
    }

}