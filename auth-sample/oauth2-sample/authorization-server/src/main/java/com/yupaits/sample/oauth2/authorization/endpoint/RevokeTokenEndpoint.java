package com.yupaits.sample.oauth2.authorization.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yupaits
 * @date 2019/8/26
 */
@FrameworkEndpoint
public class RevokeTokenEndpoint {
    private static final String REVOKE_SUCCESS = "注销成功";
    private static final String REVOKE_FAIL = "注销失败";

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @DeleteMapping("/oauth/token")
    @ResponseBody
    public String revokeToken(String accessToken) {
        return consumerTokenServices.revokeToken(accessToken) ? REVOKE_SUCCESS : REVOKE_FAIL;
    }
}
