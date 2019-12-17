package com.yupaits.sample.post.dto;

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

    @ApiModelProperty(hidden = true)
    @Override
    public boolean isValid() {
        return ValidateUtils.idValid(id) && !StringUtils.isAnyBlank(title, content);
    }
}
