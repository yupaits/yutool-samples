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
@TableName("user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel<Long, User> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐
     */
    private String salt;

    public String getCredential() {
        return username + salt;
    }
}
