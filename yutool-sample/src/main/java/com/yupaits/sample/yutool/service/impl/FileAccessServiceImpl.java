package com.yupaits.sample.yutool.service.impl;

import com.yupaits.yutool.file.support.service.FileAccessService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件访问权限服务接口实现
 * @author yupaits
 * @date 2019/8/2
 */
@Service
public class FileAccessServiceImpl implements FileAccessService {
    @Override
    public boolean checkAccess(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
