package com.yupaits.sample.yutool.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yupaits.yutool.metadata.annotation.MetaField;
import com.yupaits.yutool.metadata.annotation.MetaObj;
import com.yupaits.yutool.orm.base.BaseModel;
import lombok.*;

/**
 * 动物
 * @author yupaits
 * @date 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("animal")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MetaObj(label = "动物")
public class Animal extends BaseModel<Long, Animal> {
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @MetaField(label = "昵称")
    private String nickname;

    /**
     * 年龄
     */
    @MetaField(label = "年龄")
    private Integer age;

    /**
     * 性别
     */
    @MetaField(label = "性别")
    private Byte gender;

    /**
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    /**
     * 版本号
     */
    @Version
    private Long version;

}