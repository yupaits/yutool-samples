package com.yupaits.sample.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yupaits
 * @date 2019/7/9
 */
@EnableApolloConfig
@SpringBootApplication
public class ApolloSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloSampleApplication.class, args);
    }
}
