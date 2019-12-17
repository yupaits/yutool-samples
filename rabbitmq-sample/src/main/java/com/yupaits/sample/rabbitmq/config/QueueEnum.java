package com.yupaits.sample.rabbitmq.config;


import lombok.Getter;

/**
 * @author yupaits
 * @date 2019/7/13
 */
@Getter
public enum QueueEnum {
    /**
     * 测试队列
     */
    TEST("rabbit.test", "rabbit.test.send", "rabbit.test.send");

    private String exchange;
    private String name;
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
