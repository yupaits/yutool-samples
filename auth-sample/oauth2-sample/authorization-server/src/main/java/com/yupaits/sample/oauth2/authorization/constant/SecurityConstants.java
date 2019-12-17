package com.yupaits.sample.oauth2.authorization.constant;

/**
 * 安全相关常量
 *
 * @author yupaits
 * @date 2019/8/23
 */
public class SecurityConstants {
    public static final String JWT_SIGNING_KEY = "oauth-sample";

    public static final String SESSION_ATTRIBUTES = "authorizationRequest";

    public static String[] ignorePaths = new String[]{"/doc.html", "/v2/api-docs", "/swagger-resources/**",
            "/webjars/**", "/login"};
}
