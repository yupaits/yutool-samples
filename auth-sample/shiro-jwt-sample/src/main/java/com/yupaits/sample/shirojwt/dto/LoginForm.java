package com.yupaits.sample.shirojwt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@ApiModel(description = "登录表单对象")
public class LoginForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(hidden = true)
    public boolean isValid() {
        return !StringUtils.isAnyBlank(username, password);
    }
}
