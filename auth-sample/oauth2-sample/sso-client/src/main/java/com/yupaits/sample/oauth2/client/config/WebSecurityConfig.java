package com.yupaits.sample.oauth2.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * @author yupaits
 * @date 2019/8/26
 */
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGOUT_SUCCESS_URL = "http://localhost:8091/auth/oauth/logout";

    private final OAuth2ClientContext clientContext;

    @Autowired
    public WebSecurityConfig(OAuth2ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    @Bean
    @ConfigurationProperties("spring.oauth2.client")
    public OAuth2ProtectedResourceDetails resourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public OAuth2RestTemplate restTemplate() {
        return new OAuth2RestTemplate(resourceDetails(), clientContext);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL).permitAll();
    }
}
