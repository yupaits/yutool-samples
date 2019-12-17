package com.yupaits.sample.springsecurityjwt.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * 匿名认证对象
 * @author yupaits
 * @date 2019/8/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonymousAuthentication extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;

    public AnonymousAuthentication() {
        super(null);
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
