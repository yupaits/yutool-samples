package com.yupaits.sample.alibaba.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSampleApplication.class, args);
    }
}
