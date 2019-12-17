package com.yupaits.sample.yutool.config;

import com.yupaits.sample.yutool.mq.SampleQueue;
import com.yupaits.yutool.mq.utils.MqUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 * @author yupaits
 * @date 2019/8/2
 */
@Configuration
public class MqConfig {

    @Bean
    public DirectExchange sampleExchange() {
        return MqUtils.direct(SampleQueue.SAMPLE_QUEUE);
    }

    @Bean
    public DirectExchange sampleTtlExchange() {
        return MqUtils.direct(SampleQueue.SAMPLE_TTL_QUEUE);
    }

    @Bean
    public Queue sampleQueue() {
        return MqUtils.queue(SampleQueue.SAMPLE_QUEUE);
    }

    @Bean
    public Queue sampleTtlQueue() {
        return MqUtils.ttlQueue(SampleQueue.SAMPLE_TTL_QUEUE, SampleQueue.SAMPLE_QUEUE);
    }

    @Bean
    public Binding sampleBinding() {
        return MqUtils.binding(sampleExchange(), sampleQueue(), SampleQueue.SAMPLE_QUEUE);
    }

    @Bean
    public Binding sampleTtlBinding() {
        return MqUtils.binding(sampleTtlExchange(), sampleTtlQueue(), SampleQueue.SAMPLE_TTL_QUEUE);
    }
}
