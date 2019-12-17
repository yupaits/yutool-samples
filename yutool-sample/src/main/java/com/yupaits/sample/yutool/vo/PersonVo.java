package com.yupaits.sample.yutool.vo;

import com.yupaits.yutool.orm.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人类 Vo对象
 * @author yupaits
 * @date 2019-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "PersonVo对象")
public class PersonVo extends BaseVo<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Byte gender;

}