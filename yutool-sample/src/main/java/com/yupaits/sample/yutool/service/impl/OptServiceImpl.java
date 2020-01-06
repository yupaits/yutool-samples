package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.support.service.MetaObjectOptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作人接口实现
 * @author yupaits
 * @date 2019/7/30
 */
@Service
public class OptServiceImpl implements OptService {
    private static final String OPERATOR_NAME = "测试员";

    private final MetaObjectOptService metaObjectOptService;

    @Autowired
    public OptServiceImpl(MetaObjectOptService metaObjectOptService) {
        this.metaObjectOptService = metaObjectOptService;
    }

    @Override
    public String getOperatorId() {
        return metaObjectOptService.getOperatorId();
    }

    @Override
    public String getOptName(String operatorId) {
        return OPERATOR_NAME;
    }
}
