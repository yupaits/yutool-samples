package com.yupaits.sample.yutool.jpa.config;

import com.yupaits.yutool.plugin.swagger.config.ApiProps;
import com.yupaits.yutool.plugin.swagger.utils.SwaggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ApiProps apiProps;

    @Autowired
    public SwaggerConfig(ApiProps apiProps) {
        this.apiProps = apiProps;
    }

    /**
     * 分组接口Docket
     */
    @Bean
    public Docket testApi() {
        return SwaggerUtils.docket(apiProps, "test");
    }
}
