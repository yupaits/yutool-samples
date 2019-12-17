package com.yupaits.sample.yutool.config;

import com.yupaits.yutool.plugin.swagger.config.ApiProps;
import com.yupaits.yutool.plugin.swagger.utils.SwaggerUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger接口文档配置
 * @author yupaits
 * @date 2019/8/1
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @ConfigurationProperties(prefix = "api")
    public ApiProps apiProps() {
        return new ApiProps();
    }

    /**
     * 全局接口Docket（配置了分组接口时一般不要配置全局接口）
     */
    @Bean
    public Docket api() {
        return SwaggerUtils.docket(apiProps());
    }

    /**
     * 分组接口Docket
     */
    @Bean
    public Docket testApi() {
        return SwaggerUtils.docket(apiProps(), "test");
    }
}
