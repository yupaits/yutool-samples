package com.yupaits.sample.yutool.dto;

import com.yupaits.yutool.orm.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 人类 创建对象
 * @author yupaits
 * @date 2019-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Person创建对象")
public class PersonCreate extends BaseDto<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Byte gender;

    @ApiModelProperty(hidden = true)
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(name);
    }
}