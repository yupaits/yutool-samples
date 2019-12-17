package com.yupaits.sample.alibaba.sentinel;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.yupaits.sample.alibaba.sentinel.block.CustomBlockHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@EnableFeignClients
@SpringCloudApplication
public class SentinelSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelSampleApplication.class, args);
    }

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(blockHandlerClass = CustomBlockHandler.class, blockHandler = "handleException")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }
}
