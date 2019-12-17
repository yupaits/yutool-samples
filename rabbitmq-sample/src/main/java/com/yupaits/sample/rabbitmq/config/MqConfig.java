package com.yupaits.sample.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yupaits
 * @date 2019/7/13
 */
@Configuration
public class MqConfig {

    @Bean
    public DirectExchange directExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.TEST.getExchange())
                .durable(false)
                .build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QueueEnum.TEST.getName()).autoDelete().build();
    }

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(QueueEnum.TEST.getRouteKey());
    }
}
