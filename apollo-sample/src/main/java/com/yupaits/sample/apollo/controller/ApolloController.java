package com.yupaits.sample.apollo.controller;

import com.yupaits.sample.apollo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/7/9
 */
@RestController
public class ApolloController {

    private final AppConfig appConfig;

    @Autowired
    public ApolloController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/getconfig")
    public String getConfig(@RequestParam String key) {
        return appConfig.getConfig().getProperty(key, "no value");
    }
}
