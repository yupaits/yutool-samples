package com.yupaits.sample.alibaba.nacosconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class NacosSampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSampleConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
