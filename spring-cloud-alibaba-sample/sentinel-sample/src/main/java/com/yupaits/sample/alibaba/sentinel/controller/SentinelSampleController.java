package com.yupaits.sample.alibaba.sentinel.controller;

import com.yupaits.sample.alibaba.sentinel.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@RestController
public class SentinelSampleController {

    private final RestTemplate restTemplate;
    private final RestTemplate restTemplate2;
    private final SampleService sampleService;

    @Autowired
    public SentinelSampleController(RestTemplate restTemplate, @Qualifier("restTemplate2") RestTemplate restTemplate2, SampleService sampleService) {
        this.restTemplate = restTemplate;
        this.restTemplate2 = restTemplate2;
        this.sampleService = sampleService;
    }

    @GetMapping("/feign/user")
    public String showUserInfoByFeign() {
        return sampleService.showUserInfo();
    }

    @GetMapping("/ribbon/user")
    public String showUserInfoByRibbon() {
        return restTemplate.getForObject("http://nacos-sample/user", String.class);
    }

    @GetMapping("/ribbon/test")
    public String test() {
        return restTemplate2.getForObject("http://www.taobao.com/test", String.class);
    }
}
