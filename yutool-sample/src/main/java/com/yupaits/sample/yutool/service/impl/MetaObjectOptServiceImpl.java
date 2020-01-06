package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.orm.support.service.MetaObjectOptService;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2020/1/6
 */
@Service
public class MetaObjectOptServiceImpl implements MetaObjectOptService {
    private static final String OPERATOR_ID = "123456";

    @Override
    public String getOperatorId() {
        return OPERATOR_ID;
    }
}
