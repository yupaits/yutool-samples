package com.yupaits.sample.alibaba.sentinel.service.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@Component
public class SampleServiceFallbackFactory implements FallbackFactory<SampleServiceFallback> {

    @Override
    public SampleServiceFallback create(Throwable throwable) {
        return new SampleServiceFallback(throwable);
    }
}
