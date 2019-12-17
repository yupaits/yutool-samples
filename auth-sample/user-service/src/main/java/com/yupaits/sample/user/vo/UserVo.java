package com.yupaits.sample.user.vo;

import com.google.common.collect.Lists;
import com.yupaits.yutool.orm.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "UserVo对象")
public class UserVo extends BaseVo<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "角色列表")
    private List<String> roles = Lists.newArrayList();
}
