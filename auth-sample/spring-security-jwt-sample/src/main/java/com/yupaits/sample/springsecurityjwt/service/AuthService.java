package com.yupaits.sample.springsecurityjwt.service;

import com.yupaits.sample.user.vo.UserVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;

/**
 * @author yupaits
 * @date 2019/8/23
 */
public interface AuthService {

    /**
     * 获取当前用户信息
     * @return 当前用户信息
     * @throws BusinessException 抛出BusinessException
     */
    Result<UserVo> currentUser() throws BusinessException;
}
