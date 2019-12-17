package com.yupaits.sample.user.service;

import com.yupaits.sample.user.model.Role;
import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.model.UserRole;
import com.yupaits.sample.user.vo.UserVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.orm.base.IBaseService;

import java.util.List;

/**
 * @author yupaits
 * @date 2019/8/22
 */
public interface UserService extends IBaseService {
    /**
     * 根据用户名获取User
     * @param username 用户名
     * @return User
     */
    User getByUsername(String username);

    /**
     * 根据用户名获取UserVo
     * @param username 用户名
     * @return UserVo
     * @throws BusinessException 抛出BusinessException
     */
    Result<UserVo> getVoByUsername(String username) throws BusinessException;

    /**
     * 根据UserId获取角色列表
     * @param userId UserId
     * @return 角色列表
     */
    List<String> getUserRoles(Long userId);

    /**
     * 保存用户
     * @param user User
     * @return 保存结果
     */
    boolean save(User user);

    /**
     * 保存角色
     * @param role Role
     * @return 保存结果
     */
    boolean saveRole(Role role);

    /**
     * 保存用户-角色关系
     * @param userRole UserRole
     * @return 保存结果
     */
    boolean saveUserRole(UserRole userRole);

    /**
     * 根据角色标识获取Role
     * @param role 角色标识
     * @return Role
     */
    Role getRoleByIdentity(String role);
}
