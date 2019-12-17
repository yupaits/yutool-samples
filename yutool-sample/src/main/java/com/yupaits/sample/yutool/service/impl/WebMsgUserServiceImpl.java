package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.push.support.webmsg.WebMsgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;

/**
 * WebSocket用户服务接口实现
 * @author yupaits
 * @date 2019/8/6
 */
@Service
public class WebMsgUserServiceImpl implements WebMsgUserService {
    private final OptService optService;

    @Autowired
    public WebMsgUserServiceImpl(OptService optService) {
        this.optService = optService;
    }

    @Override
    public String getWebMsgUserName(ServerHttpRequest request) {
        return optService.getOperatorId();
    }
}
