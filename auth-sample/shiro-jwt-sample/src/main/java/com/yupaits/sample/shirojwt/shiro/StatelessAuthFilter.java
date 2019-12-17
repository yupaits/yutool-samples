package com.yupaits.sample.shirojwt.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yupaits.sample.shirojwt.constant.JwtConstants;
import com.yupaits.yutool.commons.result.IResultCode;
import com.yupaits.yutool.commons.result.ResultCode;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.plugin.jwt.support.JwtHelper;
import com.yupaits.yutool.plugin.jwt.support.JwtResultCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt验证过滤器
 * @author yupaits
 * @date 2019/8/21
 */
public class StatelessAuthFilter extends AccessControlFilter {
    private final ObjectMapper objectMapper;
    private final JwtHelper jwtHelper;

    public StatelessAuthFilter(ObjectMapper objectMapper, JwtHelper jwtHelper) {
        this.objectMapper = objectMapper;
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = jwtHelper.getToken(request);
        if (StringUtils.isBlank(token)) {
            return failCodeResult(response, ResultCode.UNAUTHORIZED);
        }
        Claims claims = jwtHelper.getClaimsFromToken(token);
        if (claims == null) {
            return failCodeResult(response, JwtResultCode.TOKEN_ILLEGAL);
        }
        String username = jwtHelper.getSubjectFromToken(token);
        if (StringUtils.isBlank(username)) {
            return failCodeResult(response, JwtResultCode.TOKEN_INVALID);
        }
        StatelessToken statelessToken = new StatelessToken(String.valueOf(claims.get(JwtConstants.USER_ID, Long.class)), username, token);
        try {
            getSubject(servletRequest, servletResponse).login(statelessToken);
        } catch (AuthenticationException e) {
            return failCodeResult(response, ResultCode.UNAUTHORIZED);
        }
        getSubject(servletRequest, servletResponse).isPermitted(request.getRequestURI());
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        return failCodeResult(response, ResultCode.FORBIDDEN);
    }

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //允许跨域访问
        if (StringUtils.equals(request.getMethod(), RequestMethod.OPTIONS.name())) {
            return true;
        }
        return super.preHandle(servletRequest, servletResponse);
    }

    /**
     * 将响应内容写入response
     * @param response 响应体
     * @param resultCode 响应码内容
     * @return false
     * @throws IOException 抛出IOException
     */
    private boolean failCodeResult(HttpServletResponse response, IResultCode resultCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        objectMapper.writeValue(response.getWriter(), ResultWrapper.fail(resultCode));
        return false;
    }
}
