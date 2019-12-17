package com.yupaits.sample.rabbitmq.controller;

import com.yupaits.sample.rabbitmq.config.QueueEnum;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author yupaits
 * @date 2019/7/13
 */
@RestController
public class RabbitMQSampleController {
    private static final String OK = "ok";

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMQSampleController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @PostMapping("/send")
    public String sendMsg() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        amqpTemplate.convertAndSend(QueueEnum.TEST.getExchange(), QueueEnum.TEST.getRouteKey(), now);
        return OK;
    }
}
