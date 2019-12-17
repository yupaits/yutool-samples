package com.yupaits.sample.springsecurityjwt;

import com.yupaits.sample.user.mapper.RoleMapper;
import com.yupaits.sample.user.model.Role;
import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.model.UserRole;
import com.yupaits.sample.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yupaits
 * @date 2019/8/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test1_addUser() {
        String password = passwordEncoder.encode("password");
        User user = User.builder()
                .username("user")
                .password(password)
                .build();
        Assert.assertTrue(userService.save(user));
    }

    @Test
    public void test2_addRoles() {
        Role roleAdmin = Role.builder()
                .role("admin")
                .roleName("管理员")
                .description("系统管理员，拥有系统的全部权限")
                .enabled(true)
                .build();
        Role roleMaster = Role.builder()
                .role("master")
                .roleName("部门主管")
                .description("部门主管，拥有所属部门的所有权限")
                .enabled(true)
                .build();
        Assert.assertTrue(userService.saveRole(roleAdmin));
        Assert.assertTrue(userService.saveRole(roleMaster));
    }

    @Test
    public void test3_assignRoles() {
        User user = userService.getByUsername("user");
        Role role = userService.getRoleByIdentity("admin");
        UserRole userRole = UserRole.builder()
                .userId(user.getId())
                .roleId(role.getId())
                .build();
        Assert.assertTrue(userService.saveUserRole(userRole));
    }
}
