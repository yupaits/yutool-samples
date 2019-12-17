package com.yupaits.sample.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author yupaits
 * @date 2019/7/13
 */
@Slf4j
@Component
@RabbitListener(queues = "rabbit.test.send")
public class QueueReceiver {

    @RabbitHandler
    public void handle(LocalDateTime time) {
        log.info("接收消息：{}", time);
    }
}
