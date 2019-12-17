package com.yupaits.sample.shirojwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yupaits
 * @date 2019/8/21
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yupaits.sample")
@MapperScan(basePackages = "com.yupaits.sample.*.mapper")
public class ShiroJwtSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroJwtSampleApplication.class, args);
    }
}
