package com.yupaits.sample.oauth2.resource.config;

import com.yupaits.yutool.plugin.swagger.config.ApiProps;
import com.yupaits.yutool.plugin.swagger.utils.SwaggerUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger接口文档配置
 * @author yupaits
 * @date 2019/8/22
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @ConfigurationProperties(prefix = "api")
    public ApiProps apiProps() {
        return new ApiProps();
    }

    @Bean
    public Docket api() {
        return SwaggerUtils.docket(apiProps());
    }
}
