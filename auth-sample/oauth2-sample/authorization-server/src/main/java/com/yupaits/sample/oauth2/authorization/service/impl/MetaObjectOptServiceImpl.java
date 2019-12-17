package com.yupaits.sample.oauth2.authorization.service.impl;

import com.yupaits.sample.oauth2.authorization.dto.UserDetailsImpl;
import com.yupaits.yutool.orm.support.service.MetaObjectOptService;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) principal;
            return userDetails.getUserId();
        }
        return null;
    }
}
