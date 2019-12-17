package com.yupaits.sample.springsecurityjwt.service.impl;

import com.yupaits.sample.springsecurityjwt.dto.UserDetailsImpl;
import com.yupaits.sample.springsecurityjwt.security.TokenAuthentication;
import com.yupaits.yutool.orm.support.service.MetaObjectOptService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2019/8/23
 */
@Service
public class MetaObjectOptServiceImpl implements MetaObjectOptService {
    @Override
    public String getOperatorId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof TokenAuthentication) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails != null ? userDetails.getUserId() : null;
        }
        return null;
    }
}
