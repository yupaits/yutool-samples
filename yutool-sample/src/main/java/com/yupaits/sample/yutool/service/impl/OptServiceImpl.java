package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.commons.service.OptService;
import org.springframework.stereotype.Service;

/**
 * 操作人接口实现
 * @author yupaits
 * @date 2019/7/30
 */
@Service
public class OptServiceImpl implements OptService {
    private static final String OPERATOR_ID = "123456";
    private static final String OPERATOR_NAME = "测试员";

    @Override
    public String getOperatorId() {
        return OPERATOR_ID;
    }

    @Override
    public String getOptName(String operatorId) {
        return OPERATOR_NAME;
    }
}
