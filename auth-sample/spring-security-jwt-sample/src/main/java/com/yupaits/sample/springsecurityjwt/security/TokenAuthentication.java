package com.yupaits.sample.springsecurityjwt.security;

import com.yupaits.sample.springsecurityjwt.dto.UserDetailsImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * 基于Token的认证对象
 * @author yupaits
 * @date 2019/8/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenAuthentication extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;

    private String token;
    private final UserDetailsImpl principal;

    public TokenAuthentication(UserDetailsImpl principal) {
        super(principal.getAuthorities());
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
