package com.yupaits.sample.yutool.config;

import com.yupaits.yutool.push.support.im.ImProvider;
import com.yupaits.yutool.push.support.im.yunxin.YunxinProvider;
import com.yupaits.yutool.push.support.notification.GetuiProvider;
import com.yupaits.yutool.push.support.notification.NotificationProvider;
import com.yupaits.yutool.push.support.sms.CtInfoService;
import com.yupaits.yutool.push.support.sms.CtSmsProvider;
import com.yupaits.yutool.push.support.sms.SmsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 推送相关服务商配置
 * @author yupaits
 * @date 2019/8/9
 */
@Configuration
public class PushConfig {

    private final CtInfoService ctInfoService;

    @Autowired
    public PushConfig(CtInfoService ctInfoService) {
        this.ctInfoService = ctInfoService;
    }

    @Bean
    public ImProvider imProvider() {
        return new YunxinProvider();
    }

    @Bean
    public NotificationProvider notificationProvider() {
        return new GetuiProvider();
    }

    @Bean
    public SmsProvider smsProvider() {
        return new CtSmsProvider(ctInfoService);
    }

    @Bean
    @Qualifier("yunxinProvider")
    public YunxinProvider yunxinProvider() {
        return new YunxinProvider();
    }
}
