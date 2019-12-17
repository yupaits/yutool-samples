package com.yupaits.sample.springsecurityjwt.service.impl;

import com.yupaits.sample.springsecurityjwt.service.AuthService;
import com.yupaits.sample.user.service.UserService;
import com.yupaits.sample.user.vo.UserVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2019/8/23
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<UserVo> currentUser() throws BusinessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getVoByUsername(username);
    }
}
