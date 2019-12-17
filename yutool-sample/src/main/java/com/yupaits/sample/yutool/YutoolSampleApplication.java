package com.yupaits.sample.yutool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yupaits
 * @date 2019/7/29
 */
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.yupaits.yutool", "com.yupaits.sample.yutool"})
@MapperScan(basePackages = "com.yupaits.sample.yutool.mapper")
public class YutoolSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(YutoolSampleApplication.class, args);
    }
}
