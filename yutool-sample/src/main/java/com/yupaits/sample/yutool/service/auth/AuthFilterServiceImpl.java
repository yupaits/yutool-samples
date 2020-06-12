package com.yupaits.sample.yutool.service.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.base.AbstractModel;
import com.yupaits.yutool.plugin.authfilter.support.AuthFilterProps;
import com.yupaits.yutool.plugin.authfilter.support.AuthScope;
import com.yupaits.yutool.plugin.authfilter.support.ICustomAuthFilter;
import com.yupaits.yutool.plugin.authfilter.support.service.AuthFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据权限过滤Service实现
 * @author yupaits
 * @date 2020/6/13
 */
@Service
public class AuthFilterServiceImpl implements AuthFilterService {
    private final OptService optService;

    @Autowired
    public AuthFilterServiceImpl(OptService optService) {
        this.optService = optService;
    }

    @Override
    public AuthFilterProps getPropsByName(String name) {
        //仅自己（创建人是自己的）
        AuthFilterProps onlySelfProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.ONLY_SELF);
        //指定部门（可指定多个，不包含下级）
        AuthFilterProps orgProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.SPECIFIED_ORG_WITH_SUB)
                .setOrgs(Lists.newArrayList("1", "2", "3"));
        //所有部门
        AuthFilterProps allOrgProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.ALL_ORG);
        //按用户过滤
        AuthFilterProps userProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.ALL_ORG)
                .setUsers(Lists.newArrayList("123456"))
                .setUserScope(AuthScope.ONLY_SELF);
        //按用户及部门进行过滤
        AuthFilterProps userWithOrgProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.ALL_ORG)
                .setUsers(Lists.newArrayList("123456"))
                .setUserScope(AuthScope.SPECIFIED_ORG)
                .setUserOrgs(Lists.newArrayList("1"));
        //按角色过滤
        AuthFilterProps roleProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.ALL_ORG)
                .setOperatorRoles(Lists.newArrayList("admin", "user"))
                .setRoles(Lists.newArrayList("admin", "superAdmin"))
                .setRoleScope(AuthScope.ONLY_SELF);
        //自定义过滤器
        AuthFilterProps customFilterProps = new AuthFilterProps().setOperator(optService.getOperatorId())
                .setOrgScope(AuthScope.ALL_ORG)
                .setCustomFilters(Lists.newArrayList(new ICustomAuthFilter() {
                    @Override
                    public <T extends AbstractModel> void appendQuery(QueryWrapper<T> queryWrapper) {
                        queryWrapper.ge("age", 30);
                    }
                }));
        return onlySelfProps;
    }
}

