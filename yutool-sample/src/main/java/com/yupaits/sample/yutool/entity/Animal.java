package com.yupaits.sample.yutool.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yupaits.yutool.metadata.annotation.MetaField;
import com.yupaits.yutool.metadata.annotation.MetaObj;
import com.yupaits.yutool.metadata.base.BaseMetaModel;
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
@MetaObj(value = "动物", serviceBean = "animalService")
public class Animal extends BaseMetaModel<Long, Animal> {
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @MetaField("昵称")
    private String nickname;

    /**
     * 年龄
     */
    @MetaField("年龄")
    private Integer age;

    /**
     * 性别
     */
    @MetaField("性别")
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