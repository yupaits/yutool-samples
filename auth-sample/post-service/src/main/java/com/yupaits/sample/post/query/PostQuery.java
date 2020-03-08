package com.yupaits.sample.post.query;

import com.yupaits.sample.post.model.Post;
import com.yupaits.yutool.orm.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yupaits
 * @date 2020/1/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "文章查询对象")
public class PostQuery implements BaseQuery<Post> {
    private static final long serialVersionUID = 1L;
}
