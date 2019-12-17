package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.file.support.service.AntiTheftService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防盗链服务接口实现
 * @author yupaits
 * @date 2019/8/2
 */
@Service
public class AntiTheftServiceImpl implements AntiTheftService {
    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
