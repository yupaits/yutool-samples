package com.yupaits.sample.alibaba.sentinel.service.fallback;

import com.yupaits.sample.alibaba.sentinel.service.SampleService;

/**
 * @author yupaits
 * @date 2019/8/27
 */
public class SampleServiceFallback implements SampleService {
    private Throwable throwable;

    public SampleServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String showUserInfo() {
        return String.format("sentinel feign fallback, cause: %s", throwable.getMessage());
    }
}
