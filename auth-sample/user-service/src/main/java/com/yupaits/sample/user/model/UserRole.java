package com.yupaits.sample.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yupaits.yutool.orm.base.BaseModel;
import lombok.*;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseModel<Long, UserRole> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
