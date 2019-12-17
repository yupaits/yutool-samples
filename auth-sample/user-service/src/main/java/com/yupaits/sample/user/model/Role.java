package com.yupaits.sample.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yupaits.yutool.orm.base.BaseModel;
import lombok.*;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel<Long, Role> {
    private static final long serialVersionUID = 1L;

    /**
     * 角色标识
     */
    private String role;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 启用标记
     */
    @TableField("is_enabled")
    private Boolean enabled;
}
