package com.yupaits.sample.springsecurityjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yupaits.sample.springsecurityjwt.constant.SecurityConstants;
import com.yupaits.sample.springsecurityjwt.dto.UserDetailsImpl;
import com.yupaits.yutool.commons.result.IResultCode;
import com.yupaits.yutool.commons.result.ResultCode;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.plugin.jwt.support.JwtHelper;
import com.yupaits.yutool.plugin.jwt.support.JwtResultCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jwt验证过滤器
 * @author yupaits
 * @date 2019/8/23
 */
public class TokenAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;

    private final List<RequestMatcher> matchers = Arrays.stream(SecurityConstants.ignorePaths).map(AntPathRequestMatcher::new).collect(Collectors.toList());

    public TokenAuthFilter(ObjectMapper objectMapper, JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.objectMapper = objectMapper;
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtHelper.getToken(request);
        if (StringUtils.isBlank(token)) {
            if (skipPathRequest(request)) {
                SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());
                filterChain.doFilter(request, response);
            } else {
                failCodeResult(response, ResultCode.UNAUTHORIZED);
            }
            return;
        }
        Claims claims = jwtHelper.getClaimsFromToken(token);
        if (claims == null) {
            failCodeResult(response, JwtResultCode.TOKEN_ILLEGAL);
            return;
        }
        String username = jwtHelper.getSubjectFromToken(token);
        if (StringUtils.isBlank(username)) {
            failCodeResult(response, JwtResultCode.TOKEN_INVALID);
            return;
        }
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
            TokenAuthentication authentication = new TokenAuthentication(userDetails);
            authentication.setToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException e) {
            failCodeResult(response, ResultCode.UNAUTHORIZED);
        }
    }

    /**
     * 判断请求是否可以跳过Token验证
     * @param request 请求体
     * @return 可跳过-true
     */
    private boolean skipPathRequest(HttpServletRequest request) {
        return new OrRequestMatcher(matchers).matches(request);
    }

    /**
     * 将响应内容写入response
     * @param response 响应体
     * @param resultCode 响应码内容
     * @throws IOException 抛出IOException
     */
    private void failCodeResult(HttpServletResponse response, IResultCode resultCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        objectMapper.writeValue(response.getWriter(), ResultWrapper.fail(resultCode));
    }
}
