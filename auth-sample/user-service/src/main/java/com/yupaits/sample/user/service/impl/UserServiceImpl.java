package com.yupaits.sample.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.yupaits.sample.user.mapper.RoleMapper;
import com.yupaits.sample.user.mapper.UserMapper;
import com.yupaits.sample.user.mapper.UserRoleMapper;
import com.yupaits.sample.user.model.Role;
import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.model.UserRole;
import com.yupaits.sample.user.service.UserService;
import com.yupaits.sample.user.vo.UserVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.base.BaseResultServiceImpl;
import com.yupaits.yutool.orm.support.AuditLogger;
import com.yupaits.yutool.orm.support.VoBuilder;
import com.yupaits.yutool.orm.support.VoProps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Service
public class UserServiceImpl extends BaseResultServiceImpl<Long, User, UserMapper> implements UserService {
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    protected UserServiceImpl(OptService optService, AuditLogger auditLogger, RoleMapper roleMapper, UserRoleMapper userRoleMapper) {
        super(User.class, optService, auditLogger);
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void setDefaultVoConfig() {
        setDefaultVoClass(UserVo.class);
        setDefaultVoBuilder(new VoBuilder<UserVo, User>() {
            @Override
            public void buildVo(UserVo vo, User model) {
                vo.setRoles(getUserRoles(model.getId()));
            }
        });
    }

    @Override
    public void setDefaultModelBuilder() {
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        QueryWrapper<UserRole> userRoleQuery = new QueryWrapper<>();
        userRoleQuery.eq("user_id", userId);
        List<UserRole> userRoleList = userRoleMapper.selectList(userRoleQuery);
        if (CollectionUtils.isNotEmpty(userRoleList)) {
            QueryWrapper<Role> roleQuery = new QueryWrapper<>();
            roleQuery.in("id", userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
            List<Role> roleList = roleMapper.selectList(roleQuery);
            if (CollectionUtils.isNotEmpty(roleList)) {
                return roleList.stream().map(Role::getRole).collect(Collectors.toList());
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public boolean saveRole(Role role) {
        return retBool(roleMapper.insert(role));
    }

    @Override
    public boolean saveUserRole(UserRole userRole) {
        return retBool(userRoleMapper.insert(userRole));
    }

    @Override
    public Role getRoleByIdentity(String role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        return roleMapper.selectOne(queryWrapper);
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

    @Override
    public Result<UserVo> getVoByUsername(String username) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return resultOne(queryWrapper, VoProps.with(true, false, false));
    }
}
