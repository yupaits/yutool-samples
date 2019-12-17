package com.yupaits.sample.shirojwt.shiro;

import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.service.UserService;
import io.jsonwebtoken.lang.Assert;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 使用Jwt进行认证授权的无状态Realm
 * @author yupaits
 * @date 2019/8/21
 */
public class StatelessRealm extends AuthorizingRealm {
    private final UserService userService;

    public StatelessRealm(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        if (principal instanceof StatelessToken) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            String username = ((StatelessToken) principal).getUsername();
            User user = userService.getByUsername(username);
            info.addRoles(userService.getUserRoles(user.getId()));
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Assert.notNull(token, "token不能为空");
        StatelessToken statelessToken = (StatelessToken) token;
        String username = statelessToken.getUsername();
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UnknownAccountException(String.format("用户 %s 不存在", username));
        }
        String accessToken = (String) token.getCredentials();
        return new SimpleAuthenticationInfo(statelessToken, accessToken, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }
}
