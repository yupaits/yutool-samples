package com.yupaits.sample.post.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yupaits.yutool.orm.base.BaseModel;
import lombok.*;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("post")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseModel<Long, Post> {
    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章标签
     */
    private String tags;
}
