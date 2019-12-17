package com.yupaits.sample.alibaba.nacosconsumer.service.config;

import com.yupaits.sample.alibaba.nacosconsumer.service.fallback.SampleServiceFallback;
import org.springframework.context.annotation.Bean;

/**
 * @author yupaits
 * @date 2019/8/27
 */
public class FeignConfig {
    @Bean
    public SampleServiceFallback sampleServiceFallback() {
        return new SampleServiceFallback();
    }
}
