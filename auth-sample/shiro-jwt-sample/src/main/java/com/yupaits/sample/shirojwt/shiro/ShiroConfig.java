package com.yupaits.sample.shirojwt.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.yupaits.sample.user.service.UserService;
import com.yupaits.yutool.plugin.jwt.support.JwtHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

/**
 * Shiro配置
 * @author yupaits
 * @date 2019/8/21
 */
@Configuration
public class ShiroConfig {
    private final static String ANON = "anon";
    private final static String JWT_AUTH_FILTER = "jwtAuthc";

    private final ObjectMapper objectMapper;
    private final JwtHelper jwtHelper;
    private final UserService userService;

    @Autowired
    public ShiroConfig(ObjectMapper objectMapper, JwtHelper jwtHelper, UserService userService) {
        this.objectMapper = objectMapper;
        this.jwtHelper = jwtHelper;
        this.userService = userService;
    }

    @Bean
    public StatelessRealm statelessRealm() {
        StatelessRealm statelessRealm = new StatelessRealm(userService);
        statelessRealm.setCachingEnabled(false);
        return statelessRealm;
    }

    @Bean
    public StatelessSubjectFactory subjectFactory() {
        return new StatelessSubjectFactory();
    }

    @Bean
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultSubjectDAO defaultSubjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) defaultSubjectDAO.getSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        securityManager.setRealm(statelessRealm());
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setRememberMeManager(null);
        securityManager.setSessionManager(sessionManager());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filterMap = Maps.newHashMap();
        StatelessAuthFilter statelessAuthFilter = new StatelessAuthFilter(objectMapper, jwtHelper);
        filterMap.put(JWT_AUTH_FILTER, statelessAuthFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChains = Maps.newLinkedHashMap();
        filterChains.put("/doc.html", ANON);
        filterChains.put("/v2/api-docs", ANON);
        filterChains.put("/swagger-resources/**", ANON);
        filterChains.put("/webjars/**", ANON);
        filterChains.put("/login", ANON);
        filterChains.put("/**", JWT_AUTH_FILTER);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChains);

        return shiroFilterFactoryBean;
    }
}
