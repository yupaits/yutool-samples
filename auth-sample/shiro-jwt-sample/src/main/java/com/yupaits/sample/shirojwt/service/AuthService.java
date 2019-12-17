package com.yupaits.sample.shirojwt.service;

import com.yupaits.sample.shirojwt.dto.LoginForm;
import com.yupaits.sample.user.vo.UserVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;

/**
 * @author yupaits
 * @date 2019/8/22
 */
public interface AuthService {

    /**
     * 登录操作
     * @param loginForm 登录表单对象
     * @return 登录结果
     */
    Result login(LoginForm loginForm);

    /**
     * 获取当前用户信息
     * @return 当前用户信息
     * @throws BusinessException 抛出BusinessException
     */
    Result<UserVo> currentUser() throws BusinessException;
}
