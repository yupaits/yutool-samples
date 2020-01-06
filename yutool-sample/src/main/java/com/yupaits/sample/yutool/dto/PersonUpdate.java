package com.yupaits.sample.yutool.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.ResultCode;
import com.yupaits.yutool.commons.utils.ValidateUtils;
import com.yupaits.yutool.commons.utils.serializer.LongDeserializer;
import com.yupaits.yutool.orm.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 人类 更新对象
 * @author yupaits
 * @date 2019-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Person更新对象")
public class PersonUpdate extends BaseDto<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人类ID", required = true)
    @JsonDeserialize(using = LongDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Byte gender;

    @Override
    public void checkValid() throws BusinessException {
        if (!ValidateUtils.idValid(id)) {
            throw BusinessException.from(ResultCode.PARAMS_ERROR, true, "无效的ID");
        }
        if (StringUtils.isBlank(name)) {
            throw BusinessException.from(ResultCode.PARAMS_ERROR, true, "姓名不能为空");
        }
    }
}