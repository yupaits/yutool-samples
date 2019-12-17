package com.yupaits.sample.yutool.mq;

import com.yupaits.yutool.mq.core.IQueueEnum;
import lombok.Getter;

/**
 * 示例队列枚举
 * @author yupaits
 * @date 2019/8/2
 */
@Getter
public enum SampleQueue implements IQueueEnum {
    /**
     * 普通队列
     */
    SAMPLE_QUEUE("sample.exchange", "sample", "sample"),
    /**
     * 延迟队列
     */
    SAMPLE_TTL_QUEUE("sample.exchange.ttl", "sample.ttl", "sample.ttl");

    private String exchange;
    private String name;
    private String routeKey;

    SampleQueue(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
