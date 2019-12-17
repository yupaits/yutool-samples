package com.yupaits.sample.post.vo;

import com.yupaits.yutool.orm.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "PostVo对象")
public class PostVo extends BaseVo<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章标签")
    private String tags;
}
