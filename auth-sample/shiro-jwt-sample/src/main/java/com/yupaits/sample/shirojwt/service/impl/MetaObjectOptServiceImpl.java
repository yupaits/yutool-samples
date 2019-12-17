package com.yupaits.sample.shirojwt.service.impl;

import com.yupaits.sample.shirojwt.shiro.StatelessToken;
import com.yupaits.yutool.orm.support.service.MetaObjectOptService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2019/8/23
 */
@Service
public class MetaObjectOptServiceImpl implements MetaObjectOptService {
    @Override
    public String getOperatorId() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof StatelessToken) {
            StatelessToken token = (StatelessToken) principal;
            return token.getUserId();
        }
        return null;
    }
}
