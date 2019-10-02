package com.didispace.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class TestController {

    // 这里建议加上默认值，不然可能加载不到该from的时候会报错
    @Value("${from: DefaultValue}")
    private String from;

    @RequestMapping("/from")
    public String from() {
        return "hello " + this.from;
    }

}