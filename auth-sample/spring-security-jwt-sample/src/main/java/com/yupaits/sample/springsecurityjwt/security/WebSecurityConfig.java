package com.yupaits.sample.springsecurityjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.yupaits.sample.springsecurityjwt.constant.JwtConstants;
import com.yupaits.sample.springsecurityjwt.constant.SecurityConstants;
import com.yupaits.sample.springsecurityjwt.dto.UserDetailsImpl;
import com.yupaits.yutool.commons.result.IResultCode;
import com.yupaits.yutool.commons.result.ResultCode;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.plugin.jwt.support.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * SpringSecurity安全配置
 * @author yupaits
 * @date 2019/8/23
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper objectMapper;
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(ObjectMapper objectMapper, JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.objectMapper = objectMapper;
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public TokenAuthFilter tokenAuthFilter() {
        return new TokenAuthFilter(objectMapper, jwtHelper, userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedEx) -> {
                    failCodeResult(response, ResultCode.FORBIDDEN);
                })
                .and()
                .addFilterBefore(tokenAuthFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(SecurityConstants.ignorePaths).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                    Map<String, Object> claims = Maps.newHashMap();
                    claims.put(JwtConstants.SUBJECT, userDetails.getUsername());
                    claims.put(JwtConstants.USER_ID, userDetails.getUserId());
                    String token = jwtHelper.generateToken(claims);
                    successResult(response, token);
                })
                .failureHandler((request, response, authEx) -> {
                    failCodeResult(response, ResultCode.UNAUTHORIZED);
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 将返回数据写入response
     * @param response 响应体
     * @param data 返回数据
     * @throws IOException 抛出IOException
     */
    private void successResult(HttpServletResponse response, Object data) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        objectMapper.writeValue(response.getWriter(), ResultWrapper.success(data));
    }

    /**
     * 将失败码写入response
     * @param response 响应体
     * @param resultCode 响应码内容
     * @throws IOException 抛出IOException
     */
    private void failCodeResult(HttpServletResponse response, IResultCode resultCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        objectMapper.writeValue(response.getWriter(), ResultWrapper.fail(resultCode));
    }
}
