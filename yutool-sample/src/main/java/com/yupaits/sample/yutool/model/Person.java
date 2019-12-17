package com.yupaits.sample.yutool.model;

import com.baomidou.mybatisplus.annotation.*;
import com.yupaits.yutool.orm.annotation.AuditLog;
import com.yupaits.yutool.orm.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人类 Model
 * @author yupaits
 * @date 2019-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("person")
public class Person extends BaseModel<Long, Person> {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @AuditLog(description = "姓名")
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 所属部门ID
     */
    private Long orgId;

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