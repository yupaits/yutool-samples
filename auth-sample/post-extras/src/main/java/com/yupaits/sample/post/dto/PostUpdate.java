package com.yupaits.sample.post.dto;

import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.ResultCode;
import com.yupaits.yutool.commons.utils.ValidateUtils;
import com.yupaits.yutool.orm.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostUpdate extends BaseDto<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID", required = true)
    private Long id;

    @ApiModelProperty(value = "文章标题", required = true)
    private String title;

    @ApiModelProperty(value = "文章内容", required = true)
    private String content;

    @ApiModelProperty(value = "文章标签")
    private String tags;

    @Override
    public void checkValid() throws BusinessException {
        if (!ValidateUtils.idValid(id)) {
            throw BusinessException.from(ResultCode.PARAMS_ERROR, true, "无效的ID");
        }
        if (StringUtils.isBlank(title)) {
            throw BusinessException.from(ResultCode.PARAMS_ERROR, true, "文章标题不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw BusinessException.from(ResultCode.PARAMS_ERROR, true, "文章内容不能为空");
        }
    }
}
