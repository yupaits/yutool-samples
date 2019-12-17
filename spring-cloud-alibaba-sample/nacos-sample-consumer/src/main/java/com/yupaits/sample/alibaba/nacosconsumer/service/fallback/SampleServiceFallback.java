package com.yupaits.sample.alibaba.nacosconsumer.service.fallback;

import com.yupaits.sample.alibaba.nacosconsumer.service.SampleService;

/**
 * @author yupaits
 * @date 2019/8/27
 */
public class SampleServiceFallback implements SampleService {
    @Override
    public String showUserInfo() {
        return "show user info fallback.";
    }

    @Override
    public String showCustomUserInfo(String username, int age) {
        return "show custom user info fallback.";
    }

    @Override
    public String notFound() {
        return "not found fallback";
    }
}
