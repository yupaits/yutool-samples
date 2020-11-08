package com.yupaits.sample.yutool.jpa.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.utils.ValidateUtils;
import com.yupaits.yutool.commons.utils.checker.params.ParamsCheckProcedure;
import com.yupaits.yutool.commons.utils.serializer.LongDeserializer;
import com.yupaits.yutool.orm.commons.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 人类 Dto对象
 * @author yupaits
 * @date 2019/8/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PersonDto对象")
public class PersonDto extends BaseDto<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人类ID")
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
        ParamsCheckProcedure.procedure()
                .add(id != null && !ValidateUtils.idValid(id), "无效的ID")
                .add(StringUtils.isBlank(name), "姓名不能为空")
                .check();
    }
}
