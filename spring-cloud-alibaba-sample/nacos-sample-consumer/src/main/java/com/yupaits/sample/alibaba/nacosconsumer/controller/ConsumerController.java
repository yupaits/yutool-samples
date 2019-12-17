package com.yupaits.sample.alibaba.nacosconsumer.controller;

import com.yupaits.sample.alibaba.nacosconsumer.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@RestController
public class ConsumerController {

    private final RestTemplate restTemplate;
    private final SampleService sampleService;

    @Autowired
    public ConsumerController(RestTemplate restTemplate, SampleService sampleService) {
        this.restTemplate = restTemplate;
        this.sampleService = sampleService;
    }

    @GetMapping("/feign/user")
    public String showUserInfoByFeign() {
        return sampleService.showUserInfo();
    }

    @GetMapping("/feign/user/custom")
    public String showCustomUserInfoByFeign(@RequestParam(required = false) String username,
                                             @RequestParam(required = false, defaultValue = "0") int age) {
        return sampleService.showCustomUserInfo(username, age);
    }

    @GetMapping("/feign/notFound")
    public String notFound() {
        return sampleService.notFound();
    }

    @GetMapping("/ribbon/user")
    public String showUserInfoByRibbon() {
        return restTemplate.getForObject("http://nacos-sample/user", String.class);
    }

    @GetMapping("/ribbon/user/custom")
    public String showCustomUserInfoByRibbon(@RequestParam(required = false) String username,
                                             @RequestParam(required = false, defaultValue = "0") int age) {
        return restTemplate.getForObject("http://nacos-sample/user/custom?username={username}&age={age}", String.class, username, age);
    }
}
