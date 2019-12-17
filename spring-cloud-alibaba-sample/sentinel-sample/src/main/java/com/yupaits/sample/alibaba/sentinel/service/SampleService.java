package com.yupaits.sample.alibaba.sentinel.service;

import com.yupaits.sample.alibaba.sentinel.service.fallback.SampleServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@FeignClient(name = "nacos-sample", fallbackFactory = SampleServiceFallbackFactory.class)
public interface SampleService {

    @GetMapping("/user")
    String showUserInfo();
}
