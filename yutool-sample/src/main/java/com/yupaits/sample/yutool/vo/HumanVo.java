package com.yupaits.sample.yutool.vo;

import com.yupaits.yutool.orm.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yupaits
 * @date 2019/8/7
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "HumanVo对象")
public class HumanVo extends BaseVo<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Byte gender;

    @ApiModelProperty(value = "描述")
    private String description;
}
