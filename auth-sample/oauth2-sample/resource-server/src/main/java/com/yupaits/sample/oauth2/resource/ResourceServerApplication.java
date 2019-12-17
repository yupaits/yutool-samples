package com.yupaits.sample.oauth2.resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yupaits
 * @date 2019/8/23
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.yupaits.sample")
@MapperScan(basePackages = "com.yupaits.sample.*.mapper")
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }
}
