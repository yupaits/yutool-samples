package com.yupaits.sample.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author yupaits
 * @date 2019/7/15
 */
@Slf4j
@Service
public class AppConfig {

    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void onChange(ConfigChangeEvent changeEvent) {
        log.info("ConfigChangeEvent: {}", changeEvent);
        refreshConfig();
    }

    @PostConstruct
    private void refreshConfig() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            log.info("{}: {}", key, config.getProperty(key, "no value"));
        }
    }

    public Config getConfig() {
        return config;
    }
}
