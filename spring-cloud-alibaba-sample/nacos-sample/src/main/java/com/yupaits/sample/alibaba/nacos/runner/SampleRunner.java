package com.yupaits.sample.alibaba.nacos.runner;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@Slf4j
@Component
public class SampleRunner implements ApplicationRunner {
    private static final String DATA_ID = "nacos-sample.properties";
    private static final String GROUP = "DEFAULT_GROUP";

    private final NacosConfigProperties nacosConfigProperties;

    @Autowired
    public SampleRunner(NacosConfigProperties nacosConfigProperties) {
        this.nacosConfigProperties = nacosConfigProperties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nacosConfigProperties.configServiceInstance().addListener(DATA_ID, GROUP, new Listener() {

            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                Properties properties = new Properties();
                try {
                    properties.load(new StringReader(configInfo));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info("配置更新了：{}", properties);
            }
        });
    }
}
