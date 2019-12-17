package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.push.support.sms.CtInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 电信短信读取企业用户参数服务接口实现
 * @author yupaits
 * @date 2019/8/12
 */
@Service
@Data
public class CtInfoServiceImpl implements CtInfoService {

    @Value("${ct.sms.eprId:0}")
    private int eprId;
    @Value("${ct.sms.userId:}")
    private String userId;
    @Value("${ct.sms.userPassword:}")
    private String userPassword;
}
