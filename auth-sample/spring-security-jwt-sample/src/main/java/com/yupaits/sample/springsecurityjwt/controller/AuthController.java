package com.yupaits.sample.springsecurityjwt.controller;

import com.yupaits.sample.springsecurityjwt.service.AuthService;
import com.yupaits.sample.user.vo.UserVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证授权接口
 * @author yupaits
 * @date 2019/8/23
 */
@RestController
@Api(tags = "认证授权接口")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/user")
    public Result<UserVo> currentUser() throws BusinessException {
        return authService.currentUser();
    }
}
