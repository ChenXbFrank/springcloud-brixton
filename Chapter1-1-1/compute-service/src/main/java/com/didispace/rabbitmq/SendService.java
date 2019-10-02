package com.didispace.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface SendService {

    // 这是通道名称
    @Output("member")
    SubscribableChannel sendMember();
}
