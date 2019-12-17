package com.yupaits.sample.alibaba.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@RefreshScope
@RestController
public class SampleController {

    @Value("${user.name}")
    private String username;

    @Value("${user.age}")
    private int age;

    @GetMapping("/user")
    public String showUserInfo() {
        return String.format("Nacos Config UserInfo: {username: %s, age: %d}", username, age);
    }

    @GetMapping("/user/custom")
    public String showCustomUserInfo(@RequestParam(required = false) String username,
                                     @RequestParam(required = false, defaultValue = "0") int age) {
        return String.format("Custom UserInfo: {username: %s, age: %d}", username, age);
    }
}
