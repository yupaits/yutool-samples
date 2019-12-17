package com.yupaits.sample.oauth2.resource.service.impl;

import com.yupaits.sample.user.mapper.UserMapper;
import com.yupaits.sample.user.model.User;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.support.service.MetaObjectOptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2019/8/23
 */
@Service
public class OptServiceImpl implements OptService {
    private final MetaObjectOptService metaObjectOptService;
    private final UserMapper userMapper;

    @Autowired
    public OptServiceImpl(MetaObjectOptService metaObjectOptService, UserMapper userMapper) {
        this.metaObjectOptService = metaObjectOptService;
        this.userMapper = userMapper;
    }

    @Override
    public String getOperatorId() {
        return metaObjectOptService.getOperatorId();
    }

    @Override
    public String getOptName(String operatorId) {
        if (StringUtils.isBlank(operatorId)) {
            return null;
        }
        User user = userMapper.selectById(Long.parseLong(operatorId));
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }
}
