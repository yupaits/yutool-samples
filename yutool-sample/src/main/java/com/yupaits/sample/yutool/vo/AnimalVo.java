package com.yupaits.sample.yutool.vo;

import com.yupaits.yutool.metadata.base.BaseMetaVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 动物Vo对象
 * @author yupaits
 * @date 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "动物Vo对象")
public class AnimalVo extends BaseMetaVo<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Byte gender;

}