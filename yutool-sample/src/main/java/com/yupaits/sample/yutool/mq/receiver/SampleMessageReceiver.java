package com.yupaits.sample.yutool.mq.receiver;

import com.yupaits.yutool.cache.core.CacheTemplate;
import com.yupaits.yutool.mq.core.BaseReceiver;
import com.yupaits.yutool.mq.core.RetryableSender;
import com.yupaits.yutool.mq.exception.MqRetryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 示例队列消息接收器
 * @author yupaits
 * @date 2019/8/2
 */
@Slf4j
@Component
@RabbitListener(queues = {"sample"})
public class SampleMessageReceiver extends BaseReceiver<String> {

    @Autowired
    protected SampleMessageReceiver(RetryableSender sender, CacheTemplate cacheTemplate) {
        super(sender, cacheTemplate);
        setCancelCallback(message -> {
            log.info("取消重试消息回调，消息内容：{}", message);
        });
    }

    @Override
    public void handle(@Payload String message) {
        log.info("接收消息：{}", message);
    }

    @Override
    @RabbitHandler
    public void handle(@Payload String message, @Headers Map<String, Object> headers) {
        log.info("接收消息：{}，headers：{}", message, headers);
        try {
            cancelRetry(message, headers);
            if (isRetryable(headers)) {
                retry(message, headers);
            }
        } catch (MqRetryException e) {
            log.error(e.getMessage(), e);
        }
    }
}
