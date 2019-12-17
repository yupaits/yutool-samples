package com.yupaits.sample.alibaba.nacosconsumer.service;

import com.yupaits.sample.alibaba.nacosconsumer.service.config.FeignConfig;
import com.yupaits.sample.alibaba.nacosconsumer.service.fallback.SampleServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@FeignClient(name = "nacos-sample", fallback = SampleServiceFallback.class, configuration = FeignConfig.class)
public interface SampleService {

    @GetMapping("/user")
    String showUserInfo();

    @GetMapping("/user/custom")
    String showCustomUserInfo(@RequestParam String username, @RequestParam int age);

    @GetMapping("/notFound")
    String notFound();
}
