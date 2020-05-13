package com.yupaits.sample.yutool.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.utils.ValidateUtils;
import com.yupaits.yutool.commons.utils.checker.params.ParamsCheckProcedure;
import com.yupaits.yutool.commons.utils.serializer.LongDeserializer;
import com.yupaits.yutool.orm.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 动物Dto对象
 * @author yupaits
 * @date 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "动物Dto对象")
public class AnimalDto extends BaseDto<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonDeserialize(using = LongDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Byte gender;

    @Override
    public void checkValid() throws BusinessException {
        ParamsCheckProcedure.procedure()
                .add(id != null && !ValidateUtils.idValid(id), "无效的动物ID")
                .check();
    }
}